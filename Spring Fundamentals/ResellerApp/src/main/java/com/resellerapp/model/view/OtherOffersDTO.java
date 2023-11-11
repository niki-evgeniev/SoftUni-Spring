package com.resellerapp.model.view;

import com.resellerapp.model.entity.Offer;

public class OtherOffersDTO  extends MyOfferDTO{
    private String sellerUsername;

    public OtherOffersDTO() {
    }

    public OtherOffersDTO(Offer offer) {
        super(offer);
        sellerUsername = offer.getUser().getUsername();

    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

}
