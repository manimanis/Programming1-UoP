/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
class Parent {

    static void print() {

        System.out.println("Parent");

    }

}

class Child extends Parent {

    static void print() {

        System.out.println("Child");

    }

}

public class Main {

    static int x = 5;

    void myvoid() {

        System.out.println(x);
    }

    public static void main(String[] args) {

        Parent obj = new Child();

        obj.print();

        int num = 7;

        switch (num) {

            case 1:

                System.out.println("One");

                break;

            case 2:

                System.out.println("Two");

                break;

            default:

                System.out.println("Other");

        }
        int x = 10;

        x *= 2 + 3;

        System.out.println(x);

    }

}
