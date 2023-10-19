package com.nnk.springboot;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController loginController;

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
    public void testError() {
        ModelAndView expectedModelAndView = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        expectedModelAndView.addObject("errorMsg", errorMessage);
        expectedModelAndView.setViewName("403");
        ModelAndView modelAndView = loginController.error();
        assertEquals(expectedModelAndView.getViewName(), modelAndView.getViewName());
        assertEquals(expectedModelAndView.getModel().get("errorMsg"), modelAndView.getModel().get("errorMsg"));
    }
}
