package com.example.pathfinder.repository;

import com.example.pathfinder.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
