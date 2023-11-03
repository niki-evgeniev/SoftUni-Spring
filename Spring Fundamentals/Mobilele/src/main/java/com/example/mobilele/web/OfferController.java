package com.example.mobilele.web;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.services.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("offer-add");
    }

    @PostMapping("/add")
    public ModelAndView modelAndView(@ModelAttribute("offerAddBindingModel") @Valid OfferAddBindingModel offerAddBindingModel,
                                     BindingResult bindingResult) {

        if (!bindingResult.hasErrors()){
            boolean offerIsAdded = offerService.addOffer(offerAddBindingModel);
        }



        return new ModelAndView("offer-add");
    }

}
