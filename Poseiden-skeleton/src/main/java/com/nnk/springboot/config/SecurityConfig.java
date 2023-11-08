package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * La classe SecurityConfig configure la sécurité de l'application en utilisant Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {	
	
    private final CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    
    /**
     * Configure un encodeur de mot de passe BCrypt.
     *
     * @return Un bean BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    /**
     * Définit l'enchaînement des filtres de sécurité appliqués aux demandes entrantes dans l'application.
     *
     * @param http La configuration HttpSecurity pour définir les règles de sécurité.
     * @return Un SecurityFilterChain configuré.
     * @throws Exception En cas d'erreur lors de la configuration de la sécurité.
     */
     
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests()
            .requestMatchers("/login", "/sessionExpired").permitAll()
            .requestMatchers("/admin/home", "/user/list", "/user/add", "/user/update/**", "/user/delete/**").hasRole("ADMIN")
            .requestMatchers("/user/home", "bidList/list", "curvePoint/list", "rating/list", "trade/list", "ruleName/list").hasRole("USER")
            .anyRequest().authenticated();
        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/process-login")
            .defaultSuccessUrl("/", true) // Redirige vers la page d'accueil ("/") après la connexion
            .failureUrl("/login?error=true")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/app-logout") // Configure l'URL de déconnexion
            .logoutSuccessUrl("/login") // Redirection après la déconnexion
            .invalidateHttpSession(true) // Invalide la session après la déconnexion
            .deleteCookies("JSESSIONID") // Supprime les cookies après la déconnexion
            .permitAll()
            .and()
            .csrf()
            .disable()
            .exceptionHandling()
            .accessDeniedPage("/app/error");// Redirige vers la page d'erreur en cas d'accès refusé

        	http
        	.sessionManagement()
        	.invalidSessionStrategy(new SimpleRedirectInvalidSessionStrategy("/sessionExpired"))// Redirection en cas de session expirée
        	.maximumSessions(1) // Nombre maximum de sessions autorisées
        	.maxSessionsPreventsLogin(true);// Empêcher l'utilisateur de se connecter s'il dépasse le nombre maximal de sessions

            http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // La session sera créée uniquement si nécessaire
            .and()
            .sessionManagement()
            .enableSessionUrlRewriting(false); // Empêchez l'ajout automatique de l'identifiant de session aux URL
                
        return http.build();
    }
}
