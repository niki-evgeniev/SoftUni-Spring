package com.example.jsonexercises.service;

import com.example.jsonexercises.model.dto.SoldProductDto;
import com.example.jsonexercises.model.entity.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserServices {

    void seedUsers() throws FileNotFoundException;

    User getRandomUser();

    List<SoldProductDto> findAllUserWithMoreThenOneSoldProduct();
}
