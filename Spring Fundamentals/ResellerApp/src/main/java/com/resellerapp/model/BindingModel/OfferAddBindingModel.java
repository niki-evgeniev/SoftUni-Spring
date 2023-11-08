package com.resellerapp.model.BindingModel;

import com.resellerapp.model.enums.ConditionNameEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferAddBindingModel {
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;
    @NotNull
    private ConditionNameEnum condition;

    public OfferAddBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionNameEnum condition) {
        this.condition = condition;
    }
}
