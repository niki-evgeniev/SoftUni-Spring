package com.example.jsonexercises.repository;

import com.example.jsonexercises.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.name, count (c.id), avg(p.price), sum(p.price)" +
            "FROM Product p join p.categories c group by c.id")
    List<Category> getAllCountAndSum();
}
