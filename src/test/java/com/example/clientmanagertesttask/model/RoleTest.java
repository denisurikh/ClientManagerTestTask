package com.example.clientmanagertesttask.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testEquals() {
        Role role1 = new Role();
        role1.setId(1);
        role1.setRoleName("Name");

        Role role2 = new Role();
        role2.setId(2);
        role2.setRoleName("Name");

        Role role3 = new Role();
        role3.setId(1);
        role3.setRoleName("ID");

        assertNotEquals(role1, role3);

        assertEquals(role1, role2);
    }

    @Test
    void testHashCode() {
        Role role1 = new Role();
        role1.setId(1);
        role1.setRoleName("Name");

        Role role2 = new Role();
        role2.setId(2);
        role2.setRoleName("Name");

        Role role3 = new Role();
        role3.setId(1);
        role3.setRoleName("ID");

        assertNotEquals(role1.hashCode(), role3.hashCode());
        assertNotEquals(role2.hashCode(), role3.hashCode());

        assertEquals(role1.hashCode(), role2.hashCode());
    }
}