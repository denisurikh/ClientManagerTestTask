package com.example.clientmanagertesttask.repository;

import com.example.clientmanagertesttask.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
public Role findRoleById(Integer id);
public Role findRoleByRoleName(String roleName);
public Boolean existsRoleByRoleName(String roleName);
}