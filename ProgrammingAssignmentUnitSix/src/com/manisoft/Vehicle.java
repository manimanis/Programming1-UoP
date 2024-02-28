
package com.manisoft;

/**
 *
 * @author manianis
 */
public interface Vehicle {
    String getMake();
    void setMake(String make);
    
    String getModel();
    void setModel(String model);
    
    int getYearManufacture();
    void setYearManufacture(int year);
    
    void display();
}
