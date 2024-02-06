/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class StudentList {
    private final ArrayList<Student> students = new ArrayList<>();

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
}
