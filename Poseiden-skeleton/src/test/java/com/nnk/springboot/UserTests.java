package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userTest() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFullname("Test User");
        user.setRole("USER");

        // Save
        user = userRepository.save(user);
        assertNotNull(user.getId());
        assertEquals("testUser", user.getUsername());

        // Update
        user.setUsername("updatedUser");
        user = userRepository.save(user);
        assertEquals("updatedUser", user.getUsername());

        // Find
        List<User> listResult = userRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> userList = userRepository.findById(id);
        assertFalse(userList.isPresent());
    }
}
