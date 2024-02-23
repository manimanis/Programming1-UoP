
package org.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author manianis
 */
class EnrolledCoursesList implements Serializable {
    private ArrayList<EnrolledCourse> courses = new ArrayList<>();

    public EnrolledCoursesList() {
    }
    
    /**
     * Search if the student is enrolled in the course.
     * 
     * @param course 
     * @return 
     */
    public boolean contains(EnrolledCourse course) {
        return indexOf(course) != -1;
    }
    
    /**
     * Search the index the information about student
     * enrollment.
     * 
     * @param course Enrolled course information
     * @return The index of the object, or -1.
     */
    public int indexOf(EnrolledCourse course) {
        return courses.indexOf(course);
    }
    
    public boolean enrollToCourse(Student student, Course course) {
        return enrollToCourse(new EnrolledCourse(course, student));
    }
    
    public boolean enrollToCourse(EnrolledCourse course) {
        if (contains(course)) {
            return false;
        }
        return courses.add(course);
    }
    
    public boolean leaveFromCourse(EnrolledCourse course) {
        if (!contains(course)) {
            return false;
        }
        return courses.remove(course);
    }
    
    /**
     * Remove all the leaving student enrollments.
     * 
     * @param student Student to remove
     */
    public void removeStudent(Student student) {
        int n = 0;
        for (int i = 0; i < courses.size(); i++) {
            EnrolledCourse ec = courses.get(i);
            if (!student.equals(ec.getStudent())) {
                courses.set(n, ec);
                n++;
            }
        }
        while (courses.size() > n) {
            courses.remove(n);
        }
    }
    
    /**
     * Remove all the leaving course enrollments.
     * 
     * @param course Course to remove
     */
    public void removeCourse(Course course) {
        int n = 0;
        for (int i = 0; i < courses.size(); i++) {
            EnrolledCourse ec = courses.get(i);
            if (!course.equals(ec.getCourse())) {
                courses.set(n, ec);
                n++;
            }
        }
        while (courses.size() > n) {
            courses.remove(n);
        }
    }
    
    /**
     * Returns if the student is enrolled in the course or not.
     * 
     * @param student A student object
     * @param course A course object
     * @return true if the student is enrolled to the course.
     */
    public boolean isEnrolled(Student student, Course course) {
        return contains(new EnrolledCourse(course, student, null));
    }
    
    /**
     * Return the student's course enrollment.
     * 
     * @param student A student object
     * @param course A course object
     * @return null if the student is not enrolled to the course.
     */
    public EnrolledCourse getEnroll(Student student, Course course) {
        for (EnrolledCourse ec : courses) {
            if (student.equals(ec.getStudent())
                    && course.equals(ec.getCourse())) {
                return ec;
            }
        }
        return null;
    }
    
    /**
     * List of enrollment of one student.
     * 
     * @param student A student object.
     * @return 
     */
    public ArrayList<EnrolledCourse> enrollByStudent(Student student) {
        ArrayList<EnrolledCourse> res = new ArrayList<>();
        for (EnrolledCourse ec : courses) {
            if (student.equals(ec.getStudent())) {
                res.add(ec);
            }
        }
        return res;
    }
    
    /**
     * List of enrollment to one course.
     * @param course
     * @return 
     */
    public ArrayList<EnrolledCourse> enrollByCourse(Course course) {
        ArrayList<EnrolledCourse> res = new ArrayList<>();
        for (EnrolledCourse ec : courses) {
            if (course.equals(ec.getCourse())) {
                res.add(ec);
            }
        }
        return res;
    }
    
    /**
     * Reads EnrolledCoursesList from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        int count = ois.readInt();
        courses.clear();
        for (int i = 0; i < count; i++) {
            courses.add((EnrolledCourse) ois.readObject());
        }
    }

    /**
     * Writes a Student to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeInt(courses.size());
        for (EnrolledCourse ec : courses) {
            oos.writeObject(ec);
        }
    }
}
