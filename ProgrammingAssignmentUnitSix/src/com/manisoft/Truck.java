
package com.manisoft;

/**
 *
 * @author manianis
 */
public class Truck extends AbstractVehicle implements TruckVehicle {
    private double capacity;
    private TransmissionType transmission;

    public Truck(double capacity, TransmissionType transmission, String make, String model, int year) {
        super("Truck", make, model, year);
        this.capacity = capacity;
        this.transmission = transmission;
    }

    @Override
    public double getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public TransmissionType getTransmissionType() {
        return transmission;
    }

    @Override
    public void setTransmissionType(TransmissionType transmission) {
        this.transmission = transmission;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Capacity (tons): " + capacity);
        System.out.println("Transmission type: " + transmission);
    }
}
