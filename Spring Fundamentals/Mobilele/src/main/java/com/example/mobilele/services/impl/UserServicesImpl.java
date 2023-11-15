package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.UserRegisterBindingModel;
import com.example.mobilele.model.entity.User;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServicesImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {

        Optional<User> user = userRepository.findByUsername(userRegisterBindingModel.getUsername());

        if (user.isEmpty() && userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            User userRegister = modelMapper.map(userRegisterBindingModel, User.class);
            userRegister.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
            userRegister.setCreated(LocalDateTime.now());


            userRepository.save(userRegister);

            return true;
        }

        return false;
    }

//    @Override
//    public boolean login(UserLoginBindingModel userLoginBindingModel) {
//        User user = userRepository.findByUsername(userLoginBindingModel.getUsername());
//
//        if (user != null && user.getPassword().equals(userLoginBindingModel.getPassword())) {
//            loggedUser.setId(user.getId());
//            loggedUser.setUsername(user.getUsername());
//            loggedUser.setFirstName(user.getFirstName());
//            loggedUser.setLastName(user.getLastName());
//            loggedUser.setLogged(true);
//            return true;
//        }
//
//        return false;
//    }
}
