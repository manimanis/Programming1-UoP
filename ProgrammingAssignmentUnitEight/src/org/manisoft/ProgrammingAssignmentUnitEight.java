package org.manisoft;

import java.util.HashMap;
import org.manisoft.entities.Employee;

/**
 *
 * @author Cyberbox
 */
public class ProgrammingAssignmentUnitEight {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSVReader csv = new CSVReader(
                ProgrammingAssignmentUnitEight.class
                        .getResourceAsStream(
                                "./EmployeeSampleData1.csv"));
        for (HashMap<String, String> data : csv) {
            System.out.println(new Employee(data));
        }
        
    }

}
