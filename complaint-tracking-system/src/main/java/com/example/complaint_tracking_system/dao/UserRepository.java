package com.example.complaint_tracking_system.dao;

import com.example.complaint_tracking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    User findByEmail(String email);

    // Find user by email and role
    User findByEmailAndRole(String email, String role);

    // Check if user already exists
    boolean existsByEmail(String email);
}
