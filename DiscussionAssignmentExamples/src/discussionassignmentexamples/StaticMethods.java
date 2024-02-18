
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
public class StaticMethods {
    public static void main(String[] args) {
        BaseClass cls = new ChildClass();
        cls.sayHello("Ahmed");
    }
}

class BaseClass {
    public static void sayHello(String name) {
        System.out.printf("Hello %s\n", name);
    }
}

class ChildClass extends BaseClass {
    public static void sayHello(String name) {
        System.out.printf("Greetings %s\n", name);
    }
}
