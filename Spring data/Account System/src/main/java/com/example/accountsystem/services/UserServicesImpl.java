package com.example.accountsystem.services;

import com.example.accountsystem.entity.User;
import com.example.accountsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
        System.out.println("Success");
    }
}
