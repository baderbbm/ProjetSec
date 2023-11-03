package com.nnk.springboot.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CustomUserDetails étend la classe User de Spring Security pour personnaliser le stockage des détails de l'utilisateur.
 */
public class CustomUserDetails extends User {

    /**
     * Constructeur de CustomUserDetails.
     *
     * @param user L'objet utilisateur de l'application.
     */
    public CustomUserDetails(com.nnk.springboot.domain.User user) {
        super(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    /**
     * Cette méthode convertit les rôles de l'utilisateur en autorités accordées.
     *
     * @param roles Les rôles de l'utilisateur, séparés par des virgules.
     * @return Une collection d'autorités accordées pour l'utilisateur.
     */
    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles) {
        List<String> roleList = Arrays.asList(roles.split("\\s*,\\s*")); // Divise les rôles par des virgules
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
