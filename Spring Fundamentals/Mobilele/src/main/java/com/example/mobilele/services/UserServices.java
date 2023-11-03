package com.example.mobilele.services;

import com.example.mobilele.model.DTO.UserLoginBindingModel;
import com.example.mobilele.model.DTO.UserRegisterBindingModel;

public interface UserServices {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);
}
