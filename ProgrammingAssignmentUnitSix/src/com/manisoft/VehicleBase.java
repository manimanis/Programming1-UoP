package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Provides an implementation of the Vehicle interface serving as a base class
 * for all types of vehicles.
 *
 * @author manianis
 */
public abstract class VehicleBase implements Vehicle {

    protected VehicleType vehicleType;
    protected String make;
    protected String model;
    protected int year;

    /**
     * Creates a vehicle from the user input.
     *
     * @param vehicleType
     * @return a Vehicle object
     */
    public static Vehicle createVehicle(VehicleType vehicleType) {
        Vehicle vehicle = switch (vehicleType) {
            case CAR ->
                new Car();
            case MOTORCYCLE ->
                new Motorcycle();
            case TRUCK ->
                new Truck();
            default ->
                null;
        };
        if (vehicle != null) {
            vehicle.input();
        }
        return vehicle;
    }

    /**
     * Ctor.
     *
     * @param vehicleType
     * @param make
     * @param model
     * @param year
     */
    public VehicleBase(VehicleType vehicleType, String make,
            String model, int year) {
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * Vehicle type getter.
     *
     * @return VehicleType
     */
    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    /**
     * Make getter.
     *
     * @return String
     */
    @Override
    public String getMake() {
        return make;
    }

    /**
     * Make setter.
     *
     * @param make String
     */
    @Override
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Model getter.
     *
     * @return String
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * Model setter.
     *
     * @param model String
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Year Manufacture getter.
     *
     * @return int
     */
    @Override
    public int getYearManufacture() {
        return year;
    }

    /**
     * Year Manufacture setter.
     *
     * @param year int
     */
    @Override
    public void setYearManufacture(int year) {
        this.year = year;
    }

    /**
     * Create a clone from the current Vehicle using the object's implementation
     * of clone.
     *
     * @return Vehicle
     */
    @Override
    public abstract Vehicle clone();

    /**
     * Provides a hash for a Vehicle usable in HashMaps.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.vehicleType);
        hash = 37 * hash + Objects.hashCode(this.make);
        hash = 37 * hash + Objects.hashCode(this.model);
        hash = 37 * hash + this.year;
        return hash;
    }

    /**
     * Check if this object is equal to another object.
     *
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleBase other = (VehicleBase) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        return this.vehicleType == other.vehicleType;
    }

    /**
     * Provides a nice formatted representation of the Vehicle.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%-5s %-20s %-20s %d",
                StrUtil.left(vehicleType.toString(), 5),
                make, model, year);
    }

    /**
     * Displays Vehicle's information.
     */
    @Override
    public void display() {
        System.out.println("\n--- " + vehicleType + " Information ---\n");
        System.out.println("Make: " + make);
        System.out.println("Model:" + model);
        System.out.println("Manufacture year: " + year);
    }

    /**
     * Inputs Vehicle's information.
     */
    @Override
    public void input() {
        System.out.println("\n--- Enter " + vehicleType + " Information ---\n");
        make = InputUtil.enterString("Make? ", make, 2, 64);
        model = InputUtil.enterString("Model? ", model, 2, 64);
        year = InputUtil.enterNumber("Manufacture Year? ", year, 2000, 2024);
    }

    /**
     * Provides object streaming of a Vehicle object.
     *
     * @param ois ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        vehicleType = VehicleType.valueOf(ois.readInt());
        make = ois.readUTF();
        model = ois.readUTF();
        year = ois.readInt();
    }

    /**
     * Provides object streaming of Vehicle object.
     *
     * @param oos ObjectOutputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        oos.writeInt(vehicleType.ordinal());
        oos.writeUTF(make);
        oos.writeUTF(model);
        oos.writeInt(year);
    }
}
