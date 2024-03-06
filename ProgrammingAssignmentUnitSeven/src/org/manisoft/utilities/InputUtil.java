
package org.manisoft;

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

    /**
     * Prompts the user for a valid ID.
     *
     * @return
     */
    public static String enterStudentId() {
        String id;
        boolean isValid;

        do {
            System.out.print("ID? ");
            id = scanner.nextLine().toUpperCase();
            isValid = StrUtil.isValidStudentId(id);
            if (!isValid) {
                System.out.println("Incorrect ID!\n"
                        + "Should be composed of 3 letters and 4 digits.");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Prompts the user for a valid name.
     *
     * @return A valid name.
     */
    public static String enterStudentName() {
        return enterStudentName("");
    }

    /**
     * Prompts the user for a valid name, proposing a default name.
     *
     * @param defaultName The default name.
     * @return The user valid name.
     */
    public static String enterStudentName(String defaultName) {
        String name;
        boolean isValid;

        do {
            System.out.print("Name? ");
            if (!defaultName.isEmpty()) {
                System.out.print("[" + defaultName + "] ");
            }
            name = StrUtil.toTitleCase(
                    StrUtil.removeSpaces(scanner.nextLine()));
            name = (name.isEmpty()) ? defaultName : name;
            isValid = name.length() == 0 || (name.length() >= 5
                    && StrUtil.isAlphabetic(name, true));
            if (!isValid) {
                System.out.println("Incorrect name!\n"
                        + "Should be composed of at least 5 alphabetic letters.");
            }
        } while (!isValid);

        return name;
    }

    /**
     * Prompts the user for a valid course code.
     *
     * @return
     */
    public static String enterCourseCode() {
        return enterCourseCode("");
    }

    /**
     * Prompts the user for a valid course code, proposing a default code.
     *
     * @param defCode The default code.
     * @return The course valid code.
     */
    public static String enterCourseCode(String defCode) {
        String code;
        boolean isValid;

        do {
            System.out.print("Code? ");
            if (!defCode.isEmpty()) {
                System.out.print("[" + defCode + "] ");
            }
            code = scanner.nextLine().toUpperCase();
            isValid = StrUtil.isValidCourseCode(code);
            if (!isValid) {
                System.out.println("Incorrect course code!\n"
                        + "Should be composed of 2 letters and 4 digits.");
            }
        } while (!isValid);

        return code;
    }

    /**
     * Prompts the user for a valid course name.
     *
     * @return A valid name.
     */
    public static String enterCourseName() {
        return enterStudentName("");
    }

    /**
     * Prompts the user for a valid course name, proposing a default name.
     *
     * @param defaultName The default name.
     * @return The user valid name.
     */
    public static String enterCourseName(String defaultName) {
        return enterStudentName(defaultName);
    }

    /**
     * Prompts the user to enter a valid course capacity.
     *
     * @return A valid course capacity.
     */
    public static int enterCourseCap() {
        return enterCourseCap(Integer.MIN_VALUE);
    }

    /**
     * Prompts the user to enter a valid course capacity, proposing a current
     * value.
     *
     * @param defaultVal The current capacity.
     * @return A valid capacity.
     */
    public static int enterCourseCap(int defaultVal) {
        return enterNumber("Capacity [5, 40]? ",
                defaultVal, 5, 40);
    }

    /**
     * Prompts the user to enter a valid course grade count.
     *
     * @return A valid course capacity.
     */
    public static int enterCourseGradesCount() {
        return enterCourseGradesCount(Integer.MIN_VALUE);
    }

    /**
     * Prompts the user to enter a valid course capacity, proposing a current
     * value.
     *
     * @param defaultVal The current capacity.
     * @return A valid capacity.
     */
    public static int enterCourseGradesCount(int defaultVal) {
        return enterNumber("Grades Count [1, 20]? ",
                defaultVal, 1, 20);
    }

    /**
     * Prompts the user to enter a valid grade.
     *
     * @return A valid grade.
     */
    public static int enterGrade(String message) {
        return enterGrade(message, Integer.MIN_VALUE);
    }

    /**
     * Prompts the user to enter a valid grade, proposing a current value.
     *
     * @param defaultVal The current grade.
     * @return A valid grade.
     */
    public static int enterGrade(String message, int defaultVal) {
        return enterNumber(message, defaultVal, 0, 100);
    }
}
