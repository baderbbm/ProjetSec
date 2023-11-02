package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {	
	
    private final CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
// Définir l'enchaînement des filtres de sécurité qui sont appliqués aux demandes entrantes dans l'application. 
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
              //  .requestMatchers("/admin/**").hasRole("ADMIN") 
               // .requestMatchers("/user/**").hasRole("USER")
             .requestMatchers("/user/home").hasRole("USER")
             .requestMatchers("/user/list").hasRole("ADMIN")
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
             //   .logoutSuccessUrl("/login?logout=true")
            .logoutUrl("/app-logout") // Configurez l'URL de déconnexion
            .logoutSuccessUrl("/login") // Redirection après la déconnexion
            .invalidateHttpSession(true) // Invalidate la session après la déconnexion
            .deleteCookies("JSESSIONID") // Supprimez les cookies après la déconnexion
            .permitAll()
            .and()
            .csrf()
            .disable()
             .exceptionHandling()
              .accessDeniedPage("/app/error"); // Redirige vers la page d'erreur en cas d'accès refusé
		    
        return http.build();
    }
}
