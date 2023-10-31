package com.example.pathfinder.repository;

import com.example.pathfinder.models.Enum.CategoriesName;
import com.example.pathfinder.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByNameIn(Set<CategoriesName> categories);
}
