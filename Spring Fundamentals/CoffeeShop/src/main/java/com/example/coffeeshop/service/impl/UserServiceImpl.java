package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.binding.UserLoginBindingModel;
import com.example.coffeeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.LoggedUser;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.view.UserViewModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }


    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = new User();

        Optional<User> byUsername = userRepository.findByUsername(userRegisterBindingModel.getUsername());

        if (byUsername.isEmpty()) {
            user.setUsername(userRegisterBindingModel.getUsername());
            user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
            user.setEmail(userRegisterBindingModel.getEmail());
            user.setFirstName(userRegisterBindingModel.getFirstName());
            user.setLastName(userRegisterBindingModel.getLastName());
            System.out.println();
            userRepository.save(user);
        }
    }

    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) {
        Optional<User> user = userRepository.findByUsername(userLoginBindingModel.getUsername());
        System.out.println();
        loggedUser.setUsername(userLoginBindingModel.getUsername());

        if (user.isPresent() && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.get().getPassword())) {

                loggedUser.setUsername(user.get().getUsername());
                loggedUser.setPassword(user.get().getPassword());
                loggedUser.setId(user.get().getId());
                loggedUser.setLogged(true);
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUserAndCountOrdersDesc() {

        return userRepository.findAllByOrdersByDescending()
                .stream()
                .map( user -> {
                    UserViewModel userViewModel = new UserViewModel();
                    userViewModel.setUsername(user.getUsername());
                    userViewModel.setCountOfOrders(user.getOrders().size());
                    return userViewModel;
                }).collect(Collectors.toList());
    }
}
