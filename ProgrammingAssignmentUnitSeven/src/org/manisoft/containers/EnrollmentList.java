package org.manisoft.containers;

import java.util.ArrayList;
import org.manisoft.entities.EnrolledCourse;
import org.manisoft.entities.Student;

/**
 *
 * @author manianis
 */
public class EnrollmentList extends ArrayList<EnrolledCourse> {

    /**
     * Find the student object.
     * @param student
     * @return the object index in the list, -1 otherwise.
     */
    public int findStudent(Student student) {
        return findStudent(student, 0);
    }

    /**
     * Find the student object.
     * @param student
     * @param startIndex the index
     * @return the object index in the list, -1 otherwise.
     */
    public int findStudent(Student student, int startIndex) {
        for (int i = startIndex; i < size(); i++) {
            EnrolledCourse ec = get(i);
            if (ec.getStudent().equals(student)) {
                return i;
            }
        }
        return -1;
    }
}
