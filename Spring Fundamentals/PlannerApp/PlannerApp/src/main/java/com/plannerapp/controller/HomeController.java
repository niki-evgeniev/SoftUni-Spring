package com.plannerapp.controller;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.view.TaskHomeViewModel;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }


    @GetMapping("/")
    public ModelAndView index() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("index");
        }

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView home() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        TaskHomeViewModel taskHomeViewModel = taskService.getHomeViewInfo();

        return new ModelAndView("home", "tasks", taskHomeViewModel);
    }


}
