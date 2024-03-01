
package com.manisoft;

import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class ProgrammingAssignmentUnitSix {
    
    private DistinctArrayList<Vehicle> vehicles;
    
    public void mainMenu() {
        vehicles.add(AbstractVehicle.createVehicle(VehicleType.CAR));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ProgrammingAssignmentUnitSix().mainMenu();
    }
    
}
