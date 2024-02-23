package org.manisoft;

/**
 *
 * @author Cyberbox
 */
public class Administrator {

    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Main Menu ---\n");
            System.out.println("1. Students Management");
            System.out.println("2. Courses Management");
            System.out.println("3. Enrollment Management");
            System.out.println("");
            System.out.println("0. Exit");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 3);
            switch (choice) {
                case 1 ->
                    studentsManagement();
                case 2 ->
                    coursesManagement();
                case 3 ->
                    enrollmentManagement();
            }
        } while (choice != 0);
        System.out.println("See you soon, goodbye!");
    }

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
                    0, 3);
            switch (choice) {
                case 1 ->
                    CourseManagement.displayStudents();
                case 2 ->
                    CourseManagement.addNewStudent();
                case 3 ->
                    CourseManagement.updateStudentInfo();                    
            }
        } while (choice != 0);
    }

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
                    0, 3);
            switch (choice) {
                case 1 ->
                    CourseManagement.displayCourses();
                case 2 ->
                    CourseManagement.addNewCourse();
                case 3 ->
                    CourseManagement.updateCourseInfo();
            }
        } while (choice != 0);
    }

    private static void enrollmentManagement() {

    }
}
