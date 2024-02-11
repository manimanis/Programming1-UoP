
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
public class Unit03 {
    public static void main(String[] args) {
        Animal animal1 = new Dog("Bobie");
        Animal animal2 = new Cat("Minouch");
        Animal animal3 = new Turtle("Habouba");
        // animalCount static variable 
        // is shared across all instances
        System.out.println(animal1.getAnimalsCount()); // 3
        System.out.println(animal2.getAnimalsCount()); // 3
        System.out.println(animal3.getAnimalsCount()); // 3
        
        // static methods are accessible using class name
        System.out.println(Animal.getAnimalsCount()); // 3
        // static methods are accessible using object name
        System.out.println(animal1.getAnimalsCount()); // 3
        // instance methods are accessible using object name
        System.out.printf("You have a %s called %s.\n",
                animal1.getSpecie(), animal1.getName());
        // output: You created 3 instances of Animal class.
        
        
        // Static are used to define
        // Constants
        System.out.println("PI = " + Math.PI); // 3.141592653589793
        System.out.println("e = " + Math.E); // 2.718281828459045
        // Utility functions
        System.out.println("SQRT(25) = " + Math.sqrt(25)); // 5.0
        System.out.println("sin(PI/6) = " + Math.sin(Math.PI/6)); // 0.5
        // Class level object
        System.out.println(Animal.class.getCanonicalName());
        // output: discussionassignmentexamples.Animal
        
        // Create a square at position (20, 30)
        Rectangle rect = new Rectangle(20, 30, 50, 50);
        // --- Manipulate object state ---
        // Change position
        rect.setPosition(120, 0);
        // Change dimensions
        rect.setDimensions(300, 300);
        // Change angle
        rect.setAngle(45);
        // Display state
        rect.state();
        // output: x: 120, y: 0, width: 300, height: 300, angle: 45
        
        // --- Manipulate object behavior ---
        // Move the rectangle to position (120, 40) in 3 seconds
        rect.moveTo(120, 40, 3);
        // Rotate the object by 180° in 2 seconds
        rect.rotate(180, 2);
        // Display state
        rect.state();
        // output: x: 120, y: 40, width: 300, height: 300, angle: -135
    }
}
