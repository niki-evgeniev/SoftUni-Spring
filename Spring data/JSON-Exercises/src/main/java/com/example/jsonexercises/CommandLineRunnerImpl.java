package com.example.jsonexercises;

import com.example.jsonexercises.model.dto.ProductCountDto;
import com.example.jsonexercises.model.dto.SoldProductDto;
import com.example.jsonexercises.model.entity.Category;
import com.example.jsonexercises.service.CategoryServices;
import com.example.jsonexercises.service.ProductServices;
import com.example.jsonexercises.service.UserServices;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.example.jsonexercises.constants.Paths.*;

@Component
public class CommandLineRunnerImpl  implements CommandLineRunner {

    private final CategoryServices categoryServices;
    private final UserServices userServices;
    private final ProductServices productServices;
    private final Scanner scanner;

    private final Gson gson;


    public CommandLineRunnerImpl(CategoryServices categoryServices, UserServices userServices,
                                 ProductServices productServices, Gson gson) {
        this.gson = gson;
        this.categoryServices = categoryServices;
        this.userServices = userServices;
        this.productServices = productServices;
        scanner = new Scanner(System.in);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to json exercise");
        seedCategory();
        System.out.println("Enter number of exercise");
        int numEx = Integer.parseInt(scanner.nextLine());

        switch (numEx){
            case 1 -> productInRange();
            case 2 -> successfullySoldProducts();
            case 3 -> categoriesByProductsCount();
        }

    }

    private void categoriesByProductsCount() {
        List<Category> allByCategoryAndProductsCount =
                categoryServices.findAllByCategoryAndProductsCount();
    }

    private void successfullySoldProducts() throws IOException {
        List<SoldProductDto> soldProductDtos = userServices.findAllUserWithMoreThenOneSoldProduct();

        String content = gson.toJson(soldProductDtos);
        String test = gson.toJson(soldProductDtos);

        Files.write(Path.of(PATH_JSON_OUTPUT + PRODUCTS_SOLD_PRODUCTS), Collections.singleton(content));

    }

    private void productInRange() throws IOException {
        List<ProductCountDto> productCountDtoList = productServices
                .findAllProductBetween(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String content = gson.toJson(productCountDtoList);

        Files.write(Path.of(PATH_JSON_OUTPUT + PRODUCTS_COUNT), Collections.singleton(content));

    }

    private void seedCategory() throws FileNotFoundException {
        categoryServices.seedCategory();
        userServices.seedUsers();
        productServices.seedProducts();
    }
}
