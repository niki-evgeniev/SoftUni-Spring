package com.example.xmlexercise.Services;

import com.example.xmlexercise.model.Dto.UsersSeedDto;
import com.example.xmlexercise.model.entity.User;

import java.util.List;

public interface UserServices {

    Long usersCountRepository();

    void seedUsers(List<UsersSeedDto> users);

    User getRandomUser();
}
