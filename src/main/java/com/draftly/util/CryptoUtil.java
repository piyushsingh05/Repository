package com.draftly.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {
    private static final String SECRET = System.getenv().getOrDefault("TOKEN_ENC_KEY", "16bytesSecretKey!!");

    public static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET.getBytes(), "AES"));
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    }

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET.getBytes(), "AES"));
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
