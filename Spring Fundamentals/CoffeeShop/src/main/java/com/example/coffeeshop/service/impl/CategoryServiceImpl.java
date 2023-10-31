package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initDataBaseCategories() {
        if (categoryRepository.count() == 0) {

            Arrays.stream(CategoryNameEnum.values()).forEach(categoryNameEnum -> {
                        Category category = new Category();
                        category.setName(categoryNameEnum);
                        switch (categoryNameEnum) {
                            case Drink -> category.setNeededTime(1);
                            case Coffee -> category.setNeededTime(2);
                            case Other -> category.setNeededTime(5);
                            case Cake -> category.setNeededTime(10);
                        }
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByCategoryName(CategoryNameEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
