/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manisoft;

import java.util.Calendar;

/**
 *
 * @author manianis
 */
public class Student {
    private String id;
    private String name;
    private Calendar dateOfBirth;
    private int age;
    private int grade;

    public Student() {
        this("", "", Calendar.getInstance(), 0);
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
        age = (int)((today.getTimeInMillis() - dateOfBirth.getTimeInMillis()) / 365250);
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
        return String.format("ID: %s\nName: %s\nAge: %d\nGrade: %d", 
                id, name, age, grade);
    }

    @Override
    protected Student clone() {
        return new Student(id, name, dateOfBirth, grade);
    }
    
    
}
