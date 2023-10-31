package com.example.coffeeshop.service;

import com.example.coffeeshop.model.binding.OrderBindingModel;
import com.example.coffeeshop.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    void addOrder(OrderBindingModel orderBindingModel);

    List<OrderViewModel> findAllOrderByPriceDesc();

    void readyOrder(Long id);
}
