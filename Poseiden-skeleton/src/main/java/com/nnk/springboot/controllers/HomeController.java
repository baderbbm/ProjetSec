package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Le contrôleur HomeController gère les requêtes liées à la page d'accueil et à
 * la redirection des utilisateurs.
 */
@Controller
public class HomeController {

	/**
	 * Gère la requête d'accueil "/" et redirige les utilisateurs en fonction de
	 * leur rôle.
	 *
	 * @param model          Le modèle de données pour la vue.
	 * @param authentication L'objet d'authentification de l'utilisateur.
	 * @return Une chaîne représentant la vue vers laquelle rediriger l'utilisateur.
	 */
	@RequestMapping("/")
	public String home(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
				return "redirect:/admin/home";
			} else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
				return "redirect:/user/home";
			}
		}
		return "redirect:/login";
	}

	/**
	 * Gère la requête "/admin/home" et redirige les administrateurs vers la liste
	 * des utilisateurs.
	 *
	 * @param model Le modèle de données pour la vue.
	 * @return Une chaîne représentant la vue vers laquelle rediriger
	 *         l'administrateur.
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/user/list";
	}

	/**
	 * Gère la requête "/user/home" et redirige les utilisateurs vers la liste des
	 * offres.
	 *
	 * @param model Le modèle de données pour la vue.
	 * @return Une chaîne représentant la vue vers laquelle rediriger l'utilisateur.
	 */
	@RequestMapping("/user/home")
	public String userHome(Model model) {
		return "redirect:/bidList/list";
	}

	/**
	 * Gère la requête GET "/login" et renvoie la vue de la page de connexion.
	 *
	 * @return Une chaîne représentant la vue de la page de connexion.
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Gère la requête GET "/sessionExpired" et renvoie la vue de la page d'expiration de session.
	 *
	 * @return Une chaîne représentant la vue de la page d'expiration de session.
	 */
	@GetMapping("/sessionExpired")
	public String sessionExpired() {
		return "sessionExpired";
	}
}
