package com.example.clientmanagertesttask.response;

import java.util.HashSet;
import java.util.Set;

public class UsersResponse {
    private boolean success = true;
    private final Set<String> errors= new HashSet<>();

    public boolean isSuccess() {
        return success;
    }

    public void addError(String error) {
        success = false;
        errors.add(error);
    }

    public Set<String> getErrors() {
        return errors;
    }
}
