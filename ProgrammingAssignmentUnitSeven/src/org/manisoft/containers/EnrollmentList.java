package org.manisoft.containers;

import java.util.ArrayList;
import org.manisoft.entities.EnrolledCourse;
import org.manisoft.entities.Student;

/**
 *
 * @author manianis
 */
public class EnrollmentList extends ArrayList<EnrolledCourse> {

    public int findStudent(Student student) {
        return findStudent(student, 0);
    }

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
