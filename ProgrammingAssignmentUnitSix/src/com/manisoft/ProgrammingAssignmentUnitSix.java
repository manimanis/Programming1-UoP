
package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class ProgrammingAssignmentUnitSix {
    
    private String filePath = "Vehicles.obj";
    VehicleList vehicleList = new VehicleList();
        
    public void mainMenu() {
        loadData();
        if (vehicleList.isEmpty()) {
            vehicleList.add(new Car(6, FuelType.PETROL, "BMW", "X6", 2024));
            vehicleList.add(new Car(6, FuelType.PETROL, "BMW", "e46", 2024));
            // saveData();
        }
        vehicleList.displayVehicleByType(VehicleType.CAR);
        vehicleList.displayVehicleByType(VehicleType.MOTORCYCLE);
        vehicleList.displayVehicleByType(VehicleType.TRUCK);
    }
    
       /**
     * Load data from a file.
     */
    public void loadData() {
        ObjectInputStream ois = null;
        File fichier = new File(filePath);
        if (!fichier.exists()) {
            return;
        }
        try {
            ois = new ObjectInputStream(new FileInputStream(fichier));
            vehicleList = (VehicleList) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Saves data to a file.
     */
    public void saveData() {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(vehicleList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ProgrammingAssignmentUnitSix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ProgrammingAssignmentUnitSix().mainMenu();
    }
    
}
