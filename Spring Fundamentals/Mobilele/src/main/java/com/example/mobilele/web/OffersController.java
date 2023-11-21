package com.example.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("offers")
public class OffersController {

    @GetMapping("/all")
    public ModelAndView allOffers (){
        return new ModelAndView("offers");
    }

}
