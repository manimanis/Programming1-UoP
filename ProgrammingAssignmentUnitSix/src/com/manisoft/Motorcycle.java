package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a Motorcycle object by extending VehicleBase class and
 * implementing MotorVehicle interface.
 *
 * @author manianis
 */
public class Motorcycle extends VehicleBase implements MotorVehicle {

    private int numWheels;
    private MotorcycleType type;

    /**
     * Default Ctor.
     */
    public Motorcycle() {
        this(2, MotorcycleType.SPORT, "", "", 2024);
    }

    /**
     * Alternate Ctor.
     *
     * @param numWeels int
     * @param type MotorcycleType
     * @param make String
     * @param model String
     * @param year int
     */
    public Motorcycle(int numWeels, MotorcycleType type, String make, String model, int year) {
        super(VehicleType.MOTORCYCLE, make, model, year);
        this.numWheels = numWeels;
        this.type = type;
    }

    /**
     * Number of wheels getter.
     *
     * @return int
     */
    @Override
    public int getNumberOfWheels() {
        return numWheels;
    }

    /**
     * Number of wheels setter.
     *
     * @param numWheels int
     */
    @Override
    public void setNumberOfWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    /**
     * Motor cycle type getter.
     *
     * @return MotorcycleType
     */
    @Override
    public MotorcycleType getMotorcycleType() {
        return type;
    }

    /**
     * Motor cycle type setter.
     *
     * @param type MotorcycleType
     */
    @Override
    public void setMotorcycleType(MotorcycleType type) {
        this.type = type;
    }

    /**
     * Displays Motorcycle's information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Number of wheels: " + numWheels);
        System.out.println("Motocycle type: " + type);
    }

    /**
     * Inputs Motorcycle's information.
     */
    @Override
    public void input() {
        super.input();
        numWheels = InputUtil.enterNumber("Number of wheels? ",
                numWheels, 1, 10);
        int mt = InputUtil.enterNumber(
                "Motorcycle Type (0: Sport, 1: Cruiser, 2: Off Road)? ",
                type.ordinal(), 0, 2);
        type = MotorcycleType.valueOf(mt);
    }

    /**
     * Create a clone from the current Motorcycle.
     *
     * @return Motorcycle
     */
    @Override
    public Motorcycle clone() {
        return new Motorcycle(numWheels, type, make, model, year);
    }

    /**
     * Provides object streaming of a Motorcycle object.
     *
     * @param ois ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        super.readObject(ois);
        numWheels = ois.readInt();
        type = MotorcycleType.valueOf(ois.readInt());
    }

    /**
     * Provides object streaming of Motorcycle object.
     *
     * @param oos ObjectOutputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        super.writeObject(oos);
        oos.writeInt(numWheels);
        oos.writeInt(type.ordinal());
    }
}
