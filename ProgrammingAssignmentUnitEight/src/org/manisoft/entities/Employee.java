package org.manisoft.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyberbox
 */
public class Employee {

    private String ID;
    private String fullName;
    private String jobTitle;
    private String department;
    private String businessUnit;
    private String gender;
    private String ethnicity;
    private int age;
    private Date hireDate;
    private double annualSalary;
    private double bonus;
    private String country;
    private String city;
    private Date exitDate;

    public Employee() {
    }

    public Employee(String[] line) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        for (int i = 0; i < line.length; i++) {
            String item = line[i];
            System.out.print(i + ":" + item + ",");
        }
        System.out.println();
        ID = line[0]; // Employee ID
        fullName = line[1]; // Full Name
        jobTitle = line[2]; // Job Title
        department = line[3]; // Department
        businessUnit = line[4]; // Business Unit
        gender = line[5]; // Gender
        ethnicity = line[6]; // Ethnicity
        age = Integer.parseInt(line[7]); // Age
        try {
            // Hire Date
            hireDate = !line[8].isEmpty() ? sdf.parse(line[8]) : new Date();
        } catch (ParseException ex) {
            Logger.getLogger(Employee.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        annualSalary = 0.0; // Annual Salary
        bonus = 0.0; // Bonus %
        country = line[11]; // Country
        city = line[12]; // City
        try {
            // Exit Date
            exitDate = !line[13].isEmpty() ? sdf.parse(line[13]) : new Date();
        } catch (ParseException ex) {
            Logger.getLogger(Employee.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %-32s %-16s",
                ID, fullName, department);
    }
}
