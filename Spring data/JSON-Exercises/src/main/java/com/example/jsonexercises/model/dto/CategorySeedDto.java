package com.example.jsonexercises.model.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CategorySeedDto {

    @Expose
    private String name;


    public CategorySeedDto() {
    }
    @Size(min = 3 , max = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorySeedDto that = (CategorySeedDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
