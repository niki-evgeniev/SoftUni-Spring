package com.example.xmlexercise.model.Dto;


import com.example.xmlexercise.model.entity.Product;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewRootDto {

    @XmlElement(name = "product")
    private List<ProductViewDto> products;

    public List<ProductViewDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewDto> products) {
        this.products = products;
    }
}
