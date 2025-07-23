package com.example.complaint_tracking_system.controller;

import com.example.complaint_tracking_system.model.User;
import com.example.complaint_tracking_system.service.*;
import com.example.complaint_tracking_system.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.complaint_tracking_system.dao.ComplaintRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow frontend access
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ComplaintRepository complaintRepository;

    private Map<String, String> otpMap = new HashMap<>();
    
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
    	System.out.println("Signup received: " + user.getEmail());  
        if (authService.emailExists(user.getEmail())) {
            return "Email already registered!";
        }
        authService.saveUser(user);
        return "Signup successful!";
    }

    @PostMapping("/send-otp")
    public Map<String, String> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String role = request.get("role").toUpperCase();

        User user = authService.findByEmailAndRole(email, role);
        Map<String, String> response = new HashMap<>();

        if (user == null) {
            response.put("status", "error");
            response.put("message", "Invalid email or role");
            return response;
        }

        String otp = OtpGenerator.generateOtp();
        otpMap.put(email, otp);

        response.put("status", "success");
        response.put("otp", otp); // For testing only
        return response;
    }

    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String enteredOtp = request.get("otp");

        String storedOtp = otpMap.get(email);
        Map<String, String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(enteredOtp)) {
            response.put("status", "success");
            response.put("message", "OTP verified");
        } else {
            response.put("status", "error");
            response.put("message", "Incorrect OTP");
        }

        return response;
    }
    
        
    @DeleteMapping("/logout/{email}")
    public String logoutAndDeleteUser(@PathVariable String email) {
        User user = authService.findByEmail(email);
        if (user != null) {
            // Delete complaints of the user
            complaintRepository.deleteAll(complaintRepository.findByUser(user));

            // Then delete the user
            authService.deleteUserByEmail(email);

            return "User and complaints deleted successfully.";
        } else {
            return "User not found.";
        }
    }


}
