/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit5;

/**
 *
 * @author Cyberbox
 */
public class Item {
    public String name;
    public double price;
    public int quantity;
    
    public void displayTotalPrice() {
        System.out.printf("%-30s %3.3f x %d = %3.3f\n",
                name, price, quantity, price * quantity);
    }
}
