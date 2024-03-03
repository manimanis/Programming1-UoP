package com.manisoft;

/**
 * Represents the properties specific to all CarVehicles.
 *
 * @author manianis
 */
public interface CarVehicle {

    int getNumberOfDoors();

    void setNumberOfDoors(int numDoors);

    FuelType getFuelType();

    void setFuelType(FuelType fuel);
}
