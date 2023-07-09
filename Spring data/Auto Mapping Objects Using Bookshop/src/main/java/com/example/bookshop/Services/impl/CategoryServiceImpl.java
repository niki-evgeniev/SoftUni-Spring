package com.example.bookshop.Services.impl;

import com.example.bookshop.Repository.CategoryRepository;
import com.example.bookshop.Services.CategoryService;
import com.example.bookshop.entity.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seed() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of("src/main/resources/files/categories.txt"))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(row -> {
                    Category category = new Category(row);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategory() {
        Set<Category> categorySet = new HashSet<>();

        int randId = ThreadLocalRandom.current().nextInt(1, 3);
        for (int i = 0; i < randId; i++) {
            long idCategory = ThreadLocalRandom.current()
                    .nextLong(1, categoryRepository.count() + 1);

            Category category = categoryRepository.findById(idCategory).orElse(null);

            categorySet.add(category);
        }

        return categorySet;
    }
}
