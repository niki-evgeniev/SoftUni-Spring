package com.example.pathfinder.controler;

import com.example.pathfinder.models.DTO.AddRouteDTO;
import com.example.pathfinder.models.entity.CategoriesName;
import com.example.pathfinder.models.entity.LevelEnum;
import com.example.pathfinder.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add")
        public ModelAndView addRoute(){

        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject( "levels",LevelEnum.values());
        modelAndView.addObject( "categories", CategoriesName.values());

            return modelAndView ;
        }

     @PostMapping("/add")
    public ModelAndView addRoute(AddRouteDTO addRouteDTO){

        routeService.addRoute(addRouteDTO);

        return new ModelAndView("redirect:/");
     }


}
