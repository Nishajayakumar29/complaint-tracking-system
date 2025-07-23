package com.example.complaint_tracking_system.util;

import java.security.SecureRandom;

public class OtpGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    public static String generateOtp() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int digit = random.nextInt(10); // 0 to 9
            sb.append(digit);
        }

        return sb.toString();
    }
}
