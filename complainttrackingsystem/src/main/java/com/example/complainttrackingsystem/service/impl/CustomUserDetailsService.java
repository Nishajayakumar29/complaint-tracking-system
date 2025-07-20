package com.example.complainttrackingsystem.service.impl;

import com.example.complainttrackingsystem.model.User;
import com.example.complainttrackingsystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository a;

    // Spring Security calls this to load user during authentication
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = a.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found: " + email);

        // Create a UserDetails object with username, password, and role
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );

    }
}
