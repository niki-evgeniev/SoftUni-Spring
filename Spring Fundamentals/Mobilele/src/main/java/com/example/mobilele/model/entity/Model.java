package com.example.mobilele.model.entity;

import com.example.mobilele.model.enums.CategoryModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {


    @Column(name = "name")
    private String name;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryModel category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @ManyToOne
    private Brand brand;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Brand getBrand() {
        return brand;
    }


    public void setBrand(Brand brand) {
        this.brand = brand;
    }


}
