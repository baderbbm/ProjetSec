package com.nnk.springboot;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testHome() {
        String viewName = userController.home(model);
        assertEquals("user/list", viewName);
        verify(model, times(1)).addAttribute("users", userService.getAllUsers());
    }

    @Test
    public void testAddUser() {
        String viewName = userController.addUser(new User());
        assertEquals("user/add", viewName);
    }


    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = userController.validate(Mockito.mock(User.class), bindingResult, model);
        assertEquals("user/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password");
        user.setPassword(encodedPassword);
        String viewName = userController.validate(user, bindingResult, model);
        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).saveUser(user);
        verify(model, times(1)).addAttribute("users", userService.getAllUsers());
    }

    @Test
    public void testShowUpdateForm() {
        Integer userId = 1;
        User mockUser = Mockito.mock(User.class);
        when(userService.getUserById(userId)).thenReturn(mockUser);
        Model model = Mockito.mock(Model.class);
        String viewName = userController.showUpdateForm(userId, model);
        assertEquals("user/update", viewName);
        verify(userService, times(1)).getUserById(userId);
        verify(model, times(1)).addAttribute("user", mockUser);
    }

    @Test
    public void testUpdateUserWithErrors() {
        Integer userId = 1;
        User mockUser = Mockito.mock(User.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        Model model = Mockito.mock(Model.class);
        String viewName = userController.updateUser(userId, mockUser, bindingResult, model);
        assertEquals("user/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(userService, never()).updateUser(mockUser);
    }

    @Test
    public void testUpdateUserWithoutErrors() {
        Integer userId = 1;
        User mockUser = Mockito.mock(User.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        Model model = Mockito.mock(Model.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password");
        when(mockUser.getPassword()).thenReturn(encodedPassword);
        String viewName = userController.updateUser(userId, mockUser, bindingResult, model);
        assertEquals("redirect:/user/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(userService, times(1)).updateUser(mockUser);
        verify(model, times(1)).addAttribute("users", userService.getAllUsers());
    }


    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);
        String viewName = userController.deleteUser(userId, model);
        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).deleteUserById(userId);
        verify(model, times(1)).addAttribute("users", userService.getAllUsers());
    }
}

