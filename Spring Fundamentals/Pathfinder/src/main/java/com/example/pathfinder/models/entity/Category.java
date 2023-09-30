package com.example.pathfinder.models.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name ="description" )
    private String description;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
