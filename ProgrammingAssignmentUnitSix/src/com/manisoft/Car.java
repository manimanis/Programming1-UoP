
package com.manisoft;

/**
 *
 * @author manianis
 */
public class Car extends AbstractVehicle implements CarVehicle {
    
    private int numDoors;
    private FuelType fuelType;

    public Car(int numDoors, FuelType fuelType, String make, String model, int year) {
        super("Car", make, model, year);
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
    
}
