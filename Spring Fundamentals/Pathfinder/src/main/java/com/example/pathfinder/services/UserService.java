package com.example.pathfinder.services;

import com.example.pathfinder.models.DTO.UserLoginDTO;
import com.example.pathfinder.models.DTO.UserRegisterDTO;
import com.example.pathfinder.models.entity.User;

public interface UserService {

    boolean areImported();

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO) ;

    void logout();

    User getLoggedUser();
}
