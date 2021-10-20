package com.example.clientmanagertesttask.service;

import org.springframework.stereotype.Service;
import java.util.function.Predicate;

@Service
public class PasswordValidator implements Predicate<String> {
    @Override
    public boolean test(String password) {
        String regex = "^.*(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$";
        return password.matches(regex);
    }
}
