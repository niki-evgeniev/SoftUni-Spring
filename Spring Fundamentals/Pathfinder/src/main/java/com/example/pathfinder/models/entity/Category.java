package com.example.pathfinder.models.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private CategoriesName name;

    @Column(name ="description", columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public CategoriesName getName() {
        return name;
    }

    public void setName(CategoriesName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
