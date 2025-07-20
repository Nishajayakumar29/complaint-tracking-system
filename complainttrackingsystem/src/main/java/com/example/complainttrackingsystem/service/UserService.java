package com.example.complainttrackingsystem.service;

import com.example.complainttrackingsystem.model.User;
import java.util.List;

// Interface to define user-related operations
public interface UserService {

    // INSERT/UPDATE: Save a new user or update existing user
    User saveUser(User user);

    // SELECT: Fetch a user by ID
    User getUserById(Long id);

    // SELECT: Fetch all users
    List<User> getAllUsers();
    

    // DELETE: Delete a user by ID
    void deleteUser(Long id);

    // SELECT: Find user by email (used in login)
    User findByEmail(String email);
    
}
