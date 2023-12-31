package com.etslyam.facturationbackend.utils;

import java.security.SecureRandom;
import java.time.Instant;

public class CodeGenerator {
    static String hubCode = Instant.now().toString();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789%/_$^";
    private static final int CODE_LENGTH = 9;

    public static String generateUniqueCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        SecureRandom random = new SecureRandom();

        while (sb.length() < CODE_LENGTH) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);

            if (sb.indexOf(String.valueOf(randomChar)) == -1) {
                sb.append(randomChar);
            }
        }

        return sb.toString();
    }
}
