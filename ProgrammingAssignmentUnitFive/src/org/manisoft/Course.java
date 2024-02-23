
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
public class Course implements Serializable, Comparable<Course> {
    private String code;
    private String name;
    private int capacity;
    private int gradesCount;

    public Course(String code, String name, int capacity, int gradesCount) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.gradesCount = gradesCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getGradesCount() {
        return gradesCount;
    }

    public void setGradesCount(int gradesCount) {
        this.gradesCount = gradesCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.code);
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
        final Course other = (Course) obj;
        return Objects.equals(this.code, other.code);
    }
    
    @Override
    public int compareTo(Course o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return code.compareToIgnoreCase(o.code);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-30s", code, StrUtil.formatStr(name, 30));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Course(code, name, capacity, gradesCount);
    }
    
    /**
     * Reads a Course from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.code = ois.readUTF();
        this.name = ois.readUTF();
        this.capacity = ois.readInt();
        this.gradesCount = ois.readInt();
    }

    /**
     * Writes a Course to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeUTF(code);
        oos.writeUTF(name);
        oos.writeInt(capacity);
        oos.writeInt(gradesCount);
    }
}
