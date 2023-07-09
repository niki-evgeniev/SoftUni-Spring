package com.example.bookshop.Services;

import com.example.bookshop.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seed() throws IOException;

    Set<Category> getRandomCategory();
}
