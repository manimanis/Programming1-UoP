package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a Car object by extending VehicleBase class and implementing
 * CarVehicle interface.
 *
 * @author manianis
 */
public class Car extends VehicleBase implements CarVehicle {

    private int numDoors;
    private FuelType fuelType;

    /**
     * Default ctor.
     */
    public Car() {
        this(6, FuelType.PETROL, "", "", 2024);
    }

    /**
     * Alternate Ctor.
     *
     * @param numDoors int
     * @param fuelType FuelType
     * @param make String
     * @param model String
     * @param year int
     */
    public Car(int numDoors, FuelType fuelType, String make, String model, int year) {
        super(VehicleType.CAR, make, model, year);
        this.numDoors = numDoors;
        this.fuelType = fuelType;
    }

    /**
     * Number of doors getter.
     *
     * @return int
     */
    @Override
    public int getNumberOfDoors() {
        return numDoors;
    }

    /**
     * Number of doors setter.
     *
     * @param numDoors int
     */
    @Override
    public void setNumberOfDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    /**
     * Fuel type getter.
     *
     * @return FuelType
     */
    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * Fuel type setter.
     *
     * @param fuel FuelType
     */
    @Override
    public void setFuelType(FuelType fuel) {
        this.fuelType = fuel;
    }

    /**
     * Displays Car's information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Number of doors: " + numDoors);
        System.out.println("Fuel type: " + fuelType);
    }

    /**
     * Inputs Car's information.
     */
    @Override
    public void input() {
        super.input();
        numDoors = InputUtil.enterNumber("Number of doors? ",
                numDoors, 2, 10);
        int ft = InputUtil.enterNumber("Fuel type (0: Petrol, "
                + "1: Diesel, 2: Electric)? ", fuelType.ordinal(), 0, 2);
        fuelType = FuelType.valueOf(ft);
    }

    /**
     * Create a clone from the current Car.
     *
     * @return Car
     */
    @Override
    public Car clone() {
        return new Car(numDoors, fuelType, make, model, year);
    }

    /**
     * Provides object streaming of a Car object.
     *
     * @param ois ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        super.readObject(ois);
        numDoors = ois.readInt();
        fuelType = FuelType.valueOf(ois.readInt());
    }

    /**
     * Provides object streaming of car object.
     *
     * @param oos ObjectOutputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        super.writeObject(oos);
        oos.writeInt(numDoors);
        oos.writeInt(fuelType.ordinal());
    }
}
