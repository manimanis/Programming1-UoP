
package com.manisoft;

/**
 *
 * @author manianis
 */
public class StrUtil {
    /**
     * Ensures that the string does not exceed a certain length.
     *
     * @param s The string to be formatted.
     * @param length The maxmimum allowed length.
     * @return A string that the length does not exceed the desired length.
     */
    public static String formatStr(String s, int length) {
        if (s.length() <= length) {
            return s;
        }
        return s.substring(0, length - 3) + "...";
    }
    
    public static String left(String s, int nbCars) {
        if (s.length() >= nbCars) {
            return s.substring(0, nbCars);
        }
        return s;
    }
    
    public static int calcNumDigits(int value) {
        int nd = 1;
        long p = 1L;
        while (value <= p) {
            p *= 10;
        }
        return nd;
    }
    
    public static String format(int val, int digits) {
        String fs = "%" + digits + "d";
        return String.format(fs, val);
    }

    /**
     * Verifies that s is composed only of alphabetic letters.
     *
     * @param s The string to be verified.
     * @return true if the string is alphabetic.
     */
    public static boolean isAlphabetic(String s) {
        return isAlphabetic(s, false);
    }

    /**
     * Verifies that s is composed only of alphabetic letters.
     *
     * @param s The string to be verified.
     * @param spaces true, if spaces are allowed in the string.
     * @return true if the string is alphabetic.
     */
    public static boolean isAlphabetic(String s, boolean spaces) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean valid = (c >= 'A' && c <= 'Z')
                    || (c >= 'a' && c <= 'z')
                    || (spaces && c == ' ');
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that s is composed only of digits.
     *
     * @param s The string to be verified.
     * @return true if the string is composed only of digits.
     */
    public static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean valid = (c >= '0' && c <= '9');
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    /**
     * Transforms the string to Title Case Format.
     *
     * @param s The string to be transformed.
     * @return The title case formatted string.
     */
    public static String toTitleCase(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i - 1) == ' ') {
                sb.append(s.substring(i, i + 1).toUpperCase());
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * Removes trailing spaces from user input.
     *
     * @param s The String to be formatted.
     * @return The string without trailing spaces.
     */
    public static String removeSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i) != ' '
                    || (s.charAt(i) == ' ' && s.charAt(i - 1) != ' ')) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    /**
     * Duplicate a String count times.
     * 
     * @param s
     * @param count
     * @return 
     */
    public static String duplicateStr(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
