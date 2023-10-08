package com.example.pathfinder.services.impl;

import com.example.pathfinder.models.DTO.AddRouteDTO;
import com.example.pathfinder.models.entity.Category;
import com.example.pathfinder.models.entity.Route;
import com.example.pathfinder.models.entity.User;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.services.RouteService;
import com.example.pathfinder.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoriesRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;

    public RouteServiceImpl(RouteRepository routeRepository, UserRepository userRepository,
                            CategoryRepository categoriesRepository, ModelMapper modelMapper,
                            UserService userService) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addRoute(AddRouteDTO addRouteDTO) {

        Route map = modelMapper.map(addRouteDTO, Route.class);
        map.getCategories().clear();
        Set<Category> categories = categoriesRepository.findByNameIn(addRouteDTO.getCategories());
        map.addCategories(categories);

        User user = userService.getLoggedUser();
        map.setAuthor(user);

        routeRepository.save(map);

    }
}
