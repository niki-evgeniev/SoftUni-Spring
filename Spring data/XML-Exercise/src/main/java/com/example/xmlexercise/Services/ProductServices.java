package com.example.xmlexercise.Services;

import com.example.xmlexercise.model.Dto.ProductSeedDto;
import com.example.xmlexercise.model.Dto.ProductViewRootDto;

import java.util.List;

public interface ProductServices {

    Long productCountRepository();

    void seedProduct(List<ProductSeedDto> products);

    ProductViewRootDto findInRange();
}
