package com.example.coffeeshop.service;

import com.example.coffeeshop.model.binding.UserLoginBindingModel;
import com.example.coffeeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.view.UserViewModel;

import java.util.List;

public interface UserService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    void login(UserLoginBindingModel userLoginBindingModel);

    User findById(Long id);

    List<UserViewModel> findAllUserAndCountOrdersDesc();
}
