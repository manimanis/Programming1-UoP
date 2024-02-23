/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.manisoft;

import java.util.Scanner;

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
     * Verifies that s is a valid ID.
     *
     * @param s The string to be verified.
     * @return true if the string is a valid ID.
     */
    public static boolean isValidId(String s) {
        if (s.length() != 7) {
            return false;
        }
        return s.length() == 7
                && isAlphabetic(s.substring(0, 3))
                && isNumeric(s.substring(3));
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
     * Creates a shared scanner object.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a letter to continue a treatment.
     *
     * @param message The message to be displayed as an invite.
     * @param options The accepted letters.
     * @param defaultOption The default letter.
     * @return The user choice.
     */
    public static char promptContinue(String message, String options,
            char defaultOption) {
        String inp = null;
        do {
            System.out.print(message + "[" + defaultOption + "] ");
            inp = scanner.nextLine().toUpperCase().trim();
            inp = (inp.length() == 0) ? ("" + defaultOption) : inp;
        } while (inp.length() != 1 || !options.contains(inp));
        return inp.charAt(0);
    }
}
