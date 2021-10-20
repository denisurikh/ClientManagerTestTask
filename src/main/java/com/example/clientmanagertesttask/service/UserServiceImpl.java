package com.example.clientmanagertesttask.service;

import com.example.clientmanagertesttask.model.Role;
import com.example.clientmanagertesttask.model.User;
import com.example.clientmanagertesttask.repository.RoleRepository;
import com.example.clientmanagertesttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public void create(User user) {
        for (Role role:user.getRoles()) {
            if(roleRepository.existsRoleByRoleName(role.getRoleName())) {
                role.setId(roleRepository.findRoleByRoleName(role.getRoleName()).getId());
            }
        }
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(String login) {
        return userRepository.getById(login);
    }

    @Override
    public boolean update(User user) {
        return userRepository.save(user).equals(user) ;
    }

    @Override
    public boolean delete(String login) {
        userRepository.deleteById(login);
        return true;
    }
}
