package com.example.complaint_tracking_system.service;

import com.example.complaint_tracking_system.model.User;
import com.example.complaint_tracking_system.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Save a new user or admin
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Check if email already exists
     */
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Find user by email
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find user by email and role
     */
    public User findByEmailAndRole(String email, String role) {
        return userRepository.findByEmailAndRole(email, role);
    }
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
}
}
