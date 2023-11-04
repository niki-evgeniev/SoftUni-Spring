package com.plannerapp.controller;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.bindingModel.UserLoginBindingModel;
import com.plannerapp.model.bindingModel.UserRegisterBindingModel;
import com.plannerapp.service.UserServer;
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

    private final UserServer userServer;
    private final LoggedUser loggedUser;

    public UserController(UserServer userServer, LoggedUser loggedUser) {
        this.userServer = userServer;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public ModelAndView login() {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("/login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isLoginUser = userServer.login(userLoginBindingModel);

            if (isLoginUser) {
                return new ModelAndView("redirect:/");
            }
        }

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("isLoginError", true);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("register");
    }

    @PostMapping("register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isRegisterUser = userServer.registerUser(userRegisterBindingModel);

            if (isRegisterUser) {
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
