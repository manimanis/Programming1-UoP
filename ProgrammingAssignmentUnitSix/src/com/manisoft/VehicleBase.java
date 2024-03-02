package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 *
 * @author manianis
 */
public class VehicleBase implements Vehicle {

    protected VehicleType vehicleType;
    protected String make;
    protected String model;
    protected int year;

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

    public VehicleBase(VehicleType vehicleType, String make, String model, int year) {
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getYearManufacture() {
        return year;
    }

    @Override
    public void setYearManufacture(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.vehicleType);
        hash = 37 * hash + Objects.hashCode(this.make);
        hash = 37 * hash + Objects.hashCode(this.model);
        hash = 37 * hash + this.year;
        return hash;
    }

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

    @Override
    public String toString() {
        return String.format("%c %-20s %-20s %d", 
                vehicleType.toString().charAt(0),
                make, model, year);
    }
    
    @Override
    public void display() {
        System.out.println("\n--- " + vehicleType + " Information ---\n");
        System.out.println("Make: " + make);
        System.out.println("Model:" + model);
        System.out.println("Manufacture year: " + year);
    }

    @Override
    public void input() {
        System.out.println("\n--- Enter " + vehicleType + " Information ---\n");
        make = InputUtil.enterString("Make? ", make, 3, 64);
        model = InputUtil.enterString("Model? ", model, 3, 64);
        year = InputUtil.enterNumber("Manufacture Year? ", year, 2000, 2024);
    }

    @Override
    public void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException {
        vehicleType = VehicleType.valueOf(ois.readInt());
        make = ois.readUTF();
        model = ois.readUTF();
        year = ois.readInt();
    }

    @Override
    public void writeObject(ObjectOutputStream oos) 
            throws IOException, ClassNotFoundException {
        oos.writeInt(vehicleType.ordinal());
        oos.writeUTF(make);
        oos.writeUTF(model);
        oos.writeInt(year);
    }

}
