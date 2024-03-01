package com.manisoft;

/**
 *
 * @author manianis
 */
public class Truck extends AbstractVehicle implements TruckVehicle {

    private double capacity;
    private TransmissionType transmission;

    public Truck() {
        this(5.5, TransmissionType.MANUAL, "", "", 2024);
    }

    public Truck(double capacity, TransmissionType transmission, String make, String model, int year) {
        super(VehicleType.TRUCK, make, model, year);
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
}
