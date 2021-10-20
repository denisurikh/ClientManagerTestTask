package com.example.clientmanagertesttask.repository;

import com.example.clientmanagertesttask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
