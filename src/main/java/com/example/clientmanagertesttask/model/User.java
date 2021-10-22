package com.example.clientmanagertesttask.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(name = "login", nullable = false, unique = true, length = 70)
    private String login;
    @Column(name = "name", length = 70)
    private String name;
    @Column(name = "password", length = 70)
    private String password;
    @JoinTable(name = "user_role_link",
            joinColumns = @JoinColumn(name = "user_role"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.JOIN)
    private Set<Role> roles;
}
