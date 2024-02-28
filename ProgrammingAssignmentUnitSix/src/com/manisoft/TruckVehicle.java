
package com.manisoft;

/**
 *
 * @author manianis
 */
public interface TruckVehicle {
    double getCapacity();
    void setCapacity(double capacity);
    
    TransmissionType getTransmissionType();
    void setTransmissionType(TransmissionType transmission);
}
