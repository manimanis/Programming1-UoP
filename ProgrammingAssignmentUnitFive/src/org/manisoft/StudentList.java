
package org.manisoft;

/**
 *
 * @author manianis
 */
public class StudentList extends DistinctArrayList<Student> {

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
    
}
