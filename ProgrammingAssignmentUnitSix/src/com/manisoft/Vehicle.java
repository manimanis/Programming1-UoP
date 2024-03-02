
package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author manianis
 */
public interface Vehicle extends Serializable {
    VehicleType getType();
    
    String getMake();
    void setMake(String make);
    
    String getModel();
    void setModel(String model);
    
    int getYearManufacture();
    void setYearManufacture(int year);
    
    void display();
    void input();
    
    void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException;
    void writeObject(ObjectOutputStream oos)
            throws IOException, ClassNotFoundException;
}
