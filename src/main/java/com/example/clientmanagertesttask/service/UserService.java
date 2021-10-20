package com.example.clientmanagertesttask.service;

import com.example.clientmanagertesttask.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    List<User> readAll();

    User read(String login);

    boolean update(User user);

    boolean delete(String login);
}
