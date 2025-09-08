package com.example.compressurlback.util;

import java.math.BigInteger;

public class Base62Encoder {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String encode(byte[] input, int minLength) {
        BigInteger bigInt = new BigInteger(1, input); // ensure positive
        StringBuilder sb = new StringBuilder();

        while (bigInt.compareTo(BigInteger.ZERO) > 0) {
            int remainder = bigInt.mod(BigInteger.valueOf(62)).intValue();
            sb.append(BASE62.charAt(remainder));
            bigInt = bigInt.divide(BigInteger.valueOf(62));
        }

        // Reverse because we build string backwards
        String encoded = sb.reverse().toString();

        // Pad with 'a' if shorter than minLength
        while (encoded.length() < minLength) {
            encoded = "a" + encoded;
        }

        return encoded;
    }
}

