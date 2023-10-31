package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.binding.OrderBindingModel;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.LoggedUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.view.OrderViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, LoggedUser loggedUser,
                            UserService userService, CategoryService categoryService,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOrder(OrderBindingModel orderBindingModel) {
        Order order = modelMapper.map(orderBindingModel, Order.class);
        order.setEmployee(userService.findById(loggedUser.getId()));
        order.setCategory(categoryService.findByCategoryName(orderBindingModel.getCategory()));

        orderRepository.save(order);

    }

    @Override
    public List<OrderViewModel> findAllOrderByPriceDesc() {


        return orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
