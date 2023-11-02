package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class HomeController {
	
    @RequestMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            	System.out.println("Je suis dans ADMIN");
                return "redirect:/admin/home"; // Rediriger l'administrateur
            } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            	System.out.println("Je suis dans USER");
                return "redirect:/user/home"; // Rediriger l'utilisateur
            }
        }
        return "redirect:/login"; // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté ou si son rôle n'est pas USER ou ADMIN 
    }
    
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		// return "redirect:/bidList/list";
		return "redirect:/user/list";
	}

	// ajouter
	@RequestMapping("/user/home")
	public String userHome(Model model) {
		return "redirect:/bidList/list";
	}
	
	// Affiche la page de connexion
	@GetMapping("/login")
	public String login() {
		return "login";
	}	
}
