package com.example.clientmanagertesttask.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @EqualsAndHashCode.Exclude
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name", nullable = false, unique = true, length = 70)

    private String roleName;

    protected Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
