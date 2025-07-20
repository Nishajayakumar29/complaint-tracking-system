package com.example.complainttrackingsystem.service.impl;

import com.example.complainttrackingsystem.service.OtpService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpServiceImpl implements OtpService {
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    @Override
    public void saveOtp(String email, String otp) {
        otpStore.put(email, otp);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStore.get(email);
        return storedOtp != null && storedOtp.equals(otp);
    }

    @Override
    public String generateOtp(String email) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        saveOtp(email, otp);
        return otp;
    }
}
