package com.example.mobilele.services;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.DTO.OfferSummaryBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    boolean addOffer(OfferAddBindingModel offerAddBindingModel);

    Page<OfferSummaryBindingModel> getAllOffers(Pageable pageable);
}
