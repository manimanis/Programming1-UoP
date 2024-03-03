package com.manisoft;

/**
 * Represents the properties specific to all MotorVehicles.
 *
 * @author manianis
 */
public interface MotorVehicle {

    int getNumberOfWheels();

    void setNumberOfWheels(int numWheels);

    MotorcycleType getMotorcycleType();

    void setMotorcycleType(MotorcycleType type);
}
