package com.example.clientmanagertesttask.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")

public class User {

    @Id
    @Column(name = "LOGIN", nullable = false, unique = true, length = 70)
    private String login;
    @Column(name = "NAME", length = 70)
    private String name;
    @Column(name = "PASSWORD", length = 70)
    private String password;
    @JoinTable(name = "USER_ROLE_LINK",
            joinColumns = @JoinColumn(name = "USER_LOGIN"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.JOIN)
    private Set<Role> roles;
}
