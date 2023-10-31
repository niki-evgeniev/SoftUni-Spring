package com.dictionaryapp.service;


import com.dictionaryapp.model.BindingModel.UserLoginBindingModel;
import com.dictionaryapp.model.BindingModel.UserRegisterBindingModel;

public interface UserService {
    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean loginUser(UserLoginBindingModel userLoginBindingModel);
}
