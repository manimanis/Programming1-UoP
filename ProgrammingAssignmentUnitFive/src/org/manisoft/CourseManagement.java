package org.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class CourseManagement {

    private static StudentList students = new StudentList();
    private static CourseList courses = new CourseList();
    private static EnrolledCoursesList enrollment = new EnrolledCoursesList();
    
    static {
        Student student1 = new Student(Student.genStudentID(), "Amine");
        Student student2 = new Student(Student.genStudentID(), "Zaher");
        Course course1 = new Course("CS1101", "Programming Fundamentals");
        Course course2 = new Course("CS1102", "Programming One");
        students.add(student1);
        students.add(student2);
        courses.add(course1);
        courses.add(course2);
        enrollment.enrollToCourse(student1, course1);
        enrollment.enrollToCourse(student2, course2);
    }

    private final static int PAGE_SIZE = 10;

    public static void displayStudents(int start, int end) {
        end = (end > students.size()) ? students.size() : end;
        for (int i = start; i < end; i++) {
            System.out.println(students.get(i));
        }
    }

    public static void displayStudents() {
        int pages = students.size() / PAGE_SIZE
                + ((students.size() % PAGE_SIZE != 0) ? 1 : 0);
        int cur = 0;
        System.out.println("\n--- List of Students ---\n");
        if (pages == 0) {
            System.out.println("No students yet!");
        }
        for (int i = 0; i < pages; i++) {
            System.out.println("Page: " + (i + 1) + " / " + pages);
            displayStudents(cur, cur + PAGE_SIZE);
            if (i < pages - 1) {
                char ans = InputUtil.promptContinue(
                        "Continue (Y/N)? ", "YN", 'Y');
                if (ans == 'N') {
                    break;
                }
            }
        }
    }

    public static void displayCourses(int start, int end) {
        end = (end > courses.size()) ? courses.size() : end;
        for (int i = start; i < end; i++) {
            System.out.println(courses.get(i));
        }
    }

    public static void displayCourses() {
        int pages = courses.size() / PAGE_SIZE
                + ((courses.size() % PAGE_SIZE != 0) ? 1 : 0);
        int cur = 0;
        System.out.println("\n--- List of Courses ---\n");
        if (pages == 0) {
            System.out.println("No courses yet!");
        }
        for (int i = 0; i < pages; i++) {
            System.out.println("Page: " + (i + 1) + " / " + pages);
            displayCourses(cur, cur + PAGE_SIZE);
            if (i < pages - 1) {
                char ans = InputUtil.promptContinue(
                        "Continue (Y/N)? ", "YN", 'Y');
                if (ans == 'N') {
                    break;
                }
            }
        }
    }

    public static void displayCoursesEnrolledByStudent(Student student) {
        ArrayList<EnrolledCourse> enr = enrollment.enrollByStudent(student);
        System.out.println("\n*** List of Courses ***\n");
        for (EnrolledCourse ec : enr) {
            System.out.println(ec.getCourse() + " " + ec.getAvgGrade());
        }
    }
    
    public static void displayStudentsEnrolledToCourse(Course course) {
        ArrayList<EnrolledCourse> enr = enrollment.enrollByCourse(course);
        System.out.println("\n*** List of Students ***\n");
        for (EnrolledCourse ec : enr) {
            System.out.println(ec.getStudent());
        }
    }

    public static void displayEnrolledCourse(EnrolledCourse course) {
        System.out.println("\n--- Enrolled Course ---\n");
        System.out.println("Course: " + course.getCourse());
        System.out.println("Student: " + course.getStudent());
        System.out.println("Grades: ");
        for (int i = 0; i < course.getGradesCount(); i++) {
            System.out.printf("%2d %3d",
                    (i + 1), course.getGrade(i));
        }
    }

    /**
     * Prompts the user to enter new student's information
     */
    public static void addNewStudent() {
        System.out.println("\n--- Add New Student ---\n");
        Student student = new Student();

        // Enter name
        student.setName(InputUtil.enterStudentName());
        if (student.getName().isEmpty()) {
            return;
        }

        // Verify the name's unicity
        Student exStudent = students.getByName(student.getName());
        if (exStudent != null) {
            System.out.println("This student name already exists!");
            return;
        }
        student.setID(Student.genStudentID());

        // Generate a new ID
        System.out.println("New Student ID: " + student.getID());

        // Insert the student
        if (students.add(student)) {
            System.out.println("Student "
                    + student.getID() + " " + student.getName()
                    + " inserted!");
        }
    }

    /**
     * Prompts the user to update student's information
     */
    public static void updateStudentInfo() {
        System.out.println("\n--- Update Student ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();

        // Verify ID existance
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }

        System.out.println("ID: " + Id);
        Student copySt = (Student) student.clone();

        // Enter students name;
        copySt.setName(InputUtil.enterStudentName(
                copySt.getName()));

        // Verify name unicity
        Student oStudent = students.getByName(copySt.getName());
        if (oStudent != null && !oStudent.equals(student)) {
            System.out.println("Another student has this name!");
            return;
        }

        // Update students record
        int pos = students.indexOf(student);
        students.set(pos, copySt);
        System.out.println("Student "
                + copySt.getID() + " " + copySt.getName()
                + " updated!");
    }
    
    /**
     * Prompts the user for student removal
     */
    public static void deleteStudentInfo() {
        System.out.println("\n--- Delete Student ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();

        // Verify ID existance
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }

        student.display();

        char ans = InputUtil.promptContinue("Do you want to remove"
                + " this stduent?\nAll the students enrollment "
                + " will be removed. (Y/N)", "YN", 'N');
        if (ans == 'Y') {
            enrollment.removeStudent(student);
            students.remove(student);
            System.out.println("The student is removed!");
        }
    }

    /**
     * Prompts the user to enter new course's information.
     */
    public static void addNewCourse() {
        System.out.println("\n--- Add New Course ---\n");
        Course course = new Course();

        // Enter course code
        course.setCode(InputUtil.enterCourseCode());
        if (course.getCode().isEmpty()) {
            return;
        }

        // Verify course existance by code
        Course exCourse = courses.getByCode(course.getCode());
        if (exCourse != null) {
            System.out.println("This course code already exists!");
            System.out.println(exCourse);
            return;
        }

        // Enter course name
        course.setName(InputUtil.enterCourseName());
        if (course.getName().isEmpty()) {
            return;
        }

        // Verify course existance by name
        exCourse = courses.getByName(course.getName());
        if (exCourse != null) {
            System.out.println("This student name already exists!");
            System.out.println(exCourse);
            return;
        }

        // Enter course capacity
        course.setCapacity(
                InputUtil.enterCourseCap(
                        course.getCapacity()));

        // Enter course grades count
        course.setGradesCount(
                InputUtil.enterCourseGradesCount(
                        course.getGradesCount()));

        // Insert new course
        if (courses.add(course)) {
            System.out.println("Course "
                    + course.getCode() + " " + course.getName()
                    + " inserted!");
        }
    }

    public static void updateCourseInfo() {
        System.out.println("\n--- Update Course ---\n");
        // Enter course code 
        String code = InputUtil.enterCourseCode();

        // Verify course existance by code
        Course oCourse = courses.getByCode(code);
        if (oCourse == null) {
            System.out.println("Course does not exists!");
            return;
        }

        System.out.println("Code: " + oCourse.getCode());

        // Clone the course
        Course nCourse = (Course) oCourse.clone();

        //Enter course name
        nCourse.setName(InputUtil.enterCourseName(
                oCourse.getName()));

        // Verify course existance by name
        Course exCourse = courses.getByName(nCourse.getName());
        if (exCourse != null && !exCourse.equals(oCourse)) {
            System.out.println("This course name already exists!");
            System.out.println(exCourse);
            return;
        }

        // Enter course capacity
        nCourse.setCapacity(
                InputUtil.enterCourseCap(
                        oCourse.getCapacity()));

        // Enter course grades count
        nCourse.setGradesCount(
                InputUtil.enterCourseGradesCount(
                        oCourse.getGradesCount()));

        // Uodate course
        int pos = courses.indexOf(oCourse);
        courses.set(pos, nCourse);
        System.out.println("Course "
                + nCourse.getCode() + " " + nCourse.getName()
                + " updated!");
    }
    
    /**
     * Prompts the user for course removal
     */
    public static void deleteCourseInfo() {
        System.out.println("\n--- Delete Course ---\n");

        // Enter course code
        String code = InputUtil.enterCourseCode();

        // Verify code existance
        Course course = courses.getByCode(code);
        if (course == null) {
            System.out.println("No course found!");
            return;
        }

        course.display();
        char ans = InputUtil.promptContinue("Do you want to remove"
                + " this course?\nAll the course's enrollment "
                + " will be removed. (Y/N)", "YN", 'N');
        if (ans == 'Y') {
            enrollment.removeCourse(course);
            courses.remove(course);
            System.out.println("The course is removed!");
        }
    }
     
    public  static  void showStudentEnrollments() {
        System.out.println("\n--- Student Enrollments ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }
        
        displayCoursesEnrolledByStudent(student);
    }
    
    public  static  void showCourseEnrollments() {
        System.out.println("\n--- Course Enrollments ---\n");

        // Enter Course Code
        String code = InputUtil.enterCourseCode();
        Course course = courses.getByCode(code);
        if (course == null) {
            System.out.println("No course found!");
            return;
        }
        
        displayStudentsEnrolledToCourse(course);
    }
    
    /**
     * Enroll a student to a specific course
     */
    public static void enrollStudent() {
        System.out.println("\n--- New Student Enrollment ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }
        
        // Enter course code
        String code = InputUtil.enterCourseCode();
        Course course = courses.getByCode(code);
        if (course == null) {
            System.out.println("No course found!");
            return;
        }
        
        if (enrollment.isEnrolled(student, course)) {
            System.out.println("The student is aleardy enrolled!");
            return;
        }
        
        student.display();
        System.out.println();
        course.display();
        
        EnrolledCourse ec = new EnrolledCourse(course, student);
        enrollment.enrollToCourse(ec);
        System.out.println("The student is enrolled to the course!");
    }
    
    /**
     * Unenroll a student to a specific course
     */
    public static void unenrollStudent() {
        System.out.println("\n--- Student's Enrollment ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }
        
        // Enter course code
        String code = InputUtil.enterCourseCode();
        Course course = courses.getByCode(code);
        if (course == null) {
            System.out.println("No course found!");
            return;
        }
        
        if (!enrollment.isEnrolled(student, course)) {
            System.out.println("The student is not enrolled!");
            return;
        }
        
        EnrolledCourse ec = enrollment.getEnroll(student, course);
        ec.display();
        
        char ans = InputUtil.promptContinue("Do you want to remove"
                + " this course enrollment? (Y/N)",
                "YN", 'N');
        if (ans == 'Y') {
            enrollment.leaveFromCourse(ec);
            System.out.println("The student left the course!");
        }
    }
    
    public static void showStudentGrades() {
        System.out.println("\n--- Student grades ---\n");

        // Enter student ID
        String Id = InputUtil.enterStudentId();
        Student student = students.getByID(Id);
        if (student == null) {
            System.out.println("No student found!");
            return;
        }
        
        // Enter course code
        String code = InputUtil.enterCourseCode();
        Course course = courses.getByCode(code);
        if (course == null) {
            System.out.println("No course found!");
            return;
        }
        
        
    }
}
