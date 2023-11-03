package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.entity.Offer;
import com.example.mobilele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;

    public OfferServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addOffer(OfferAddBindingModel offerAddBindingModel) {

        Offer map = modelMapper.map(offerAddBindingModel, Offer.class);

        System.out.println();


        return false;
    }
}
