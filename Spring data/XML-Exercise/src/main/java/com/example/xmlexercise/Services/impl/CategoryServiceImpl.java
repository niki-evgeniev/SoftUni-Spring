package com.example.xmlexercise.Services.impl;

import com.example.xmlexercise.Repository.CategoryRepository;
import com.example.xmlexercise.Services.CategoryService;
import com.example.xmlexercise.model.Dto.CategorySeedDto;
import com.example.xmlexercise.model.entity.Category;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategory(List<CategorySeedDto> categories) {

        if (categoryRepository.count() > 0) {
            return;
        }
        categories
                .stream()
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Long categoryCountRepository() {
        return categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int categoryCount = ThreadLocalRandom.current().nextInt(1, 4);
        long categoryRepositorySize = categoryRepository.count() + 1;

        for (int i = 0; i < categoryCount; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, categoryRepositorySize);

            categories.add(categoryRepository.findById(randomId)
                    .orElse(null));

        }

        return categories;
    }
}
