/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public Student(String id) {
        this(id, "", Calendar.getInstance(), Integer.MIN_VALUE);
    }

    public Student(String id, String name, Calendar dateOfBirth, int grade) {
        this.id = id;
        this.name = name;
        setDateOfBirth(dateOfBirth);
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public final void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        calculateAge();
    }

    private void calculateAge() {
        Calendar today = Calendar.getInstance();
        age = (int) ((today.getTimeInMillis() - dateOfBirth.getTimeInMillis()) / 31557600000l);
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nName: %s\nAge: %d - Birth Date: %s\n"
                + "Grade: %d", id, name, age,
                StudentManagement.fromCalendar(dateOfBirth), grade);
    }

    @Override
    protected Student clone() {
        return new Student(id, name, dateOfBirth, grade);
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
