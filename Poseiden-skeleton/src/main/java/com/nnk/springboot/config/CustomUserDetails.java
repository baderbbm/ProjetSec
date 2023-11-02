package com.nnk.springboot.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends User {

    public CustomUserDetails(com.nnk.springboot.domain.User user) {
        super(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
        System.out.println("username "+ user.getUsername());
        System.out.println("pass"+ user.getPassword());
    	System.out.println("role "+ user.getRole());
    }

    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles) {
        List<String> roleList = Arrays.asList(roles.split("\\s*,\\s*")); // Divise les rôles par des virgules
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}