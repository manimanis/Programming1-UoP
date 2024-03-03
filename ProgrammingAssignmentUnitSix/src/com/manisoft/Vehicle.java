package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents the common properties and methods to all Vehicles.
 *
 * @author manianis
 */
public interface Vehicle extends Serializable {

    // Common Properties
    VehicleType getType();

    String getMake();

    void setMake(String make);

    String getModel();

    void setModel(String model);

    int getYearManufacture();

    void setYearManufacture(int year);

    // Common Operations
    void display();

    void input();

    // Provides Vehicle Streaming
    void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException;

    void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException;

    // Permit the creation of Vehicle's clones.
    Vehicle clone();
}
