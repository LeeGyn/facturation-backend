package com.etslyam.facturationbackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    public static boolean isDate(String input, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean containsDigits(String input) {
        return input.matches(".*\\d.*");
    }

    public static boolean isInteger(String input) {
        return input.matches("^[-+]?\\d+$");
    }

    public static boolean isDouble(String input) {
        return input.matches("^[-+]?\\d*\\.\\d+$");
    }

    public static boolean isEmail(String input) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
