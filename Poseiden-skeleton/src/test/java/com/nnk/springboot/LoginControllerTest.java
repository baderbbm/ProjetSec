package com.nnk.springboot;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController loginController;
    
    @Mock
    private Authentication mockAuthentication;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController(userRepository);
    }

    @Test
    public void testLogin() {
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.setViewName("login");
        ModelAndView modelAndView = loginController.login();
        assertEquals(expectedModelAndView.getViewName(), modelAndView.getViewName());
    }

    @Test
    public void testGetAllUserArticles() {
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.addObject("users", userRepository.findAll());
        expectedModelAndView.setViewName("user/list");
        when(userRepository.findAll()).thenReturn(null); // Replace with actual data if needed
        ModelAndView modelAndView = loginController.getAllUserArticles();
        assertEquals(expectedModelAndView.getViewName(), modelAndView.getViewName());
    }

    @Test
    void testErrorWithAuthentication() {
        when(mockAuthentication.getName()).thenReturn("user");
        when(mockAuthentication.isAuthenticated()).thenReturn(true);
        ModelAndView modelAndView = loginController.error(mockAuthentication);
        assertEquals("403", modelAndView.getViewName());
        assertEquals("You are not authorized for the requested data.", modelAndView.getModel().get("errorMsg"));
        assertEquals("user", modelAndView.getModel().get("username"));
    }

    @Test
    void testErrorWithoutAuthentication() {
        when(mockAuthentication.isAuthenticated()).thenReturn(false);
        ModelAndView modelAndView = loginController.error(mockAuthentication);
        assertEquals("403", modelAndView.getViewName());
        assertEquals("You are not authorized for the requested data.", modelAndView.getModel().get("errorMsg"));
        assertEquals(null, modelAndView.getModel().get("username"));
    }
}
