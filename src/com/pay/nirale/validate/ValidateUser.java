package com.pay.nirale.validate;

import com.pay.nirale.bean.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {

    private static Matcher matcher;
    private static Pattern pattern;
    //Email Pattern
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";
    private static Object moblie;

    public static boolean checkLength(int length, String text, boolean equalLength) {
        if (equalLength) {
            if (text.length() == length && text != null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (text.length() > length && text != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    // Check Valid Email Method
    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    // Verify pin Method 
    public static boolean verifyPin(int pin, User user) {
        if (pin == user.getAccountPin()) {
            return true;
        } else {
            return false;
        }
    }

    // Check is Not Null
    public static boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() > 0 ? true : false;
    }

    // Validating Password with retype Password
    public static boolean validatePassword(String pass) {
        return pass != null && pass.length() > 3 ? true : false;
    }

    // Check Space
    public static boolean haveSpace(String userName) {
        boolean checkSpace = false;
        int f = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (userName.contains(" ")) {
                f = 1;
                checkSpace = true;
            }
        }
        if (f == 1) {
            return checkSpace;
        } else {
            return checkSpace;
        }
    }

    // Maximum Mobile
    public static boolean validateMaxMobile(String mobile) {
        return mobile != null && mobile.length() > 10 ? true : false;
    }

    // Minimum Mobile
    public static boolean validateMinMobile(String mobile) {
        return mobile != null && mobile.length() < 10 ? true : false;
    }

}
