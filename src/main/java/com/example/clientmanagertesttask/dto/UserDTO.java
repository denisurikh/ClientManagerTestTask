package com.example.clientmanagertesttask.dto;

import lombok.Data;

import java.util.Set;
@Data
public class UserDTO {

    private String login;

    private String name;

    private String password;

    private Set<String> roles;
}
