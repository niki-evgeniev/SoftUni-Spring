package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.entity.Offer;
import com.example.mobilele.model.entity.User;
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

    public OfferServiceImpl(ModelMapper modelMapper,
                            UserRepository userRepository, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean addOffer(OfferAddBindingModel offerAddBindingModel) {

        User user = userRepository.findById(offerAddBindingModel.getModelId())
                .orElse(null);

        if (user != null) {
            Offer addOffer = modelMapper.map(offerAddBindingModel, Offer.class);
            addOffer.setSeller(user);
            addOffer.setCreated(LocalDateTime.now());
            offerRepository.save(addOffer);
            return true;
        }

        return false;
    }
}
