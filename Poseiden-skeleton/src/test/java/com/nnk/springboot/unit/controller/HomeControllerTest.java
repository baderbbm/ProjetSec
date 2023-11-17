package com.nnk.springboot.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import com.nnk.springboot.controllers.HomeController;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

    private HomeController homeController;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController();
    }

    @Test
    public void testHomeAuthenticatedAsAdmin() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        User user = new User("admin", "password",
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        when(authentication.getPrincipal()).thenReturn(user);
        String viewName = homeController.home(model, authentication);
        assertEquals("redirect:/admin/home", viewName);
    }

    @Test
    public void testHomeAuthenticatedAsUser() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        User user = new User("user", "password",
            List.of(new SimpleGrantedAuthority("ROLE_USER")));
        when(authentication.getPrincipal()).thenReturn(user);
        String viewName = homeController.home(model, authentication);
        assertEquals("redirect:/user/home", viewName);
    }

    @Test
    public void testHomeNotAuthenticated() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        String viewName = homeController.home(model, authentication);
        assertEquals("redirect:/login", viewName);
    }

    @Test
    public void testHomeAuthenticatedWithUnknownRole() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        User user = new User("unknown", "password",
            List.of(new SimpleGrantedAuthority("ROLE_UNKNOWN")));
        when(authentication.getPrincipal()).thenReturn(user);
        String viewName = homeController.home(model, authentication);
        assertEquals("redirect:/login", viewName);
    }

    @Test
    public void testAdminHome() {
        String viewName = homeController.adminHome(model);
        assertEquals("redirect:/user/list", viewName);
    }
}