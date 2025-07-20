package com.example.complainttrackingsystem.controller;

import com.example.complainttrackingsystem.dto.LoginRequest;
import com.example.complainttrackingsystem.dto.UserSignupRequest;
import com.example.complainttrackingsystem.model.User;
import com.example.complainttrackingsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Register new users
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequest signupRequest) {
        // Check if email already exists
        if (userService.findByEmail(signupRequest.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User with this email already exists");
        }

        // Create new user with dynamic role
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole().toUpperCase()); // <-- ✅ MAIN CHANGE

        // Save user
        User savedUser = userService.saveUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully with ID: " + savedUser.getId());
    }


    // ✅ You will now handle login using OTP via a separate OtpController
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Login via OTP. Please request OTP first.");
    }
    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok(user.getRole());  // return role like "ADMIN" or "USER"
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}