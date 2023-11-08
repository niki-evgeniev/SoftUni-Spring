package com.resellerapp.controller;

import com.resellerapp.model.BindingModel.OfferAddBindingModel;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/offer-add")
    public ModelAndView addOffer(){
        return new ModelAndView("offer-add");
    }

    @PostMapping("/offer-add")
    public ModelAndView addOffer(@Valid OfferAddBindingModel offerAddBindingModel, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ModelAndView("offer-add");
        }

        offerService.addOffer(offerAddBindingModel);

        return new ModelAndView("redirect:/home");

    }

    @ModelAttribute OfferAddBindingModel offerAddBindingModel(){
        return new OfferAddBindingModel();
    }
}
