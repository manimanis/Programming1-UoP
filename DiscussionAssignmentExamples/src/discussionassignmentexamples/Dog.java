
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
public class Dog extends Animal {
    
    public int test;

    public Dog(String name) {
        super(name, "Dog");
        test = 10;
    }

    @Override
    public void makeSound() {
        System.out.println("Haw Haw");
        System.out.println(Animal.test);
        System.out.println(test);
    }
}
