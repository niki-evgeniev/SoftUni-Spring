package com.resellerapp.controller;

import com.resellerapp.model.BindingModel.UserLoginBindingModel;
import com.resellerapp.model.BindingModel.UserRegisterBindingModel;
import com.resellerapp.model.LoggedUser;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isLoginComplete = userService.login(userLoginBindingModel);

            if (isLoginComplete) {
                return new ModelAndView("redirect:/home");
            }
        }

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("isLoginError", true);

        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView register() {

        return new ModelAndView("register");

    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isRegisterComplete = userService.registerUser(userRegisterBindingModel);

            if (isRegisterComplete) {
                return new ModelAndView("login");
            }
        }


        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("isRegisterUserError", true);
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        httpSession.invalidate();
        return "redirect:/";
    }

    @ModelAttribute
    UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
