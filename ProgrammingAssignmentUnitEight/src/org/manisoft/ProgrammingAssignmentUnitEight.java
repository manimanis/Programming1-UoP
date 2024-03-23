package org.manisoft;

import org.manisoft.csv.CSVReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import org.manisoft.entities.DescriptiveStatistics;
import org.manisoft.entities.DistributionByCountry;
import org.manisoft.entities.DistributionByCountryAndGender;
import org.manisoft.entities.Employee;
import org.manisoft.entities.Pair;

/**
 *
 * @author Cyberbox
 */
public class ProgrammingAssignmentUnitEight {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Employee> employees = readEmployees();
        System.out.println(employees.size()
                + " employees read from the dataset\n");

        // Find the company's departments
        List<String> departments = employees.stream()
                .map((emp) -> emp.getDepartment())
                .distinct()
                .sorted()
                .toList();

        // Find the employees of each department and sort them
        // according to their respective names
        Comparator<Employee> nameComparator
                = (e1, e2) -> e1.getFullName()
                        .compareToIgnoreCase(e2.getFullName());
        List<List<Employee>> emplByDept = departments.stream()
                .map((department) -> {
                    return employees.stream()
                            .filter((emp)
                                    -> emp.getDepartment()
                                    .equalsIgnoreCase(department))
                            .sorted(nameComparator)
                            .toList();
                })
                .toList();

        // Display employees names and their departments
        displayEmployeesNamesAndDept(employees);

        // Display the average salary and
        // and the number of employees who earn less than average
        displayAverageSalary(employees);

        // Display the average, minimal, and maximal age of all employees
        DescriptiveStatistics ageStatistics = new DescriptiveStatistics(
                employees, (emp) -> emp.getAge());
        displayAgesStatistics(ageStatistics);

        // Display salaries per age
        displaySalaryPerAgeRange(ageStatistics, employees);

        // Display the company's departments
        displayDepartments(departments);

        // Display all employees salaries in all departments
        displayAllDepartmentsEmployeesSalaries(emplByDept);

        // Display employees earnings distribution by country
        displayEmployeeDistributionByCountry(employees);

        // Display employees earnings distribution by country and gender
        displayEmployeeDistributionByGenderAndCountry(employees);
    }

    private static void displayEmployeesNamesAndDept(
            List<Employee> employees) {
        // Using Function interface to concatenate employee fullname
        // and departement into a string.
        Function<Employee, String> employeeDepartment = (e)
                -> String.format("%-20s %-15s",
                        e.getFullName(), e.getDepartment());

        // Display the list of employees and their respective Departements
        // The employees are sorted according to their departments.
        employees.stream()
                .sorted((e1, e2) -> {
                    int cmp = e1.getDepartment()
                            .compareToIgnoreCase(e2.getDepartment());
                    if (cmp != 0) {
                        return cmp;
                    }
                    return e1.getFullName()
                            .compareToIgnoreCase(e2.getFullName());
                })
                .map(employeeDepartment)
                .forEach((text) -> System.out.println(text));
        System.out.println();
    }

    private static void displayAverageSalary(List<Employee> employees) {
        // Find the average salary of all employees
        // Find how many employees touch below the average salary
        // and how many above
        final double avgSalary = employees.stream()
                .mapToDouble((emp) -> emp.getAnnualSalary())
                .average()
                .getAsDouble();
        int countBelowAvg = (int) employees.stream()
                .mapToDouble((emp) -> emp.getAnnualSalary())
                .filter((salary) -> salary < avgSalary)
                .count();
        int countAboveAvg = (int) employees.stream()
                .mapToDouble((emp) -> emp.getAnnualSalary())
                .filter((salary) -> salary >= avgSalary)
                .count();
        System.out.println("Salaries");
        System.out.println(repStr("-", 30));
        System.out.printf(
                "Average salary of all employees is: $%2.2f\n",
                avgSalary);
        System.out.println();
        System.out.printf("%d employees below average salary.\n",
                countBelowAvg);
        System.out.printf("%d employees above average salary.\n",
                countAboveAvg);
        System.out.println();
    }

    /**
     * Display employees salaries statistics.
     *
     * @param stat
     */
    private static void displaySalariesStatistics(
            DescriptiveStatistics stat) {
        System.out.println("Statistics");
        System.out.printf("- Count         : %10d\n",
                stat.count());
        System.out.printf("- Average Salary: %10.2f$\n",
                stat.getAvg());
        System.out.printf("- Sta. Deviation: %10.2f$\n",
                stat.getStd());
        System.out.printf("- Minimum Salary: %10.2f$\n",
                stat.getMin());
        System.out.printf("- Maximum Salary: %10.2f$\n",
                stat.getMax());
    }

    /**
     * Display employees salaries per department and the employees salaries
     * statistics in the department.
     *
     * @param dept
     * @param listEmp
     * @param stat
     */
    private static void displayDepartmentSalariesStatistics(String dept,
            List<Employee> listEmp, DescriptiveStatistics stat) {
        // Concatenate employee's name and annual salary
        Function<Employee, String> employeeSalary = (emp)
                -> String.format("%-20s %10.2f",
                        emp.getFullName(), emp.getAnnualSalary());

        System.out.println(dept + " department");
        System.out.println(repStr("-", 30));
        listEmp.stream()
                .map(employeeSalary)
                .forEach((empSal) -> System.out.println(empSal));
        displaySalariesStatistics(stat);
        System.out.println();
    }

    private static void displayAllDepartmentsEmployeesSalaries(
            List<List<Employee>> emplByDept) {
        System.out.println("List of employee in each department");
        System.out.println(repStr("-", 40));
        System.out.println();
        emplByDept.stream()
                .forEach((listEmp) -> {
                    DescriptiveStatistics stat
                            = new DescriptiveStatistics(listEmp,
                                    (emp) -> emp.getAnnualSalary());
                    displayDepartmentSalariesStatistics(
                            listEmp.get(0).getDepartment(),
                            listEmp, stat
                    );
                });
    }

    /**
     * Display employees ages statistics.
     *
     * @param stat
     */
    private static void displayAgesStatistics(DescriptiveStatistics stat) {
        System.out.println("Ages");
        System.out.println(repStr("-", 30));
        System.out.printf("Minimal Age: %d\n", (int) stat.getMin());
        System.out.printf("Maximal Age: %d\n", (int) stat.getMax());
        System.out.printf("Average Age: %d\n", (int) stat.getAvg());
        System.out.println();
    }

    /**
     * Display average salary per age interval.
     *
     * @param ageStat
     * @param employees
     */
    private static void displaySalaryPerAgeRange(
            DescriptiveStatistics ageStat, List<Employee> employees) {
        // Divide the age range to five ranges
        int step = (int) (ageStat.getMax() - ageStat.getMin() + 1) / 5;
        for (int i = 0; i < 5; i++) {
            final int sa = (int) ageStat.getMin() + i * step,
                    ea = sa + step;
            double avgSalaryByAge = employees.stream()
                    .filter((emp) -> emp.getAge() >= sa && emp.getAge() < ea)
                    .mapToDouble((emp) -> emp.getAnnualSalary())
                    .average()
                    .getAsDouble();
            System.out.printf(
                    "Average Salary for ages between [%d, %d[ is $%2.2f\n",
                    sa, ea, avgSalaryByAge);
        }
        System.out.println();
    }

    /**
     * Display departments list
     *
     * @param departments
     */
    private static void displayDepartments(List<String> departments) {
        System.out.println("Companie's departments");
        System.out.println(repStr("-", 30));
        departments.stream()
                .forEach((dep) -> System.out.println(dep));
        System.out.println();
    }

    /**
     * Display the employees distrubtion by country.
     *
     * @param employees
     */
    private static void displayEmployeeDistributionByCountry(
            List<Employee> employees) {
        System.out.println("Salaries By Country\n");
        System.out.println("Countries        Empl.        Min.         Max.      Average");
        System.out.println(repStr("-", 60));
        List<String> countries = employees.stream()
                .map((emp) -> emp.getCountry())
                .distinct()
                .sorted()
                .toList();
        countries.stream()
                .map((country) -> new DistributionByCountry(employees, country))
                .forEach((distribution)
                        -> System.out.printf(
                        "%-15s %6d %10.2f$  %10.2f$  %10.2f$\n",
                        distribution.country,
                        distribution.stats.count(),
                        distribution.stats.getMin(),
                        distribution.stats.getMax(),
                        distribution.stats.getAvg()
                ));
        System.out.println();
    }

    private static void displayEmployeeDistributionByGenderAndCountry(
            ArrayList<Employee> employees) {
        System.out.println("Salaries By Country And Gender\n");
        System.out.println("Countries       Gender  Empl.        Min.         Max.      Average");
        System.out.println(repStr("-", 60));
        employees.stream()
                .map((emp) -> new Pair<String, String>(emp.getCountry(), emp.getGender()))
                .distinct()
                .sorted()
                .map((pair) -> new DistributionByCountryAndGender(employees,
                pair.first, pair.second))
                .forEach((distribution) -> System.out.printf(
                "%-15s %-6s %6d %10.2f$  %10.2f$  %10.2f$\n",
                distribution.country,
                distribution.gender,
                distribution.stats.count(),
                distribution.stats.getMin(),
                distribution.stats.getMax(),
                distribution.stats.getAvg()
        ));

    }

    /**
     * Reads employees data from the CSV file.
     *
     * @return ArrayList<Employee>
     */
    private static ArrayList<Employee> readEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        CSVReader csv = new CSVReader(
                ProgrammingAssignmentUnitEight.class
                        .getResourceAsStream("./EmployeeSampleData1.csv")
        );
        for (HashMap<String, String> data : csv) {
            employees.add(Employee.createFromHashMap(data));
        }
        return employees;
    }

    private static String repStr(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

}
