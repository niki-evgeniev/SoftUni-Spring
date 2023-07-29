package com.example.jsonexercises.model.dto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class SoldProductDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductBuyerDto> soldProducts;

    public SoldProductDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
