package com.example.clientmanagertesttask.utils;

import com.example.clientmanagertesttask.dto.UserDTO;
import com.example.clientmanagertesttask.model.Role;
import com.example.clientmanagertesttask.model.User;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapping {

    public static User mapUserDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles().stream().map(Role::new).collect(Collectors.toSet()));
        return user;
    }

    public static UserDTO mapUserToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));
        return userDTO;
    }

    public static UserDTO mapUserToUserDtoWithoutRoles(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(Collections.EMPTY_SET);
        return userDTO;
    }
}
