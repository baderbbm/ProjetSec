package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldGetUserById() {
        Integer userId = 1;
        User expectedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.getUserById(userId);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldDeleteUserById() {
        Integer userId = 1;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        userService.deleteUserById(userId);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void shouldGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldUpdateUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        userService.updateUser(user);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(userId);
        });
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteNonExistentUser() {
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUserById(userId);
        });
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void shouldDeleteUserWhenUserExists() {
        Integer userId = 1;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> {
            userService.deleteUserById(userId);
        });
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }
}

