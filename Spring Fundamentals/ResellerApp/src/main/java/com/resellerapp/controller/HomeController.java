package com.resellerapp.controller;

import com.resellerapp.model.LoggedUser;
import com.resellerapp.model.view.OfferDTO;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final OfferService offerService;

    public HomeController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("/home");
        OfferDTO offersFotHomePage = offerService.getOffersFotHomePage();

        modelAndView.addObject("offerDTO", offersFotHomePage);

        return modelAndView;
    }


}
