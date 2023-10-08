package com.example.pathfinder.services.impl;

import com.example.pathfinder.models.DTO.UserRegisterDTO;
import com.example.pathfinder.models.entity.User;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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

        System.out.println();

    }
}
