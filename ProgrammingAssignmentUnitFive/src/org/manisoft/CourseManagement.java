
package org.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class CourseManagement {
    private static StudentList students = new StudentList();
    private static CourseList courses = new CourseList();
    private static EnrolledCourses enrollment = new EnrolledCourses();
    
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
        for (int i = 0; i < pages; i++) {
            displayStudents(cur, cur + PAGE_SIZE);
            if (i < pages - 1) {
                char ans = StrUtil.promptContinue(
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
        for (int i = 0; i < pages; i++) {
            displayCourses(cur, cur + PAGE_SIZE);
            if (i < pages - 1) {
                char ans = StrUtil.promptContinue(
                        "Continue (Y/N)? ", "YN", 'Y');
                if (ans == 'N') {
                    break;
                }
            }
        }
    }
    
    public static void displayEnrollByStudent(Student student) {
        ArrayList<EnrolledCourse> enr = enrollment.enrollByStudent(student);
        for (EnrolledCourse ec : enr) {
            System.out.println(ec.getCourse() + " " + ec.getAvgGrade());
        }
    }
}
