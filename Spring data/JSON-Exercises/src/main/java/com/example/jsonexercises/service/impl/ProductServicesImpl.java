package com.example.jsonexercises.service.impl;

import com.example.jsonexercises.model.dto.ProductCountDto;
import com.example.jsonexercises.model.dto.ProductSeedDto;
import com.example.jsonexercises.model.entity.Product;
import com.example.jsonexercises.repository.ProductRepository;
import com.example.jsonexercises.service.CategoryServices;
import com.example.jsonexercises.service.ProductServices;
import com.example.jsonexercises.service.UserServices;
import com.example.jsonexercises.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.jsonexercises.constants.OutPutMessages.*;
import static com.example.jsonexercises.constants.Paths.JSON_FILE_PRODUCTS;
import static com.example.jsonexercises.constants.Paths.PATH_JSON_INPUT;

@Service
public class ProductServicesImpl implements ProductServices {

    public final ProductRepository productRepository;
    public final CategoryServices categoryServices;
    public final UserServices userServices;
    public final Gson gson;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;

    public ProductServicesImpl(ProductRepository productRepository,
                               CategoryServices categoryServices, UserServices userServices,
                               Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryServices = categoryServices;
        this.userServices = userServices;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws FileNotFoundException {

        if (productRepository.count() > 0){
            System.out.println(JSON_IS_ALREADY_LOADED + PRODUCT_DB);
            return;
        }

        FileReader fileReader = new FileReader(Path.
                of(PATH_JSON_INPUT + JSON_FILE_PRODUCTS).toFile());

        ProductSeedDto[] productSeedDtos = gson.fromJson(fileReader, ProductSeedDto[].class);

        List<Product> productToSave = Arrays.stream(productSeedDtos).
                filter(validationUtil::isValid)
                .map(ProductSeedDto -> {
                    Product product = modelMapper.map(ProductSeedDto, Product.class);
                    product.setSeller(userServices.getRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0) {
                        product.setBuyer(userServices.getRandomUser());
                    }

                    product.setCategories(categoryServices.getRandomCategories());

                    return product;
                }).collect(Collectors.toList());

        productRepository.saveAll(productToSave);
        System.out.println(SUCCESSFUL_ADDED + PRODUCT_DB);
    }

    @Override
    public List<ProductCountDto> findAllProductBetween(BigDecimal lowerPrice, BigDecimal higherPrice) {

        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(lowerPrice, higherPrice)
                .stream().map(product -> {
                    ProductCountDto productCountDto = modelMapper
                            .map(product, ProductCountDto.class);

                    productCountDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productCountDto;
                }).collect(Collectors.toList());
    }
}
