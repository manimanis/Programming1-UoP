package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a Truck object by extending VehicleBase class and implementing
 * TruckVehicle interface.
 *
 * @author manianis
 */
public class Truck extends VehicleBase implements TruckVehicle {

    private double capacity;
    private TransmissionType transmission;

    /**
     * Default Ctor.
     */
    public Truck() {
        this(5.5, TransmissionType.MANUAL, "", "", 2024);
    }

    /**
     * Alternate Ctor.
     *
     * @param capacity double
     * @param transmission TransmissionType
     * @param make String
     * @param model String
     * @param year int
     */
    public Truck(double capacity, TransmissionType transmission, String make, String model, int year) {
        super(VehicleType.TRUCK, make, model, year);
        this.capacity = capacity;
        this.transmission = transmission;
    }

    /**
     * Load capacity getter.
     *
     * @return double
     */
    @Override
    public double getCapacity() {
        return capacity;
    }

    /**
     * Load capacity setter.
     *
     * @param capacity double
     */
    @Override
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Transmission type getter.
     *
     * @return TransmissionType
     */
    @Override
    public TransmissionType getTransmissionType() {
        return transmission;
    }

    /**
     * Transmission type setter.
     *
     * @param transmission TransmissionType
     */
    @Override
    public void setTransmissionType(TransmissionType transmission) {
        this.transmission = transmission;
    }

    /**
     * Displays Truck's information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Capacity (tons): " + capacity);
        System.out.println("Transmission type: " + transmission);
    }

    /**
     * Inputs Truck's information.
     */
    @Override
    public void input() {
        super.input();
        capacity = InputUtil.enterNumber("Capacity (Kg)? ",
                (int) (capacity * 1000), 1000, 60000) / 1000.0;
        int tt = InputUtil.enterNumber(
                "Transmission Type (0: Manual, 1: Automatic)? ",
                transmission.ordinal(), 0, 1);
        transmission = TransmissionType.valueOf(tt);
    }

    /**
     * Create a clone from the current Truck.
     *
     * @return Truck
     */
    @Override
    public Truck clone() {
        return new Truck(capacity, transmission, make, model, year);
    }

    /**
     * Provides object streaming of a Truck object.
     *
     * @param ois ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        super.readObject(ois);
        capacity = ois.readDouble();
        transmission = TransmissionType.valueOf(ois.readInt());
    }

    /**
     * Provides object streaming of Truck object.
     *
     * @param oos ObjectOutputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        super.writeObject(oos);
        oos.writeDouble(capacity);
        oos.writeInt(transmission.ordinal());
    }
}
