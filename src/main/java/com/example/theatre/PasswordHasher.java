package com.example.theatre;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_LENGTH = 16;
    private static final String HASH_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        try {
            // Генерация соли
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashBase64 = Base64.getEncoder().encodeToString(hashedPassword);

            return HASH_ALGORITHM + "$" + saltBase64 + "$" + hashBase64;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verifyPassword(String password, String storedHash) {
        if (storedHash == null || !storedHash.contains("$")) {
            return false;
        }

        String[] parts = storedHash.split("\\$");
        if (parts.length != 3) {
            return false;
        }

        try {
            String algorithm = parts[0];
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] originalHash = Base64.getDecoder().decode(parts[2]);

            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(salt);
            byte[] computedHash = md.digest(password.getBytes());

            return MessageDigest.isEqual(originalHash, computedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }
}
