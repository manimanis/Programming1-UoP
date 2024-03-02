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

    public ProgrammingAssignmentUnitSix() {
        loadData();
        if (vehicleList.isEmpty()) {
            vehicleList.add(new Car(6, FuelType.PETROL, "BMW", "X6", 2024));
            vehicleList.add(new Motorcycle(2, MotorcycleType.OFF_ROAD, "Suzuki", "Raider J Crossover", 2024));
            vehicleList.add(new Truck(0.760, TransmissionType.MANUAL, "Ford", "Maverick", 2024));
            vehicleList.add(new Car(6, FuelType.PETROL, "BMW", "e46", 2024));
            vehicleList.add(new Motorcycle(2, MotorcycleType.OFF_ROAD, "Suzuki", "Raider R150", 2024));
            vehicleList.add(new Car(6, FuelType.ELECTRIC, "Audi", "A3", 2024));
            vehicleList.add(new Car(6, FuelType.ELECTRIC, "Ford", "Bronco Sport", 2023));
            vehicleList.add(new Car(6, FuelType.ELECTRIC, "Porsche", "Cayenne", 2024));
            vehicleList.add(new Truck(3.5, TransmissionType.AUTOMATIC, "Chevrolet", "Silverado 2500HD", 2024));
            vehicleList.add(new Truck(2.6, TransmissionType.AUTOMATIC, "GMC", "Sierra 1500", 2023));
            saveData();
        }
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Main Menu ---\n");
            System.out.println("1. List of Vehicles");
            System.out.println("2. List of Cars");
            System.out.println("3. List of Motorcycles");
            System.out.println("4. List of Trucks");
            System.out.println("5. Add New Vehicle");
            System.out.println("6. Change Vehicle Information");
            System.out.println("7. Delete Vehicle");
            System.out.println("");
            System.out.println("0. Exit");

            choice = InputUtil.enterNumber("Your choice ? ",
                    0, 10);
            switch (choice) {
                case 1 ->
                    vehicleList.displayVehicles();
                case 2 ->
                    vehicleList.displayVehiclesByType(VehicleType.CAR);
                case 3 ->
                    vehicleList.displayVehiclesByType(VehicleType.MOTORCYCLE);
                case 4 ->
                    vehicleList.displayVehiclesByType(VehicleType.TRUCK);
                case 5 ->
                    addNewVehicle();
                case 6 ->
                    changeVehicleInformation();
                case 7 ->
                    deleteVehicle();
            }
        } while (choice != 0);
        System.out.println("See you soon, goodbye!");
    }

    public void addNewVehicle() {
        System.out.println("\n--- Add New Vehicle ---\n");
        int option = InputUtil.enterNumber(
                "Vehicle Types\n"
                + "1. " + VehicleType.TYPES[0] + "\n"
                + "2. " + VehicleType.TYPES[1] + "\n"
                + "3. " + VehicleType.TYPES[2] + "\n"
                + "0. Cancel operation\n"
                + "[0, 3]? ", 0, 0, 3);
        if (option == 0) {
            System.out.println("Operation canceled!");
            return;
        }
        VehicleType vt = VehicleType.valueOf(option - 1);
        Vehicle vehicle = VehicleBase.createVehicle(vt);

        while (vehicleList.contains(vehicle)) {
            System.out.println("Unfortunately this vehicle exists!");
            char ans = InputUtil.promptContinue(
                    "Do you like to re-edit this vehicle (Y/N)? ",
                    "YN", 'Y');
            if (ans == 'N') {
                System.out.println("Operation canceled!");
                return;
            } else {
                vehicle.input();
            }
        }

        vehicle.display();
        if (vehicleList.add(vehicle)) {
            System.out.println("Vehicle inserted successfully!");
            saveData();
        }
    }

    public void changeVehicleInformation() {
        System.out.println("\n--- Add New Vehicle ---\n");
        int index = InputUtil.enterNumber(
                "Enter Vehicle Index [1, " + vehicleList.size() + "]? ",
                0, 0, vehicleList.size()) - 1;
        if (index == -1) {
            System.out.println("Operation canceled!");
            return;
        }
        Vehicle vehicle = vehicleList.get(index);
        int pos;
        do {
            vehicle.input();
            pos = vehicleList.indexOf(vehicle);
            if (pos != index) {
                System.out.println("Unfortunately this vehicle exists!");
                char ans = InputUtil.promptContinue(
                        "Do you like to re-edit this vehicle (Y/N)? ",
                        "YN", 'Y');
                if (ans == 'N') {
                    System.out.println("Operation canceled!");
                    return;
                }
            }
        } while (pos != -1 && pos != index);
        vehicleList.set(index, vehicle);
        System.out.println("Vehicle changed successfully!");
        saveData();
    }

    public void deleteVehicle() {
        System.out.println("\n--- Delete Vehicle ---\n");
        int index = InputUtil.enterNumber(
                "Enter Vehicle Index [1, " + vehicleList.size() + "]? ",
                0, 0, vehicleList.size()) - 1;
        if (index == -1) {
            System.out.println("Operation canceled!");
            return;
        }
        Vehicle vehicle = vehicleList.get(index);
        vehicle.display();
        char ans = InputUtil.promptContinue(
                "Do you like want to delete this vehicle (Y/N)? ",
                "YN", 'N');
        if (ans == 'N') {
            System.out.println("Operation canceled!");
            return;
        }
        vehicleList.removeAt(index);
        System.out.println("Vehicle deleted successfully!");
        saveData();
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
