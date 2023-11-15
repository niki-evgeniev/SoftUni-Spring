package com.example.mobilele.repository;

import com.example.mobilele.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b")
    List<Brand> getAllBrands();

}
