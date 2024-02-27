
package unit5;

/**
 *
 * @author Cyberbox
 */
public class GroceryItem {
    private String name;
    private double price;
    private int quantity;

    public GroceryItem() {
    }

    public String getName() {
        return name;
    }

    public GroceryItem setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public GroceryItem setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Error: price cannot be negative!");
        }
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public GroceryItem setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Error: quantity cannot be negative!");
        }
        return this;
    }
    
    public void displayTotalPrice() {
        System.out.printf("%-30s %3.3f x %d = %3.3f\n",
                name, price, quantity, price * quantity);
    }
}
