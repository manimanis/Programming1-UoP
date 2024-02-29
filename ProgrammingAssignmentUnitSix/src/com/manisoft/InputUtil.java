package com.manisoft;

import java.util.Scanner;

/**
 *
 * @author Cyberbox
 */
public class InputUtil {

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

    /**
     * Prompts the user to enter a number within the range minVal and maxVal.
     *
     * @param msg A message prompt.
     * @param minVal Minimum value.
     * @param maxVal Maximum value.
     * @return A valid number between minVal and maxVal.
     */
    public static int enterNumber(String msg, int minVal, int maxVal) {
        return enterNumber(msg, Integer.MIN_VALUE, minVal, maxVal);
    }

    /**
     * Prompts the user to enter a number within the range minVal and maxVal.
     *
     * @param msg A message prompt.
     * @param defaultVal Default value.
     * @param minVal Minimum value.
     * @param maxVal Maximum value.
     * @return A valid number between minVal and maxVal.
     */
    public static int enterNumber(String msg, int defaultVal,
            int minVal, int maxVal) {
        int value = 0;
        boolean isValid;

        do {
            System.out.print(msg);
            if (defaultVal != Integer.MIN_VALUE) {
                System.out.print("[" + defaultVal + "] ");
            }
            try {
                String tempVal = scanner.nextLine().trim();
                value = tempVal.isEmpty()
                        ? defaultVal : Integer.parseInt(tempVal);
                isValid = value >= minVal && value <= maxVal;
            } catch (NumberFormatException e) {
                isValid = false;
            }
            if (!isValid) {
                System.out.printf("Incorrect value!\n"
                        + "Should be between %d and %d.\n",
                        minVal, maxVal);
            }
        } while (!isValid);

        return value;
    }

    public static String enterString(String msg,
            String defaultVal, int minLength, int maxLength) {
        String val = "";
        String curValue = defaultVal;
        boolean isValid;

        do {
            System.out.print(msg);
            if (curValue == null || curValue.isEmpty()) {
                curValue = "empty";
            }
            System.out.print("[" + curValue + "] ");
            try {
                String tempVal = StrUtil.removeSpaces(
                        scanner.nextLine().trim());
                val = (tempVal.isEmpty()) ? defaultVal : tempVal;
                isValid = (tempVal.length() >= minLength
                        && tempVal.length() <= maxLength) 
                        || (val.equalsIgnoreCase(defaultVal));
            } catch (NumberFormatException e) {
                isValid = false;
            }
            if (!isValid) {
                System.out.printf("Incorrect input!\n"
                        + "Should have between %d and %d characters.\n",
                        minLength, maxLength);
            }
        } while (!isValid);

        return val;
    }
}
