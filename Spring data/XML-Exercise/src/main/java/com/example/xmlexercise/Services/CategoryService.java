package com.example.xmlexercise.Services;

import com.example.xmlexercise.model.Dto.CategorySeedDto;
import com.example.xmlexercise.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategory(List<CategorySeedDto> categories);

    Long categoryCountRepository();

    Set<Category> getRandomCategories();
}
