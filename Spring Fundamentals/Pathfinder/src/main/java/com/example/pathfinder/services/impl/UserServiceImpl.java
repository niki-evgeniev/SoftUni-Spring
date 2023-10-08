package com.example.pathfinder.services.impl;

import com.example.pathfinder.models.DTO.UserLoginDTO;
import com.example.pathfinder.models.DTO.UserRegisterDTO;
import com.example.pathfinder.models.entity.User;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.services.UserService;
import com.example.pathfinder.services.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User map = modelMapper.map(userRegisterDTO, User.class);
        map.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(map);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        String username = userLoginDTO.getUsername();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User " + username + " is not exist");
        }

        boolean passwordCorrect = passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
        if (!passwordCorrect) {
            throw new IllegalArgumentException("Incorrect password");
        }

        loggedUser.setUsername(user.getUsername());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setFullName(user.getFullName());
        loggedUser.setLogged(true);


        return passwordCorrect;
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public User getLoggedUser() {
        return userRepository.findByUsername(loggedUser.getUsername());
    }
}
