package com.example.clientmanagertesttask.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Role {
    @EqualsAndHashCode.Exclude
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ROLE_NAME", nullable = false, unique = true, length = 70)

    private String roleName;

    protected Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
