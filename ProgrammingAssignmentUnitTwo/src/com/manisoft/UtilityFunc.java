
package com.manisoft;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author manianis
 */
public class UtilityFunc {
    private static final Scanner scan = new Scanner(System.in);
    
    /**
     * Utility function to convert a string to title case.
     * @param s String to convert.
     * @return A new string in title case format.
     */
    public static String toTitleCase(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || (s.charAt(i) != ' ' && s.charAt(i - 1) == ' ')) {
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * Utility function to remove unnecessary spaces from the string.
     * @param s
     * @return 
     */
    public static String removeSpaces(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            if (car != ' ' || (i > 0 && sb.charAt(sb.length() - 1) != ' ')) {
                sb.append(car);
            }
        }
        return sb.toString();
    }
    
    public static String getString(String msg, int minLen, int maxLen) {
        String s;
        do {
            System.out.print(msg);
            s = removeSpaces(scan.nextLine().trim());
            if (s.length() < minLen || s.length() > maxLen) {
                System.out.println(String.format(
                        "Input should contain between %d and %d characters!",
                        minLen, maxLen));
            }
        } while (s.length() < minLen || s.length() > maxLen);
        return s;
    }
    
    public static int getInteger(String msg, int minVal, int maxVal) {
        int v = 0;
        boolean valid;
        do {
            System.out.print(msg);
            try {
                v = scan.nextInt();
                valid = v >= minVal && v <= maxVal;
            } catch (InputMismatchException e) {
                valid = false;
            } finally {
                scan.nextLine();
            }

            if (!valid) {
                System.out.println(String.format(
                        "Input should range between %d and %d!",
                        minVal, maxVal));
            }
        } while (!valid);
        return v;
    }
    
    public static String formatStr(String s, int length) {
        if (s.length() <= length) {
            return s;
        }
        return s.substring(0, length-3) + "...";
    }
}
