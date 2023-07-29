package com.example.xmlexercise.Services.impl;

import com.example.xmlexercise.Repository.UserRepository;
import com.example.xmlexercise.Services.UserServices;
import com.example.xmlexercise.model.Dto.UsersSeedDto;
import com.example.xmlexercise.model.entity.User;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServicesImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Long usersCountRepository() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UsersSeedDto> users) {

        users
                .stream()
                .filter(validationUtil::isValid)
                .map(usersSeedDto -> modelMapper.map(usersSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {

        long randomId = ThreadLocalRandom.current()
                .nextLong(1, userRepository.count() + 1);


        return userRepository.findById(randomId)
                .orElse(null);
    }
}
