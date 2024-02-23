
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
class EnrolledCourses implements Serializable {
    private ArrayList<EnrolledCourse> courses;

    public EnrolledCourses() {
    }
    
    public boolean contains(EnrolledCourse course) {
        return indexOf(course) != -1;
    }
    
    public int indexOf(EnrolledCourse course) {
        return courses.indexOf(course);
    }
    
    public boolean enrollCourse(EnrolledCourse course) {
        if (contains(course)) {
            return false;
        }
        return courses.add(course);
    }
    
    public boolean leaveCourse(EnrolledCourse course) {
        if (!contains(course)) {
            return false;
        }
        return courses.remove(course);
    }
    
    public boolean isEnrolled(Student student, Course course) {
        return contains(new EnrolledCourse(course, student, null));
    }
    
    public ArrayList<EnrolledCourse> enrollByStudent(Student student) {
        ArrayList<EnrolledCourse> res = new ArrayList<>();
        for (EnrolledCourse ec : courses) {
            if (student.equals(ec.getStudent())) {
                res.add(ec);
            }
        }
        return res;
    }
    
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
     * Reads EnrolledCourses from the input stream.
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
