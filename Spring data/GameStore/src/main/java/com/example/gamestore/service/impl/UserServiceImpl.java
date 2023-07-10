package com.example.gamestore.service.impl;

import com.example.gamestore.model.dto.UserLoginDto;
import com.example.gamestore.model.dto.UserRegisterDto;
import com.example.gamestore.model.entity.User;
import com.example.gamestore.repository.UserRepository;
import com.example.gamestore.service.UserService;
import com.example.gamestore.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loginUserSuccessful;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto , User.class);
        userRepository.save(user);
        System.out.println(userRegisterDto.getFullName() + " was registered");

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.violation(userLoginDto);

        if (!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

       User user = userRepository.findAllByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
               .orElse(null);

        if(user == null){
            System.out.println("Incorrect username / password");
            return;
        }

        loginUserSuccessful = user;
        System.out.println("Successfully logged in " + user.getFullName());

    }

    @Override
    public void logOut() {
        if(loginUserSuccessful == null){
            System.out.println("Cannot log out. No user was logged in.");
        }else {
            loginUserSuccessful = null;
            System.out.println("Successfully logout");
        }
    }
}
