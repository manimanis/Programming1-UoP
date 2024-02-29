
package com.manisoft;

/**
 *
 * @author manianis
 */
public class Motorcycle extends AbstractVehicle implements MotorVehicle {
    
    private int numWheels;
    private MotorcycleType type;

    public Motorcycle(int numWeels, MotorcycleType type, String make, String model, int year) {
        super("Motorcycle", make, model, year);
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
    
    
}
