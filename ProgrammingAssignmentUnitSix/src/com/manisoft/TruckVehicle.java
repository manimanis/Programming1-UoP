package com.manisoft;

/**
 * Represents the properties specific to all TruckVehicles.
 *
 * @author manianis
 */
public interface TruckVehicle {

    double getCapacity();

    void setCapacity(double capacity);

    TransmissionType getTransmissionType();

    void setTransmissionType(TransmissionType transmission);
}
