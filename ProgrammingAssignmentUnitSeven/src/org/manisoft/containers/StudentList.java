
package org.manisoft.containers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.manisoft.entities.Student;

/**
 *
 * @author manianis
 */
public class StudentList extends DistinctArrayList<Student> {
    
    private int index;
    
    /**
     * Generate a new Student ID.
     * @return Student ID.
     */
    public String genStudentID() {
        index++;
        return String.format("BCS%04d", index);
    }

    /**
     * Find a student by his ID.
     * 
     * @param ID The student's ID
     * @return the student having that name, null otherwise.
     */
    public Student getByID(String ID) {
        for (Student st : this) {
            if (ID.equalsIgnoreCase(st.getID())) {
                return st;
            }
        }
        return null;
    }
    
    public static StudentList findNotEnrolledStudents(StudentList studentList,
            EnrollmentList enrollmentList) {
        StudentList newList = new StudentList();
        for (Student student : studentList) {
            if (enrollmentList.findStudent(student) == -1) {
                newList.add(student);
            }
        }
        return newList;
    }
    
    /**
     * Find a student by his name.
     * 
     * @param name The student's name
     * @return the student having that name, null otherwise.
     */
    public Student getByName(String name) {
        for (Student st : this) {
            if (name.equalsIgnoreCase(st.getName())) {
                return st;
            }
        }
        return null;
    }

    /**
     * Reads a StudentList from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        index = ois.readInt();
        super.readObject(ois);
    }

    /**
     * Writes a StudentList to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    @Override
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeInt(index);
        super.writeObject(oos);
    }    
}
