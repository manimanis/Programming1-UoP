
package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author manianis
 */
public class Student implements Serializable {

    private String id;
    private String name;
    private Calendar dateOfBirth;
    private int age;
    private int grade;

    /**
     * Contructs an empty new Student only from id.
     * @param id 
     */
    public Student(String id) {
        this(id, "", null, Integer.MIN_VALUE);
    }

    /**
     * Contstucts a new Student
     * @param id Student's ID
     * @param name Student's name
     * @param dateOfBirth Student's date fo birth
     * @param grade Student's grade
     */
    public Student(String id, String name, Calendar dateOfBirth, int grade) {
        this.id = id;
        this.name = name;
        setDateOfBirth(dateOfBirth);
        this.grade = grade;
    }

    /**
     * Returns the student's ID.
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the student's ID.
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the student's name.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the student's date of birth.
     * @return 
     */
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the student's date of birth.
     * @param dateOfBirth 
     */
    public final void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        calculateAge();
    }

    /**
     * Calculates the student's age in years based on his birth date.
     */
    private void calculateAge() {
        if (dateOfBirth != null) {
            Calendar today = Calendar.getInstance();
            age = (int) ((today.getTimeInMillis() - dateOfBirth.getTimeInMillis()) / 31557600000l);
        } else {
            age = 0;
        }
    }

    /**
     * Returns the student's age in years.
     * @return 
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the student's garde.
     * @return 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets the student's garde.
     * @param grade 
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Returns a formatted version of the Student object.
     * @return 
     */
    @Override
    public String toString() {
        return String.format("ID: %s\nName: %s\nAge: %d - Birth Date: %s\n"
                + "Grade: %d", id, name, age,
                StudentManagement.fromCalendar(dateOfBirth), grade);
    }

    /**
     * Create a clone of this Student object.
     * @return 
     */
    @Override
    protected Student clone() {
        return new Student(id, name, (Calendar) dateOfBirth.clone(), grade);
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
        this.id = ois.readUTF();
        this.name = ois.readUTF();
        this.dateOfBirth = (Calendar) ois.readObject();
        this.grade = ois.readInt();
    }

    /**
     * Writes a Student to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeUTF(id);
        oos.writeUTF(name);
        oos.writeObject(dateOfBirth);
        oos.writeInt(grade);
    }
}
