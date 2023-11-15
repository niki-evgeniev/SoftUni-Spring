package com.example.mobilele.services.impl;

import com.example.mobilele.model.entity.User;
import com.example.mobilele.model.enums.RolesType;
import com.example.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

public class MobileleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(MobileleUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));


    }

    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Arrays.stream(user.getRole().getRoles().values()).map(MobileleUserDetailsService::map).toList())
                .build();
    }

    private static GrantedAuthority map(RolesType rolesType) {
        return new SimpleGrantedAuthority("ROLE_" + rolesType.name());

    }
}
