
package com.manisoft;

/**
 *
 * @author manianis
 */
public interface MotorVehicle {
    int getNumberOfWheels();
    void setNumberOfWheels(int numWheels);
    
    MotorcycleType getMotorcycleType();
    void setMotorcycleType(MotorcycleType type);
}
