package com.smartshop.validation;

import java.util.regex.Pattern;

public class InputValidator {
  // Email validation
    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // local regex
        return Pattern.matches(EMAIL_REGEX, email);
    }

    // Method to validate mobile number
    public static boolean isValidMobile(String mobile) {
        String MOBILE_REGEX = "^[0-9]{10}$"; // local regex
        return Pattern.matches(MOBILE_REGEX, mobile);
    }
   
}