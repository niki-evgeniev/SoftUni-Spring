package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.entity.Model;
import com.example.mobilele.model.entity.Offer;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(ModelMapper modelMapper,
                            UserRepository userRepository, OfferRepository offerRepository, ModelRepository modelRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean addOffer(OfferAddBindingModel offerAddBindingModel) {

        Offer newOffer = map(offerAddBindingModel);

//        Offer newOffer = modelMapper.map(offerAddBindingModel, Offer.class);
        Model model = modelRepository.findById(offerAddBindingModel.getModelId())
                .orElseThrow( ()-> new IllegalArgumentException("Model with id" + offerAddBindingModel.getModelId() + "not found!"));
        newOffer.setModel(model);
        newOffer.setCreated(LocalDateTime.now());
//        newOffer.setSeller();


        offerRepository.save(newOffer);
        return true;
    }

    private Offer map(OfferAddBindingModel offerAddBindingModel) {
        Offer offer = new Offer();

        offer.setDescription(offerAddBindingModel.getDescription());
        offer.setImageUrl(offerAddBindingModel.getImageUrl());
        offer.setEngine(offerAddBindingModel.getEngine());
        offer.setMileage(offerAddBindingModel.getMileage());
        offer.setPrice(offerAddBindingModel.getPrice());
        offer.setTransmission(offerAddBindingModel.getTransmission());
        offer.setYear(offerAddBindingModel.getYear());
        return offer;
    }


}
