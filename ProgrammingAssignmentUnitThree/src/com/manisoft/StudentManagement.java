package com.manisoft;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author manianis
 */
public class StudentManagement {

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
     * Used to format and parse dates.
     */
    public static SimpleDateFormat sdf = new SimpleDateFormat(
            "dd/MM/yyyy", Locale.ENGLISH);

    /**
     * Transforms a String into a date.
     *
     * @param date The string to be transformed.
     * @return A Calendar objet, or null if a problem occurs.
     */
    public static Calendar toCalendar(String date) {
        Calendar dob = Calendar.getInstance();
        try {
            dob.setTime(sdf.parse(date));
            return dob;
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Formats a Calendar object to a String.
     *
     * @param cal The Calendar object to be formatted.
     * @return A formatted date string, or an empty string if null.
     */
    public static String fromCalendar(Calendar cal) {
        if (cal == null) {
            return "";
        }
        return sdf.format(cal.getTime());
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

    /**
     * Prompts the user for a valid ID.
     *
     * @return
     */
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
        } while (!isValid);

        return id;
    }

    /**
     * Prompts the user for a valid name.
     *
     * @return A valid name.
     */
    public static String enterName() {
        return enterName("");
    }

    /**
     * Prompts the user for a valid name, proposing a default name.
     *
     * @param defaultName The default name.
     * @return The user valid name.
     */
    public static String enterName(String defaultName) {
        String name;
        boolean isValid;

        do {
            System.out.print("Name? ");
            if (!defaultName.isEmpty()) {
                System.out.print("[" + defaultName + "] ");
            }
            name = toTitleCase(removeSpaces(scanner.nextLine()));
            name = (name.isEmpty()) ? defaultName : name;
            isValid = name.length() >= 5 && isAlphabetic(name, true);
            if (!isValid) {
                System.out.println("Incorrect name!\n"
                        + "Should be composed only of alphabetic letters.");
            }
        } while (!isValid);

        return name;
    }

    /**
     * Prompts to enter a valid date of birth.
     *
     * @return A valid birth date.
     */
    public static Calendar enterDateOfBirth() {
        return enterDateOfBirth("");
    }

    /**
     * Prompts the user to enter a valid date of birth.
     *
     * @param defaultDate The current birth date.
     * @return A valid birth date.
     */
    public static Calendar enterDateOfBirth(String defaultDate) {
        String dateOfBirth;
        Calendar dob = null;
        boolean isValid;

        do {
            System.out.print("Date of birth (dd/MM/yyyy)? ");
            if (!defaultDate.isEmpty()) {
                System.out.print("[" + defaultDate + "] ");
            }
            dateOfBirth = scanner.nextLine().trim();
            dateOfBirth = dateOfBirth.isEmpty() ? defaultDate : dateOfBirth;
            dob = toCalendar(dateOfBirth);
            isValid = dob != null;
            if (!isValid) {
                System.out.println("Incorrect birth date!\n"
                        + "Should be formatted dd/MM/yyyy.");
            }
        } while (!isValid);

        return dob;
    }

    /**
     * Prompts the user to enter a valid grade.
     *
     * @return A valid grade.
     */
    public static int enterGrade() {
        return enterGrade(Integer.MIN_VALUE);
    }

    /**
     * Prompts the user to enter a valid grade, proposing a current value.
     *
     * @param defaultVal The current grade.
     * @return A valid grade.
     */
    public static int enterGrade(int defaultVal) {
        return enterNumber("Grade [0, 100]? ",
                defaultVal, 0, 100);
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
                value = tempVal.isEmpty() ? defaultVal : Integer.parseInt(tempVal);
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
     * Prompts the user for student's information.
     *
     * @param student The current student's information.
     */
    public static void enterStudentInfo(Student student) {
        System.out.println("Id: " + student.getId());
        student.setName(enterName(student.getName()));
        student.setDateOfBirth(enterDateOfBirth(
                fromCalendar(student.getDateOfBirth())));
        student.setGrade(enterGrade(student.getGrade()));
    }

    /**
     * Prompts the user for student's information.
     *
     * @return a Student object.
     */
    public static Student enterNewStudentInfo() {
        String id = StudentList.getNewStudentId();
        Student student = new Student(id);
        enterStudentInfo(student);
        return student;
    }

    /**
     * Student List.
     */
    private static final StudentList students = new StudentList("students.obj");

    /**
     * Initializes the student's list with some mockup values.
     */
    static {
        if (students.getStudentsCount() == 0) {
            students.addStudent(new Student("BCS0001",
                    "Mohamed Anis Mani",
                    toCalendar("28/06/1975"), 90));
            students.addStudent(new Student("BCS0002", "Sana Jedidi",
                    toCalendar("24/08/1983"), 85));
            students.addStudent(new Student("BCS0003", "Abderrazek Mani",
                    toCalendar("08/09/2012"), 95));
            students.addStudent(new Student("BCS0004", "Yosri Mani",
                    toCalendar("28/07/1981"), 98));
            students.addStudent(new Student("BCS0005",
                    "Mohamed Bachar Khalifa Hmissi",
                    toCalendar("10/01/2006"), 99));
            students.saveStudents();
        }
    }

    /**
     * Prompts the user for the student's ID and display its information.
     */
    public static void displayStudentInfo() {
        System.out.println("\nDisplay Student's Information\n");
        String ID = enterId();
        int pos = students.findStudentByID(ID);
        if (pos == -1) {
            System.out.println("\nNo students found!\n");
            System.out.println();
            return;
        }
        Student student = students.getStudent(pos);
        System.out.println(student);
        System.out.println();
    }

    /**
     * Displays student's information.
     *
     * @param student The Student object.
     */
    public static void displayStudentInfo(Student student) {
        System.out.println("\n-- Student's Information\n");
        System.out.println(student);
    }

    /**
     * Add new student, entering his information.
     */
    public static void addNewStudent() {
        System.out.println("\nAdd New Student\n");
        Student student = enterNewStudentInfo();
        int pos = students.findStudent(student);
        if (pos != -1) {
            System.out.println("\nAnother student shares the same information!\n");
            displayStudentInfo(student);
            System.out.println();
            return;
        }
        if (students.addStudent(student)) {
            students.saveStudents();
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student cannot be added!");
        }
        System.out.println();
    }

    /**
     * Update student's information.
     */
    public static void updateStudentInfo() {
        System.out.println("\nUpdate Student's Information\n");
        String ID = enterId();
        int pos = students.findStudentByID(ID);
        if (pos == -1) {
            System.out.println("\nNo students found!\n");
            System.out.println();
            return;
        }
        Student student = students.getStudent(pos);
        Student newStudent = student.clone();
        enterStudentInfo(newStudent);
        int oPos = students.findStudent(newStudent);
        if (oPos != pos) {
            System.out.println("Another student has the same information!");
            System.out.println();
            return;
        }

        if (students.updateStudent(newStudent)) {
            students.saveStudents();
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student cannot be updated!");
        }
        System.out.println();
    }

    /**
     * Display students list.
     */
    public static void displayStudentsList() {
        displayStudentsList(0, students.getStudentsCount());
    }

    /**
     * Display students list.
     *
     * @param start starting index.
     * @param end ending index.
     */
    public static void displayStudentsList(int start, int end) {
        int numLines = 5;
        for (int i = start; i < end; i++) {
            Student student = students.getStudent(i);
            System.out.printf("%4d %s %-30s %-3d\n", i + 1,
                    student.getId(), formatStr(student.getName(), 30),
                    student.getAge());
            if ((i - start + 1) % numLines == 0 && i + 1 < end) {
                char answer = promptContinue("Continue (Y/N)?", "YN", 'Y');
                if (answer != 'Y') {
                    break;
                }
            }
        }
        System.out.println();
    }

    /**
     * Prompts the user for the student's ID and delete it.
     */
    public static void deleteStudentInfo() {
        System.out.println("\nDelete Student's Information\n");
        String ID = enterId();
        int pos = students.findStudentByID(ID);
        if (pos == -1) {
            System.out.println("\nNo students found!\n");
            System.out.println();
            return;
        }
        Student student = students.getStudent(pos);
        displayStudentInfo(student);
        char confirm = promptContinue("Delete this student (Y/N)? ", "YN", 'N');
        if (confirm == 'N') {
            System.out.println("Delete aborted!");
            System.out.println();
            return;
        }
        if (students.removeStudent(student)) {
            students.saveStudents();
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student cannot be deleted!");
        }
        System.out.println();
    }
}
