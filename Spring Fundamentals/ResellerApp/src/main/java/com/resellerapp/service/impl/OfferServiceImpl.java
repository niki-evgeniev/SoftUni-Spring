package com.resellerapp.service.impl;

import com.resellerapp.model.BindingModel.OfferAddBindingModel;
import com.resellerapp.model.LoggedUser;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.view.BoughtOffersDTO;
import com.resellerapp.model.view.MyOfferDTO;
import com.resellerapp.model.view.OfferDTO;
import com.resellerapp.model.view.OtherOffersDTO;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(ConditionRepository conditionRepository, UserRepository userRepository, ModelMapper modelMapper, OfferRepository offerRepository, LoggedUser loggedUser) {
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.loggedUser = loggedUser;
    }


    @Override
    public void addOffer(OfferAddBindingModel offerAddBindingModel) {

        Condition condition = conditionRepository.findByConditionName(offerAddBindingModel.getCondition());
        User user = userRepository.findByUsername(loggedUser.getUsername());

//        String username = user.getUsername();
        String usernameLog = loggedUser.getUsername();

        Offer offer = modelMapper.map(offerAddBindingModel, Offer.class);
        offer.setCondition(condition);
        offer.setUser(user);

        offerRepository.save(offer);

    }

    @Override
    public OfferDTO getOffersFotHomePage() {
        List<Offer> allOffers = offerRepository.findAll();

        List<MyOfferDTO> myOffers = new ArrayList<>();
        List<BoughtOffersDTO> boughtOffers = new ArrayList<>();
        List<OtherOffersDTO> otherOffers = new ArrayList<>();

        for (Offer offer : allOffers) {
            String loggedUsername = loggedUser.getUsername();

            if (offer.getOffersBay() == null && offer.getUser().getUsername().equals(loggedUsername)) {
                myOffers.add(new MyOfferDTO(offer));
            } else if (offer.getOffersBay() != null && offer.getUser().getUsername().equals(loggedUsername)) {
                boughtOffers.add(new BoughtOffersDTO(offer));
            } else if (offer.getOffersBay() == null) {
                otherOffers.add(new OtherOffersDTO(offer));
            }
        }

        return new OfferDTO(myOffers, boughtOffers, otherOffers);


    }
}
