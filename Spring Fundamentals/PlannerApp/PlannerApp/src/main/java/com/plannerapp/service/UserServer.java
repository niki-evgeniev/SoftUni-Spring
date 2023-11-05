package com.plannerapp.service;

import com.plannerapp.model.bindingModel.UserLoginBindingModel;
import com.plannerapp.model.bindingModel.UserRegisterBindingModel;

public interface UserServer {

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

}
