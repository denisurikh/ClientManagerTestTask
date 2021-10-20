package com.example.clientmanagertesttask.controllers;

import com.example.clientmanagertesttask.dto.UserDTO;
import com.example.clientmanagertesttask.model.User;
import com.example.clientmanagertesttask.response.UsersResponse;
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
        UsersResponse response = new UsersResponse();

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

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> read() {
        final List<User> userList = userService.readAll();
        final List<UserDTO> userDtoList = userList.stream().map(Mapping::mapUserToUserDtoWithoutRoles).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{login}")
    public ResponseEntity<UserDTO> read(@PathVariable(name = "login") String login) {
        final User user = userService.read(login);

        return user != null
                ? new ResponseEntity<>(Mapping.mapUserToUserDto(user), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/")
    public ResponseEntity<?> update(@RequestBody UserDTO userDto) {

        final boolean updated = userService.update(Mapping.mapUserDtoToUser(userDto));

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{login}")
    public ResponseEntity<?> delete(@PathVariable(name = "login") String login) {
        final boolean deleted = userService.delete(login);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

}
