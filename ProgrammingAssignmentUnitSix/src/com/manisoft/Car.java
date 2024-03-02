
package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author manianis
 */
public class Car extends VehicleBase implements CarVehicle {
    
    private int numDoors;
    private FuelType fuelType;

    public Car() {
        this(6, FuelType.PETROL, "", "", 2024);
    }

    public Car(int numDoors, FuelType fuelType, String make, String model, int year) {
        super(VehicleType.CAR, make, model, year);
        this.numDoors = numDoors;
        this.fuelType = fuelType;
    }

    @Override
    public int getNumberOfDoors() {
        return numDoors;
    }

    @Override
    public void setNumberOfDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public void setFuelType(FuelType fuel) {
        this.fuelType = fuel;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Number of doors: " + numDoors);
        System.out.println("Fuel type: " + fuelType);
    }

    @Override
    public void input() {
        super.input();
        numDoors = InputUtil.enterNumber("Number of doors? ",
                numDoors, 2, 10);
        int ft = InputUtil.enterNumber("Fuel type (0: Petrol, "
                + "1: Diesel, 2: Electric)? ", fuelType.ordinal(), 0, 2);
        fuelType = FuelType.valueOf(ft);
    }

    @Override
    public void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException {
        super.readObject(ois);
        numDoors = ois.readInt();
        fuelType = FuelType.valueOf(ois.readInt());
    }

    @Override
    public void writeObject(ObjectOutputStream oos) 
            throws IOException, ClassNotFoundException {
        super.writeObject(oos);
        oos.writeInt(numDoors);
        oos.writeInt(fuelType.ordinal());
    }
    
    
}
