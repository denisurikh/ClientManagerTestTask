package com.example.clientmanagertesttask.service;

import com.example.clientmanagertesttask.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    List<User> readAll();

    User read(String login);

    void update(User user);

    void delete(String login);
}
