package com.example.clientmanagertesttask.controllers;

import com.example.clientmanagertesttask.dto.UserDTO;
import com.example.clientmanagertesttask.model.User;
import com.example.clientmanagertesttask.response.StatusJsonResponse;
import com.example.clientmanagertesttask.service.PasswordValidator;
import com.example.clientmanagertesttask.service.UserService;
import com.example.clientmanagertesttask.utils.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserService userService;
    MessageSource messageSource;


    @Autowired
    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping(value = "/users")
        public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        return getResponseEntity(userDTO);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> read() {
        final List<User> userList = userService.readAll();
        final List<UserDTO> userDtoList = userList.stream().map(Mapping::mapUserToUserDtoWithoutRoles).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{login}")
    public ResponseEntity<?> read(@PathVariable(name = "login") String login) {
            User user = userService.read(login);
            return new ResponseEntity<>(Mapping.mapUserToUserDto(user), HttpStatus.OK);
    }

    @PutMapping(value = "/users/")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        return getResponseEntity(userDTO);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody UserDTO userDTO) {
        StatusJsonResponse response = new StatusJsonResponse();

        if (userDTO.getName().isBlank()) {
            response.addError(getMessage("user.name.nonempty"));
        }

        if (userDTO.getLogin().isBlank()) {
            response.addError(getMessage("user.login.nonempty"));
        }

        if (userDTO.getPassword().isBlank()){
            response.addError(getMessage("user.password.nonempty"));
        }

        if (!new PasswordValidator().test(userDTO.getPassword())){
            response.addError(getMessage("user.password.validation"));
        }

        if (response.isSuccess()) {
            try {
                userService.create(Mapping.mapUserDtoToUser(userDTO));
            }
            catch (Exception e) {
                response.addError(getMessage("db.access.error"));
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{login}")
    public ResponseEntity<?> delete(@PathVariable(name = "login") String login) {
        userService.delete(login);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

}
