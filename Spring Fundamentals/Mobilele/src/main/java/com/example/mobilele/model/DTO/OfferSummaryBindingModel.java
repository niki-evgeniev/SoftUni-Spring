package com.example.mobilele.model.DTO;

import com.example.mobilele.model.enums.EngineType;
import com.example.mobilele.model.enums.TransmissionType;

import java.math.BigDecimal;

public record OfferSummaryBindingModel(
        String id,
        String brand,
        String model,
        int year,
        int mileage,
        BigDecimal price,
        String imageUrl,
        EngineType engine,
        TransmissionType transmission

) {
    public String summary() {
        return brand + " " + model + " " + year;
    }
}
