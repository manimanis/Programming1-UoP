package org.manisoft.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

    public Employee(String ID, String fullName, String jobTitle, String department, String businessUnit, String gender, String ethnicity, int age, Date hireDate, double annualSalary, double bonus, String country, String city, Date exitDate) {
        this.ID = ID;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.businessUnit = businessUnit;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.age = age;
        this.hireDate = hireDate;
        this.annualSalary = annualSalary;
        this.bonus = bonus;
        this.country = country;
        this.city = city;
        this.exitDate = exitDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    @Override
    public String toString() {
        return String.format("%s %-32s %-16s %10.2f",
                ID, fullName, department, annualSalary);
    }

    public static Employee createFromHashMap(HashMap<String, String> data) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String ID = data.get("Employee ID"); // Employee ID
        String fullName = data.get("Full Name"); // 
        String jobTitle = data.get("Job Title"); // 
        String department = data.get("Department"); // Department
        String businessUnit = data.get("Business Unit"); // Business Unit
        String gender = data.get("Gender"); // Gender
        String ethnicity = data.get("Ethnicity"); // Ethnicity
        int age = Integer.parseInt("0" + data.get("Age")); // Age
        Date hireDate;
        try {
            // Hire Date
            String temp = data.getOrDefault("Hire Date", "");
            hireDate = !temp.isEmpty() ? sdf.parse(temp) : new Date();
        } catch (ParseException ex) {
            hireDate = new Date(9999, 11, 31);
        }
        Double annualSalary = Double.parseDouble(data.get("Annual Salary")); // Annual Salary
        Double bonus = Double.parseDouble(data.get("Bonus %")); // Bonus %
        String country = data.get("Country"); // Country
        String city = data.get("City"); // City
        Date exitDate;
        try {
            // Exit Date
            String temp = data.getOrDefault("Exit Date", "");
            exitDate = !temp.isEmpty() ? sdf.parse(temp) : new Date();
        } catch (ParseException ex) {
            exitDate = new Date(9999, 11, 31);
        }
        return new Employee(ID, fullName, jobTitle, department,
                businessUnit, gender, ethnicity, age, hireDate,
                annualSalary, bonus, country, city, exitDate);
    }
}
