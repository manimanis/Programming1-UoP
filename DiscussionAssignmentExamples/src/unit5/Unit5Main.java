
package unit5;

/**
 *
 * @author Cyberbox
 */
public class Unit5Main {
    public static void main(String[] args) {
        // Storing invalid values
        Item item1 = new Item();
        item1.name = "Pen";
        item1.price = -5.2;
        item1.quantity = -3;
        item1.displayTotalPrice();
        
        GroceryItem item2 = new GroceryItem();
        item2.setName("Pen")
                .setPrice(-5.2)
                .setQuantity(-3);
        item2.displayTotalPrice();
    }
}
