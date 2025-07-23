package com.example.complaint_tracking_system.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOtpStore {

    // Thread-safe map: email → OTP
    private static final Map<String, String> otpMap = new ConcurrentHashMap<>();

    /**
     * Store OTP for the user's email
     */
    public static void storeOtp(String email, String otp) {
        otpMap.put(email, otp);
    }

    /**
     * Retrieve OTP by email
     */
    public static String getOtp(String email) {
        return otpMap.get(email);
    }

    /**
     * Validate if OTP is correct
     */
    public static boolean isValidOtp(String email, String inputOtp) {
        String storedOtp = otpMap.get(email);
        return storedOtp != null && storedOtp.equals(inputOtp);
    }

    /**
     * Remove OTP after successful verification or expiration
     */
    public static void clearOtp(String email) {
        otpMap.remove(email);
    }
}
