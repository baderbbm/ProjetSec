package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import  com.nnk.springboot.repositories.UserRepository;
import  com.nnk.springboot.domain.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
    	System.out.println("trouve username "+ username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable avec cet nom d'utilisateur : " + username);
        }
        return new CustomUserDetails(user);
    }
}

