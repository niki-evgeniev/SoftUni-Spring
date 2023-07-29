package com.example.xmlexercise;

import com.example.xmlexercise.Services.CategoryService;
import com.example.xmlexercise.Services.ProductServices;
import com.example.xmlexercise.Services.UserServices;
import com.example.xmlexercise.model.Dto.CategorySeedRootDto;
import com.example.xmlexercise.model.Dto.ProductSeedRootDto;
import com.example.xmlexercise.model.Dto.ProductViewRootDto;
import com.example.xmlexercise.model.Dto.UsersSeedRootDto;
import com.example.xmlexercise.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String FILE_XML_PATH = "src/main/resources/xml-input/";
    private static final String FILE_XML_OUT_PATH = "src/main/resources/xml-output/";
    private static final String FILE_XML_PATH_CATEGORY = "categories.xml";
    private static final String FILE_XML_PATH_USERS = "users.xml";
    private static final String FILE_XML_PATH_PRODUCT = "products.xml";
    private static final String PRODUCTS_IN_RANGE = "products-in-range.xml";
    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserServices userServices;
    private final ProductServices productServices;
    private final Scanner scanner;

    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserServices userServices,
                                 ProductServices productServices) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userServices = userServices;
        this.productServices = productServices;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("enter ex number");
        int numEx = Integer.parseInt(scanner.nextLine());

        switch (numEx){
            case 1 -> productInRange();
        }

    }

    private void productInRange() throws JAXBException {

        ProductViewRootDto productViewRootDto = productServices.findInRange();

        xmlParser.writeToFile(FILE_XML_OUT_PATH + PRODUCTS_IN_RANGE, productViewRootDto);

    }

    private void seedData() throws JAXBException, FileNotFoundException {

        if (categoryService.categoryCountRepository() == 0) {

            CategorySeedRootDto categorySeedRootDto = xmlParser.fromFile(
                    FILE_XML_PATH + FILE_XML_PATH_CATEGORY, CategorySeedRootDto.class);

            categoryService.seedCategory(categorySeedRootDto.getCategories());
        }

        if (userServices.usersCountRepository() == 0) {

            UsersSeedRootDto usersSeedRootDto = xmlParser.fromFile(
                    FILE_XML_PATH + FILE_XML_PATH_USERS, UsersSeedRootDto.class);

            userServices.seedUsers(usersSeedRootDto.getUsers());
        }

        if (productServices.productCountRepository() == 0) {

            ProductSeedRootDto productSeedRootDto = xmlParser.fromFile(
                    FILE_XML_PATH + FILE_XML_PATH_PRODUCT, ProductSeedRootDto.class);

            productServices.seedProduct(productSeedRootDto.getProducts());
        }

    }
}
