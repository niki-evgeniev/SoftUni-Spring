package com.dictionaryapp.controller;

import com.dictionaryapp.model.BindingModel.WordAddBindingModel;
import com.dictionaryapp.model.LoggedUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;
    private final LoggedUser loggedUser;

    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/word-add")
    public ModelAndView wordAdd() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("word-add");
    }

    @PostMapping("/word-add")
    public ModelAndView wordAdd(@Valid WordAddBindingModel wordAddBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("word-add");
        }

        wordService.addWord(wordAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        wordService.removeWord(id);
        return "redirect:/home";
    }


    @ModelAttribute
    WordAddBindingModel wordAddBindingModel() {
        return new WordAddBindingModel();
    }

}
