package com.manisoft;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class StudentManagement {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isAlphabetic(String s) {
        return isAlphabetic(s, false);
    }

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

    public static boolean isValidId(String s) {
        if (s.length() != 7) {
            return false;
        }
        return s.length() == 7
                && isAlphabetic(s.substring(0, 3))
                && isNumeric(s.substring(3));
    }

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

    public static String enterId() {
        String id;
        boolean isValid;

        do {
            System.out.print("ID? ");
            id = scanner.nextLine().toUpperCase();
            isValid = isValidId(id);
            if (!isValid) {
                System.out.println("Incorrect ID!\n"
                        + "Should be composed of 3 letters and 4 digits.");
            }
        } while (isValid);

        return id;
    }

    public static String enterName() {
        String name;
        boolean isValid;

        do {
            System.out.print("Name? ");
            name = toTitleCase(removeSpaces(scanner.nextLine()));
            isValid = name.length() >= 5 && isAlphabetic(name, true);
            if (!isValid) {
                System.out.println("Incorrect name!\n"
                        + "Should be composed only of alphabetic letters.");
            }
        } while (isValid);

        return name;
    }

    public static Calendar enterDateOfBirth() {
        String dateOfBirth;
        Calendar dob = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                Locale.ENGLISH);
        boolean isValid;

        do {
            System.out.print("Date of birth (dd/MM/yyyy)? ");
            dateOfBirth = scanner.nextLine();
            try {
                dob.setTime(sdf.parse(dateOfBirth));
                isValid = true;
            } catch (ParseException ex) {
                isValid = false;
            }
            if (!isValid) {
                System.out.println("Incorrect birth date!\n"
                        + "Should be formatted dd/MM/yyyy.");
            }
        } while (isValid);

        return dob;
    }
    
    public static int enterGrade() {
        int grade;
        boolean isValid;

        do {
            System.out.print("Grade [0, 100]? ");
            grade = scanner.nextInt();
            isValid = grade >= 0 && grade <= 100;
            if (!isValid) {
                System.out.println("Incorrect grade!\n"
                        + "Should be between 0 and 100.");
            }
        } while (isValid);

        return grade;
    }

    public static Student enterStudentInfo() {
        String id = enterId();
        String name = enterName();
        Calendar dob = enterDateOfBirth();
        int grade = enterGrade();
        return new Student(id, name, dob, grade);
    }
    
    public static void displayStudentInfo(Student student) {
        System.out.println(student);
    }

}
