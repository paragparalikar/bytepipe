package com.bytepipe.common.security;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private final Random random = new SecureRandom();
    private final List<String> charCategories = Arrays.asList(
            "abcdefghijklmnopqrstuvwxyz",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "0123456789",
            "#$@!%&*?"
    );

    public String generate(int length){
        final StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return password.toString();
    }

}
