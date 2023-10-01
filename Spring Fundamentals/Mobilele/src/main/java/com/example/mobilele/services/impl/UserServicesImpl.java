package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.UserRegistrationDTO;
import com.example.mobilele.repository.UserRepository;

import com.example.mobilele.services.UserServices;


public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
//        userRepository.save(map(userRegistrationDTO) );
    }
}
