package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(name = "needed_time", nullable = false)
    private Integer neededTime;

    public Category() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
