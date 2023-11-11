package com.resellerapp.service.impl;

import com.resellerapp.model.BindingModel.UserLoginBindingModel;
import com.resellerapp.model.BindingModel.UserRegisterBindingModel;
import com.resellerapp.model.LoggedUser;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        User user = userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (user != null){
            boolean isPasswordMatch =  passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword());

            if (isPasswordMatch){
                loggedUser.setId(user.getId());
                loggedUser.setUsername(user.getUsername());
                loggedUser.setEmail(user.getEmail());
                loggedUser.setLogged(true);
                return true;

            }

        }


        return false;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {

       User user = userRepository.findByUsername(userRegisterBindingModel.getUsername());

       if (user == null && userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
           User registerUser = modelMapper.map(userRegisterBindingModel, User.class);

          registerUser.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

           userRepository.save(registerUser);
           return true;
       }

        return false;
    }


}
