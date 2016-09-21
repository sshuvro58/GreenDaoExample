package com.shuvro.greendaosample;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by Mithun Sarker on 9/21/16.
 * For Surroundapps
 */
public class SecretsDatabaseKeyGenerator {
    public static final int DEFAULT_KEY_LENGTH = 256;

    public static String generateKey() {
        try {
            SecretKey secretKey = internalGenerateKey();
            return Base64.encodeToString(secretKey.getEncoded(), Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return null;
    }

    private static SecretKey internalGenerateKey() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(DEFAULT_KEY_LENGTH, random);
        return keyGenerator.generateKey();
    }
}
