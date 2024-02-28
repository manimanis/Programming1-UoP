
package com.manisoft;

/**
 *
 * @author manianis
 */
public class AbstractVehicle implements Vehicle {
    protected String vehicleType;
    protected String make;
    protected String model;
    protected int year;

    public AbstractVehicle(String vehicleType, String make, String model, int year) {
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getCarType() {
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
    public void display() {
        System.out.println(vehicleType);
        System.out.println("Make: " + make);
        System.out.println("Model:" + model);
        System.out.println("Manufacture year: " + year);
    }
}
