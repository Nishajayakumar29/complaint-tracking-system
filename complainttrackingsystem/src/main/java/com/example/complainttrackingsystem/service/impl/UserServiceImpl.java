package com.example.complainttrackingsystem.service.impl;

import com.example.complainttrackingsystem.dao.UserRepository;
import com.example.complainttrackingsystem.model.User;
import com.example.complainttrackingsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// This class implements the business logic for user operations
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository a;

    // INSERT/UPDATE: Save or update a user to the database
    @Override
    public User saveUser(User user) {
        return a.save(user); // SQL: INSERT or UPDATE
    }

    // SELECT: Get a single user by ID
    @Override
    public User getUserById(Long id) {
        return a.findById(id).orElse(null); // SQL: SELECT * FROM users WHERE id=?
    }

    // SELECT: Get all users from the database
    @Override
    public List<User> getAllUsers() {
        return a.findAll(); // SQL: SELECT * FROM users
    }

    // DELETE: Remove a user by ID
    @Override
    public void deleteUser(Long id) {
        a.deleteById(id); // SQL: DELETE FROM users WHERE id=?
    }

    // SELECT: Find a user by their email (for login or lookup)
    @Override
    public User findByEmail(String email) {
        return a.findByEmail(email); // SQL: SELECT * FROM users WHERE email=?
    }
}
