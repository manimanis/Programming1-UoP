package com.manisoft;

/**
 *
 * @author manianis
 */
public class AdminInterface {

    /**
     * The Application Main Menu
     */
    public static void displayMainMenu() {
        int choice;
        do {
            System.out.println("-- Welcome to University Management System --\n");
            System.out.println("1. Add new student");
            System.out.println("2. Update student information");
            System.out.println("3. View student details");
            System.out.println("4. List of students");
            System.out.println("5. Delete student information");
            System.out.println("\n0. Exit");

            choice = StudentManagement.enterNumber("Your choice? ",
                    0, 5);

            switch (choice) {
                case 1 ->
                    StudentManagement.addNewStudent();
                case 2 ->
                    StudentManagement.updateStudentInfo();
                case 3 ->
                    StudentManagement.displayStudentInfo();
                case 4 ->
                    StudentManagement.displayStudentsList();
                case 5 ->
                    StudentManagement.deleteStudentInfo();
            }
        } while (choice != 0);
        System.out.println("Good bye, see you soon!");
    }

}
