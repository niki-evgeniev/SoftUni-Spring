package com.plannerapp.controller;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.bindingModel.TaskAddBindingModel;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/add-task")
    public ModelAndView addTask() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("task-add");
    }

    @PostMapping("add-task")
    public ModelAndView addTask(@Valid TaskAddBindingModel taskAddBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("task-add");
        }

        taskService.addTask(taskAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.assign(id, loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @ModelAttribute
    public TaskAddBindingModel taskAddBindingModel() {
        return new TaskAddBindingModel();
    }
}