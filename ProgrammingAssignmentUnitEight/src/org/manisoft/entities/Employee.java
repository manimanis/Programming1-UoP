package org.manisoft.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    public Employee(HashMap<String, String> data) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        ID = data.get("Employee ID"); // Employee ID
        fullName = data.get("Full Name"); // 
        jobTitle = data.get("Job Title"); // 
        department = data.get("Department"); // Department
        businessUnit = data.get("Business Unit"); // Business Unit
        gender = data.get("Gender"); // Gender
        ethnicity = data.get("Ethnicity"); // Ethnicity
        age = Integer.parseInt("0"+data.get("Age")); // Age
        try {
            // Hire Date
            String temp = data.getOrDefault("Hire Date", "");
            hireDate = !temp.isEmpty() ? sdf.parse(temp) : new Date();
        } catch (ParseException ex) {
            hireDate = new Date(9999, 11, 31);
        }
        annualSalary = Double.parseDouble(data.get("Annual Salary")); // Annual Salary
        bonus = Double.parseDouble(data.get("Bonus %")); // Bonus %
        country = data.get("Country"); // Country
        city = data.get("City"); // City
        try {
            // Exit Date
            String temp = data.getOrDefault("Exit Date", "");
            exitDate = !temp.isEmpty() ? sdf.parse(temp) : new Date();
        } catch (ParseException ex) {
            exitDate = new Date(9999, 11, 31);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %-32s %-16s %10.2f",
                ID, fullName, department, annualSalary);
    }
}
