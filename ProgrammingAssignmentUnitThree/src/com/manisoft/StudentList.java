
package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class StudentList {

    private static int numStudents = 0;
    private final ArrayList<Student> students = new ArrayList<>();
    private final String filePath;

    public StudentList(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            loadStudents();
        }
    }

    public static String getNewStudentId() {
        return String.format("%s%04d", "BCS", numStudents + 1);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getStudent(int idx) {
        return students.get(idx);
    }

    public void setStudent(int idx, Student student) {
        students.set(idx, student);
    }

    public int getStudentsCount() {
        return students.size();
    }

    public boolean addStudent(Student student) {
        int pos = findStudent(student);
        if (pos != -1) {
            return false;
        }
        numStudents++;
        return students.add(student);
    }

    public boolean updateStudent(Student student) {
        int posId = findStudentByID(student.getId());
        if (posId == -1) {
            return false;
        }
        int posName = findStudentByName(student.getName());
        if (posName != -1 && posName != posId) {
            return false;
        }
        students.set(posId, student);
        return true;
    }
    
    public boolean deleteStudent(Student student) {
        int posId = findStudentByID(student.getId());
        if (posId == -1) {
            return false;
        }
        students.remove(posId);
        return true;
    }

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

    public int findStudentByName(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (name.equalsIgnoreCase(students.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    public int findStudentByID(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (id.equalsIgnoreCase(students.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Write a book to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {

    }

    public final void loadStudents() {
        ObjectInputStream ois = null;
        try {
            File fichier = new File(filePath);
            ois = new ObjectInputStream(new FileInputStream(fichier));
            this.numStudents = ois.readInt();
            int nbr = ois.readInt();
            students.clear();
            for (int i = 0; i < nbr; i++) {
                students.add((Student) ois.readObject());
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

    public final void saveStudents() {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeInt(numStudents);
            oos.writeInt(students.size());
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
