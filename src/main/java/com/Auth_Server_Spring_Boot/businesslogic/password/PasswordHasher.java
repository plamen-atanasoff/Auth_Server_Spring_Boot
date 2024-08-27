package com.Auth_Server_Spring_Boot.businesslogic.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordHasher {

    private static final int ITERATIONS = 65_536;

    private static final int KEY_LENGTH = 256;

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    private static final int SALT_LENGTH = 64;

    public static String generateHash(String password, byte[] salt)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        return salt;
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        Scanner scanner = new Scanner(System.in);
//        byte[] salt = generateSalt();
//        System.out.println(new String(salt));
//        while (true) {
//            String input = scanner.nextLine();
//
//            if (input.equals("exit")) {
//                break;
//            }
//
//            System.out.println(generateHash(input, salt));
//        }
//    }

}