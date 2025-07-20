package com.example.complainttrackingsystem.controller;

import com.example.complainttrackingsystem.dto.UserSignupRequest;
import com.example.complainttrackingsystem.service.UserService;
import com.example.complainttrackingsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 👇 Show signup form
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // Loads signup.html from templates
    }

    // 👇 Handle form submission
    @PostMapping("/auth/form-signup")
    public String handleFormSignup(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String role,
                                   Model model) {

        // Check if email already exists
        if (userService.findByEmail(email) != null) {
            model.addAttribute("error", "User already exists!");
            return "signup"; // reload the form with error
        }

        // Create and save user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userService.saveUser(user);

        model.addAttribute("message", "Signup successful!");
        return "redirect:/signup-success"; // redirect or show success message
    }

    // Optional: show success page
    @GetMapping("/signup-success")
    public String showSuccessPage() {
        return "signup-success"; // you can create this HTML later
    }
}
