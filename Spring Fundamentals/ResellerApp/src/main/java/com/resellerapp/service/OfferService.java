package com.resellerapp.service;

import com.resellerapp.model.BindingModel.OfferAddBindingModel;
import com.resellerapp.model.view.OfferDTO;

public interface OfferService {
    void addOffer(OfferAddBindingModel offerAddBindingModel);

    OfferDTO getOffersFotHomePage();
}
