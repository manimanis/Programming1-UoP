
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

    public Student() {
        this("", "");
    }  

    public Student(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void display() {
        System.out.println("Student Information");
        System.out.println(StrUtil.duplicateStr("-", 20));
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.ID);
        return hash;
    }

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
