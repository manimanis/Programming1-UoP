
package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class VehicleList implements Serializable {
    
    public static final int PAGE = 10;
    
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final int[] vehicleCount;

    public VehicleList() {
        vehicleCount = new int[VehicleType.TYPES.length];
    }

    public boolean add(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            return false;
        }
        vehicleCount[vehicle.getType().ordinal()]++;
        return vehicles.add(vehicle);
    }

    public Vehicle removeAt(int index) {
        Vehicle vehicle = null;
        if (index >= 0 && index < vehicles.size()) {
            vehicle = vehicles.remove(index);
            vehicleCount[vehicle.getType().ordinal()]--;
        }
        return vehicle;
    }
    
    boolean isEmpty() {
        return vehicles.isEmpty();
    }

    public int size() {
        return vehicles.size();
    }

    public int getVehicleCount(VehicleType type) {
        return vehicleCount[type.ordinal()];
    }

    public Vehicle get(int index) {
        if (index >= 0 && index < vehicles.size()) {
            return vehicles.get(index);
        }
        return null;
    }

    public void set(int index, Vehicle vehicle) {
        if (index >= 0 && index < vehicles.size()) {
            Vehicle oldVehicle = vehicles.get(index);
            vehicleCount[oldVehicle.getType().ordinal()]--;
            vehicles.set(index, vehicle);
            vehicleCount[vehicle.getType().ordinal()]++;
        }
    }

    public void displayVehiclesByType(VehicleType type, int start, int end) {
        int j = -1;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getType() == type) {
                j++;
                if (j >= start) {
                    System.out.println(v);
                }
                if (j == end - 1) {
                    return;
                }
            }
        }
    }
    
    public void displayVehicleByType(VehicleType type) {
        int count = vehicleCount[type.ordinal()];
        int numPages = count / PAGE + ((count % PAGE != 0) ? 1 : 0);
        System.out.println("\n--- List of " + type + "s ---\n");
        if (count == 0) {
            System.out.println("No " + type + "s!");
        } else {
            System.out.println(count + " " + type + "(s)");
        }
        for (int i = 0; i < numPages; i++) {
            int start = i * PAGE, end = start + PAGE;
            if (end > count) {
                end = count;
            }
            System.out.println("Page " + (i+1) + "/" + numPages);
            displayVehiclesByType(type, start, end);
            if (i + 1 < numPages) {
                char ans = InputUtil.promptContinue(
                        "Continue (Y/N)? ", "YN", 'Y');
            }
        }
    }
    
    public void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException {
        int count = ois.readInt();
        vehicles.clear();
        for (int i = 0; i < count; i++) {
            vehicles.add((Vehicle) ois.readObject());
        }
    }

    public void writeObject(ObjectOutputStream oos) 
            throws IOException, ClassNotFoundException {
        oos.writeInt(vehicles.size());
        for (Vehicle vehicle : vehicles) {
            oos.writeObject(vehicle);
        }
    }

    
}
