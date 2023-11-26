package com.example.mobilele.web;

import com.example.mobilele.model.DTO.OfferSummaryBindingModel;
import com.example.mobilele.services.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("offers")
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ModelAndView allOffers (
            @PageableDefault( size = 10, sort = "price")
            Pageable pageable){

        ModelAndView modelAndView = new ModelAndView("offers");
        Page<OfferSummaryBindingModel> allOffers = offerService.getAllOffers(pageable);
        modelAndView.addObject("offers", allOffers);

        return modelAndView;
    }

}
