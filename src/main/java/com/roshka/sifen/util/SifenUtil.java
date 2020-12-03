package com.roshka.sifen.util;

import java.util.Random;

public class SifenUtil {
    public static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String leftPad(String string, char character, int length) {
        return String.format("%" + length + "s", string).replace(' ', character);
    }

    public static String generateDv(String ruc) {
        int basemax = 11, k = 2, total = 0;

        if (ruc.equals("88888801")) {
            return "5";
        }

        for (int i = ruc.length() - 1; i >= 0; i--) {
            k = k > basemax ? 2 : k;
            int n = Integer.parseInt(ruc.substring(i, i + 1));
            total += n * k;
            k++;
        }
        return String.valueOf((total % 11) > 1 ? 11 - (total % 11) : 0);
    }

    public static String generateRandomNumber() {
        return leftPad(String.valueOf(new Random().ints(1, 1, 999999999).distinct().toArray()[0]), '0', 9);
    }

    public static <T> T coalesce(T... items) {
        for (T i : items) if (i != null) return i;
        return null;
    }
}
