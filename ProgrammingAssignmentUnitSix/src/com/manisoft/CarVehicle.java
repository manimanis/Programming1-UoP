
package com.manisoft;

/**
 *
 * @author manianis
 */
public interface CarVehicle {
    int getNumberOfDoors();
    void setNumberOfDoors(int numDoors);
    
    FuelType getFuelType();
    void setFuelType(FuelType fuel);
}
