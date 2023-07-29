package com.example.jsonexercises.service.impl;

import com.example.jsonexercises.model.dto.CategorySeedDto;
import com.example.jsonexercises.model.entity.Category;
import com.example.jsonexercises.repository.CategoryRepository;
import com.example.jsonexercises.service.CategoryServices;
import com.example.jsonexercises.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.jsonexercises.constants.OutPutMessages.*;
import static com.example.jsonexercises.constants.Paths.JSON_FILE_CATEGORY;
import static com.example.jsonexercises.constants.Paths.PATH_JSON_INPUT;

@Service
public class CategoryServicesImpl implements CategoryServices {

    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServicesImpl(Gson gson, CategoryRepository categoryRepository,
                                ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.gson = gson;
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategory() throws FileNotFoundException {

        if (categoryRepository.count() > 0) {
            System.out.println(JSON_IS_ALREADY_LOADED + CATEGORY_DB);
            return;
        }

        FileReader fileReader = new FileReader(Path.of(PATH_JSON_INPUT + JSON_FILE_CATEGORY).toFile());

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileReader, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos).filter(validationUtil::isValid)
                .map(CategorySeedDto -> modelMapper.map(CategorySeedDto, Category.class))
                .forEach(categoryRepository::save);
        System.out.println(SUCCESSFUL_ADDED + CATEGORY_DB);
    }

    @Override
    public Set<Category> getRandomCategories() {

        Set<Category> randomCategory = new HashSet<>();

        int categoryCount = ThreadLocalRandom.current().nextInt(1, 4);

        long sizeRepository = categoryRepository.count();

        for (int i = 0; i < categoryCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, sizeRepository);
            randomCategory.add(categoryRepository.findById(randomId).orElse(null));
        }
        return randomCategory;
    }

    @Override
    public List<Category> findAllByCategoryAndProductsCount() {

        

        return null;
    }
}
