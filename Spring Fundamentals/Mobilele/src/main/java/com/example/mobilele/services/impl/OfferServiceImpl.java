package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.DTO.OfferDetailsBindingModel;
import com.example.mobilele.model.DTO.OfferSummaryBindingModel;
import com.example.mobilele.model.entity.Model;
import com.example.mobilele.model.entity.Offer;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.services.OfferService;
import com.example.mobilele.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


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
                .orElseThrow(() -> new IllegalArgumentException("Model with id" + offerAddBindingModel.getModelId() + "not found!"));
        newOffer.setModel(model);
        newOffer.setCreated(LocalDateTime.now());
//        newOffer.setSeller();


        offerRepository.save(newOffer);
        return true;
    }

    @Override
    public Page<OfferSummaryBindingModel> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @Override
    public Optional<OfferDetailsBindingModel> getOfferDetails(String id) {
        return offerRepository.findById(Long.valueOf(id))
                .map(OfferServiceImpl::mapAsDetails);

    }

    @Override
    public void deleteOffer(String id) {
        offerRepository.deleteById(Long.valueOf(id));
    }

    private static OfferDetailsBindingModel mapAsDetails(Offer offer) {
        return new OfferDetailsBindingModel(
                offer.getId().toString(),
                offer.getModel().getBrand().getName(),
                offer.getModel().getName(),
                offer.getYear(),
                offer.getMileage(),
                offer.getPrice(),
                offer.getImageUrl(),
                offer.getEngine(),
                offer.getTransmission());
    }

    private static OfferSummaryBindingModel mapAsSummary(Offer offer) {
        return new OfferSummaryBindingModel(
                offer.getId().toString(),
                offer.getModel().getBrand().getName(),
                offer.getModel().getName(),
                offer.getYear(),
                offer.getMileage(),
                offer.getPrice(),
                offer.getImageUrl(),
                offer.getEngine(),
                offer.getTransmission());
    }

    private static Offer map(OfferAddBindingModel offerAddBindingModel) {
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
