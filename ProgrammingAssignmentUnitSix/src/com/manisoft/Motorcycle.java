
package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author manianis
 */
public class Motorcycle extends VehicleBase implements MotorVehicle {
    
    private int numWheels;
    private MotorcycleType type;

    public Motorcycle() {
        this(2, MotorcycleType.SPORT, "", "", 2024);
    }  

    public Motorcycle(int numWeels, MotorcycleType type, String make, String model, int year) {
        super(VehicleType.MOTORCYCLE, make, model, year);
        this.numWheels = numWeels;
        this.type = type;
    }

    @Override
    public int getNumberOfWheels() {
        return numWheels;
    }

    @Override
    public void setNumberOfWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    @Override
    public MotorcycleType getMotorcycleType() {
        return type;
    }

    @Override
    public void setMotorcycleType(MotorcycleType type) {
        this.type = type;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Number of wheels: " + numWheels);
        System.out.println("Motocycle type: " + type);
    }

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

    @Override
    public void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException {
        super.readObject(ois);
        numWheels = ois.readInt();
        type = MotorcycleType.valueOf(ois.readInt());
    }

    @Override
    public void writeObject(ObjectOutputStream oos) 
            throws IOException, ClassNotFoundException {
        super.writeObject(oos);
        oos.writeInt(numWheels);
        oos.writeInt(type.ordinal());
    }
    
}
