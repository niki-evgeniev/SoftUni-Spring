package com.resellerapp.model.view;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.enums.ConditionNameEnum;

public class MyOfferDTO extends BoughtOffersDTO {

    private Long id;

    private ConditionNameEnum condition;

    public MyOfferDTO() {
    }

    public MyOfferDTO(Offer offer) {
        super(offer);
        id = offer.getId();
        condition = offer.getCondition().getConditionName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionNameEnum condition) {
        this.condition = condition;
    }
}
