
package org.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author manianis
 */
public class Student implements Serializable, Comparable<Student> {
    private String ID;
    private String name;

    /**
     * Student default.
     */
    public Student() {
        this("", "");
    }  

    /**
     * Student Constructor.
     * @param ID
     * @param name 
     */
    public Student(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * The student ID getter.
     * @return 
     */
    public String getID() {
        return ID;
    }

    /**
     * The student ID setter.
     * @param ID 
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * The student name getter.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * The student name setter.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Display Student Information in apretty format.
     */
    public void display() {
        System.out.println("Student Information");
        System.out.println(StrUtil.duplicateStr("-", 20));
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
    }

    /**
     * A hash for the object to be used with HashMap.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    /**
     * Compares if two stduents are equals.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.ID, other.ID);
    }

    /**
     * Compares two Students.
     * @param o
     * @return positive, zero, or negative value.
     */
    @Override
    public int compareTo(Student o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return ID.compareToIgnoreCase(o.ID);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-60s", 
                ID, StrUtil.formatStr(name, 60));
    }

    @Override
    public Object clone() {
        return new Student(ID, name);
    }
    
    /**
     * Reads a Student from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.ID = ois.readUTF();
        this.name = ois.readUTF();
    }

    /**
     * Writes a Student to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeUTF(ID);
        oos.writeUTF(name);
    }
}
