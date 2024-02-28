
package com.manisoft;

/**
 *
 * @author manianis
 */
public class Motorcycle extends AbstractVehicle implements MotorVehicle {
    
    private int numWeels;
    private MotorcycleType type;

    public Motorcycle(int numWeels, MotorcycleType type, String make, String model, int year) {
        super("Motorcycle", make, model, year);
        this.numWeels = numWeels;
        this.type = type;
    }

    @Override
    public int getNumberOfWheels() {
        return numWeels;
    }

    @Override
    public void setNumberOfWheels(int numWheels) {
        this.numWeels = numWheels;
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
        System.out.println("Number of wheels: " + numWeels);
        System.out.println("Motocycle type: " + type);
    }
}
