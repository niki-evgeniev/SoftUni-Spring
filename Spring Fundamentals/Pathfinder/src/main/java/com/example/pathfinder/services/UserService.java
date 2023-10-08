package com.example.pathfinder.services;

import com.example.pathfinder.models.DTO.UserRegisterDTO;

public interface UserService {

    boolean areImported();

    void register(UserRegisterDTO userRegisterDTO);
}
