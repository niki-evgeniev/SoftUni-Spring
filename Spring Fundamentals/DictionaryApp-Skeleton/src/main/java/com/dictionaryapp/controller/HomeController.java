package com.dictionaryapp.controller;

import com.dictionaryapp.model.LoggedUser;
import com.dictionaryapp.model.view.ViewHomeModel;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final WordService wordService;

    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
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

        ModelAndView modelAndView = new ModelAndView("home");
        ViewHomeModel viewHomeModel = wordService.getAllInfo();
        modelAndView.addObject("viewModel", viewHomeModel);

        return modelAndView;
    }

    @GetMapping("/home/deleteAll")
    public String removeAll() {

        wordService.removeAll();
        return "redirect:/home";
    }
}
