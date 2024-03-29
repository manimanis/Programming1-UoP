package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class StudentList {

    private static int numStudents = 0;
    
    private Student[] students;
    private int maxStudentCount = 20;
    private int studentsCount = 0;
    // private final ArrayList<Student> students = new ArrayList<>();
    private final String filePath;

    /**
     * Constructs a StudentList object.
     * @param filePath
     */
    public StudentList(String filePath) {
        this.students = new Student[maxStudentCount];
        this.filePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            loadStudents();
        }
    }

    /**
     * Returns last student's ID.
     * @return
     */
    public static String getNewStudentId() {
        return String.format("%s%04d", "BCS", numStudents + 1);
    }

    /**
     * Return student's list array.
     * @return Student Array.
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * Get a student by its index in the user.
     * @param idx Student's index.
     * @return Student object.
     */
    public Student getStudent(int idx) {
        return students[idx];
    }

    /**
     * Modify student at the specified index in the array.
     * @param idx The index.
     * @param student The new object to store at idx position.
     */
    public void setStudent(int idx, Student student) {
        students[idx] = student;
    }

    /**
     * Returns students count.
     * @return students count.
     */
    public int getStudentsCount() {
        return studentsCount;
    }

    /**
     * Add a new student.
     * @param student The Student object to be inserted.
     * @return true if the object is inserted, false otherwise.
     */
    public boolean addStudent(Student student) {
        int pos = findStudent(student);
        if (pos != -1) {
            return false;
        }
        numStudents++;
        if (studentsCount + 1 > maxStudentCount) {
            maxStudentCount += 20;
            Student[] newStudents = new Student[maxStudentCount];
            System.arraycopy(students, 0, newStudents, 0, studentsCount);
            students = newStudents;
        }
        students[studentsCount] = student;
        studentsCount++;
        return true;
    }

    /**
     * Update a student's information.
     * @param student The Student object to be updated.
     * @return true if the object is updated, false otherwise.
     */
    public boolean updateStudent(Student student) {
        int posId = findStudentByID(student.getId());
        if (posId == -1) {
            return false;
        }
        int posName = findStudentByName(student.getName());
        if (posName != -1 && posName != posId) {
            return false;
        }
        setStudent(posId, student);
        return true;
    }

    /**
     * Delete a student from the record.
     * @param student The student object to be deleted.
     * @return true if the student is deleted, false otherwise.
     */
    public boolean removeStudent(Student student) {
        int posId = findStudentByID(student.getId());
        return StudentList.this.removeStudent(posId);
    }
     
    /**
     * Delete a student from the record.
     * @param index The index of the student in the array.
     * @return  true if th student is deleted, false otherwise.
     */
    public boolean removeStudent(int index) {
        if (studentsCount == 0 || index < 0 || index >= studentsCount) {
            return false;
        }
        for (int i = index + 1; i < studentsCount; i++) {
            students[i-1] = students[i];
        }
        studentsCount--;
        return true;
    }
    
    /**
     * Clear students list, by resetting stduents count to zero.
     */
    public void clear() {
        studentsCount = 0;
    }

    /**
     * Find a student by its ID, or by its name.
     * @param student A Student's object to serach for.
     * @return The first object position in the students' array or -1 otherwise.
     */
    public int findStudent(Student student) {
        int pos = findStudentByID(student.getId());
        if (pos != -1) {
            return pos;
        }
        pos = findStudentByName(student.getName());
        if (pos != -1) {
            return pos;
        }
        return -1;
    }

    /**
     * Find a student by or by its name.
     * @param name A Student's name to serach for.
     * @return The first position of the name, or -1 otherwise.
     */
    public int findStudentByName(String name) {
        for (int i = 0; i < studentsCount; i++) {
            if (name.equalsIgnoreCase(students[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find a student by or by its ID.
     * @param id A Student's ID to serach for.
     * @return The first position of the ID, or -1 otherwise.
     */
    public int findStudentByID(String id) {
        for (int i = 0; i < studentsCount; i++) {
            if (id.equalsIgnoreCase(students[i].getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Loads Students from a file.
     */
    public final void loadStudents() {
        ObjectInputStream ois = null;
        try {
            File fichier = new File(filePath);
            if (!fichier.exists()) {
                return;
            }
            ois = new ObjectInputStream(new FileInputStream(fichier));
            this.numStudents = ois.readInt();
            int nbr = ois.readInt();
            clear();
            for (int i = 0; i < nbr; i++) {
                addStudent((Student) ois.readObject());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Saves students to a file.
     */
    public final void saveStudents() {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeInt(numStudents);
            oos.writeInt(studentsCount);
            for (Student student : students) {
                oos.writeObject(student);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
