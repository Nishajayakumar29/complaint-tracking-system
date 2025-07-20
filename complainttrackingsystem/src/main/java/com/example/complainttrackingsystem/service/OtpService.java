package com.example.complainttrackingsystem.service;


public interface OtpService {
    void saveOtp(String email, String otp);
    boolean verifyOtp(String email, String otp);
    String generateOtp(String email);  // <-- Add this method
}

