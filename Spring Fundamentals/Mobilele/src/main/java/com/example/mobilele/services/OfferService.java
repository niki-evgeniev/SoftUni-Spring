package com.example.mobilele.services;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.DTO.OfferDetailsBindingModel;
import com.example.mobilele.model.DTO.OfferSummaryBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OfferService {
    boolean addOffer(OfferAddBindingModel offerAddBindingModel);

    Page<OfferSummaryBindingModel> getAllOffers(Pageable pageable);

    Optional<OfferDetailsBindingModel> getOfferDetails(String id);

    void deleteOffer(String id);
}
