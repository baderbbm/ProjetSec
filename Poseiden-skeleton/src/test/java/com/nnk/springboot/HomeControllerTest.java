package com.nnk.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.nnk.springboot.controllers.HomeController;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testHome() {
        String viewName = homeController.home(model);
        assertEquals("home", viewName);
    }

    @Test
    public void testAdminHome() {
    	String viewName = homeController.adminHome(model);
        assertEquals("redirect:/bidList/list", viewName);
    }
}
