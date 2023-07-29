package com.example.jsonexercises.service;

import com.example.jsonexercises.model.dto.ProductCountDto;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductServices {

    void seedProducts() throws FileNotFoundException;

    List<ProductCountDto> findAllProductBetween(BigDecimal lowerPrice, BigDecimal higherPrice);
}
