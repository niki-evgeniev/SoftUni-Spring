package com.example.jsonexercises.service.impl;

import com.example.jsonexercises.model.dto.SoldProductDto;
import com.example.jsonexercises.model.dto.UserSeedDto;
import com.example.jsonexercises.model.entity.User;
import com.example.jsonexercises.repository.UserRepository;
import com.example.jsonexercises.service.UserServices;
import com.example.jsonexercises.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.example.jsonexercises.constants.OutPutMessages.*;
import static com.example.jsonexercises.constants.Paths.JSON_FILE_USER;
import static com.example.jsonexercises.constants.Paths.PATH_JSON_INPUT;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public UserServicesImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;

    }

    @Override
    public void seedUsers() throws FileNotFoundException {

        if (userRepository.count() > 0) {
            System.out.println(JSON_IS_ALREADY_LOADED + USER_DB);
            return;
        }

        FileReader fileReader = new FileReader(Path.of(PATH_JSON_INPUT + JSON_FILE_USER).toFile());

        UserSeedDto[] userSeedDtos = gson.fromJson(fileReader, UserSeedDto[].class);

        List<User> usersToSave = Arrays.stream(userSeedDtos).filter(validationUtil::isValid)
                .map(UserSeedDto -> modelMapper.map(UserSeedDto, User.class))
                .collect(Collectors.toList());

        userRepository.saveAll(usersToSave);
        System.out.println(SUCCESSFUL_ADDED + USER_DB);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1, userRepository.count());
        return userRepository.findById(randomId)
                .orElse(null);
    }

    @Override
    public List<SoldProductDto> findAllUserWithMoreThenOneSoldProduct() {

        return userRepository.findAllUsersMoreThenOneSoldProducts()
                .stream()
                .map(user -> modelMapper.map(user, SoldProductDto.class))
                .collect(Collectors.toList());
    }
}
