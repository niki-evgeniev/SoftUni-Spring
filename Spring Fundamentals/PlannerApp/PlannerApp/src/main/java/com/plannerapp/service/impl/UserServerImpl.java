package com.plannerapp.service.impl;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.bindingModel.UserLoginBindingModel;
import com.plannerapp.model.bindingModel.UserRegisterBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserServer;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServerImpl(UserRepository userRepository, ModelMapper modelMapper,
                          PasswordEncoder passwordEncoder,
                          LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        if (userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            User user = userRepository.findByUsername(userRegisterBindingModel.getUsername());

            if (user == null) {
                User userRegistration = modelMapper.map(userRegisterBindingModel, User.class);
                userRegistration.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
                userRepository.save(userRegistration);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        User user = userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (user != null) {
            boolean passwordAccept = passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword());

            if (passwordAccept) {
                loggedUser.setId(user.getId());
                loggedUser.setUsername(user.getUsername());
                loggedUser.setEmail(user.getEmail());
                loggedUser.setLogged(true);
                return true;
            }
        }
        return false;
    }
}
