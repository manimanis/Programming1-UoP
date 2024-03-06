package org.manisoft.entities;

import org.manisoft.utilities.InputUtil;

/**
 *
 * @author Cyberbox
 */
public class Administrator {

    /**
     * Application Main Menu.
     */
    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Main Menu ---\n");
            System.out.println("1. Students Management");
            System.out.println("2. Courses Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Grades Management");
            System.out.println("");
            System.out.println("0. Exit");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 4);
            switch (choice) {
                case 1 ->
                    studentsManagement();
                case 2 ->
                    coursesManagement();
                case 3 ->
                    enrollmentManagement();
                case 4 ->
                    gradesManagement();
            }
        } while (choice != 0);
        System.out.println("See you soon, goodbye!");
    }

    /**
     * Menu for students management.
     */
    private static void studentsManagement() {
        int choice;
        do {
            System.out.println("\n--- Student Management Menu ---\n");
            System.out.println("1. Students List");
            System.out.println("2. Add New Student");
            System.out.println("3. Update Student's Information");
            System.out.println("4. Remove Student");
            System.out.println("");
            System.out.println("0. Back to main Menu");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 4);
            switch (choice) {
                case 1 ->
                    CourseManagement.displayStudents();
                case 2 ->
                    CourseManagement.addNewStudent();
                case 3 ->
                    CourseManagement.updateStudentInfo();
                case 4 ->
                    CourseManagement.deleteStudentInfo();
            }
        } while (choice != 0);
    }

    /**
     * Menu for courses management.
     */
    private static void coursesManagement() {
        int choice;
        do {
            System.out.println("\n--- Course Management Menu ---\n");
            System.out.println("1. Courses List");
            System.out.println("2. Add New Course");
            System.out.println("3. Update Course's Information");
            System.out.println("4. Remove Course");
            System.out.println("");
            System.out.println("0. Back to main Menu");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 4);
            switch (choice) {
                case 1 ->
                    CourseManagement.displayCourses();
                case 2 ->
                    CourseManagement.addNewCourse();
                case 3 ->
                    CourseManagement.updateCourseInfo();
                case 4 ->
                    CourseManagement.deleteCourseInfo();
            }
        } while (choice != 0);
    }

    /**
     * Menu for courses enrollment.
     */
    private static void enrollmentManagement() {
        int choice;
        do {
            System.out.println("\n--- Enrollment Management Menu ---\n");
            System.out.println("1. Show Student's Enrollments");
            System.out.println("2. Show Course's Enrollments");
            System.out.println("3. Enroll Student");
            System.out.println("4. Unenroll Student");
            System.out.println("");
            System.out.println("0. Back to main Menu");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 4);
            switch (choice) {
                case 1 ->
                    CourseManagement.showStudentEnrollments();
                case 2 ->
                    CourseManagement.showCourseEnrollments();
                case 3 ->
                    CourseManagement.enrollStudent();
                case 4 ->
                    CourseManagement.unenrollStudent();
            }
        } while (choice != 0);
    }

    /**
     * Menu to change students grades.
     */
    private static void gradesManagement() {
        int choice;
        do {
            System.out.println("\n--- Grades Management Menu ---\n");
            System.out.println("1. Show Student's Grades");
            System.out.println("2. Change Student's Grades");

            System.out.println("");
            System.out.println("0. Back to main Menu");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 2);
            switch (choice) {
                case 1 ->
                    CourseManagement.showStudentGrades();
                case 2 ->
                    CourseManagement.changeStudentGrades();
            }
        } while (choice != 0);
    }
}
