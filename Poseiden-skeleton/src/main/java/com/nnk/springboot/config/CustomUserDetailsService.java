package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;

/**
 * CustomUserDetailsService implémente l'interface UserDetailsService de Spring Security
 * pour personnaliser la récupération des détails de l'utilisateur à partir du référentiel.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructeur de CustomUserDetailsService.
     *
     * @param userRepository Le référentiel d'utilisateurs utilisé pour récupérer les détails de l'utilisateur.
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Charge les détails de l'utilisateur en utilisant son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur de l'utilisateur à charger.
     * @return Les détails de l'utilisateur correspondant.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable avec ce nom d'utilisateur : " + username);
        }
        return new CustomUserDetails(user);
    }
}
