package com.example.jsonexercises.service;

import com.example.jsonexercises.model.entity.Category;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface CategoryServices {

    void seedCategory() throws FileNotFoundException;

    Set<Category> getRandomCategories();

    List<Category> findAllByCategoryAndProductsCount();
}
