package com.example.xmlexercise.Services.impl;

import com.example.xmlexercise.Repository.ProductRepository;
import com.example.xmlexercise.Services.CategoryService;
import com.example.xmlexercise.Services.ProductServices;
import com.example.xmlexercise.Services.UserServices;
import com.example.xmlexercise.model.Dto.ProductSeedDto;
import com.example.xmlexercise.model.Dto.ProductViewDto;
import com.example.xmlexercise.model.Dto.ProductViewRootDto;
import com.example.xmlexercise.model.entity.Product;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserServices userServices;
    private final CategoryService categoryService;

    public ProductServicesImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserServices userServices, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userServices = userServices;
        this.categoryService = categoryService;
    }

    @Override
    public Long productCountRepository() {
        return productRepository.count();
    }

    @Override
    public void seedProduct(List<ProductSeedDto> products) {

        products.stream()
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);

                    product.setSeller(userServices.getRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0){
                        product.setBuyer(userServices.getRandomUser());
                    }
                    product.setCategories(categoryService.getRandomCategories());
                    return product;
                })
                .forEach(productRepository::save);

    }

    @Override
    public ProductViewRootDto findInRange() {
        ProductViewRootDto rootDto = new ProductViewRootDto();

        rootDto.setProducts(productRepository.findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500L),
                BigDecimal.valueOf(1000L))
                .stream().map(product -> {
                    ProductViewDto productViewDto = modelMapper.map(product, ProductViewDto.class);
                    productViewDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));


                    return productViewDto;
                        })
                .collect(Collectors.toList()));
        return rootDto;
    }
}
