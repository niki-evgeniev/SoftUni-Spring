package com.resellerapp.service;

import com.resellerapp.model.BindingModel.UserLoginBindingModel;
import com.resellerapp.model.BindingModel.UserRegisterBindingModel;

public interface UserService {

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);
}
