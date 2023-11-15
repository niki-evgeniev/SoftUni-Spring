package com.example.mobilele.model.DTO;

import com.example.mobilele.model.enums.CategoryModel;
import com.example.mobilele.model.enums.EngineType;
import com.example.mobilele.model.enums.TransmissionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class OfferAddBindingModel {

    @NotNull
    private EngineType engine;

    @Positive
    @NotNull
    private Long modelId;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private TransmissionType transmission;

    @NotEmpty
    private String imageUrl;

    @NotNull
    @Min(1950)
    private Integer year;

    @Positive
    @NotNull
    private Integer mileage;

    @NotEmpty
    @Size(min = 5, max = 512)
    private String description;

    public OfferAddBindingModel() {
    }


    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
