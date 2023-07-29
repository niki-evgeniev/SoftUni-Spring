package com.example.jsonexercises.model.dto.ProductsCountDto;

import com.google.gson.annotations.Expose;

import java.math.BigInteger;

public class productAllCountAndSumDto {

    @Expose
    private String category;
    @Expose
    private Integer productsCount;
    @Expose
    private BigInteger averagePrice;
    @Expose
    private BigInteger totalRevenue;

    public productAllCountAndSumDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public BigInteger getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigInteger averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigInteger getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigInteger totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
