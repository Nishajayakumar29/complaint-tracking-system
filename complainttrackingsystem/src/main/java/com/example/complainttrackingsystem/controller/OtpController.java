
package com.example.complainttrackingsystem.controller;

import com.example.complainttrackingsystem.model.User;
import com.example.complainttrackingsystem.dao.UserRepository;

import com.example.complainttrackingsystem.service.OtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin(origins = "*")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Send OTP to the email
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        String otp = otpService.generateOtp(email);
        return ResponseEntity.ok("Your OTP is: " + otp);
    }

    // Verify OTP sent to email
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body("Email and OTP must be provided");
        }

        boolean isVerified = otpService.verifyOtp(email, otp);

        if (isVerified) {
            return ResponseEntity.ok("OTP Verified Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }

    // Get user role by email and password
    @PostMapping("/get-role")
    public ResponseEntity<String> getRole(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password must be provided");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect password");
        }

        return ResponseEntity.ok(user.getRole());
    }
}