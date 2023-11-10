package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

   @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

   @GetMapping("error")
   public ModelAndView error(Authentication authentication) {
       ModelAndView mav = new ModelAndView();
       if (authentication != null && authentication.isAuthenticated()) {
           String username = authentication.getName();
           mav.addObject("username", username);
       }
       String errorMessage = "You are not authorized for the requested data.";
       mav.addObject("errorMsg", errorMessage);
       mav.setViewName("403");
       return mav;
   }
}
