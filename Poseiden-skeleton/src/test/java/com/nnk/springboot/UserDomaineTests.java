package com.nnk.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.User;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDomaineTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetFullname() {
        user.setFullname("John Doe");
        assertEquals("John Doe", user.getFullname());
    }

    @Test
    public void testGetRole() {
        user.setRole("Admin");
        assertEquals("Admin", user.getRole());
    }
}
