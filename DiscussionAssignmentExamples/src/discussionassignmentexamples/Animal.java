/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
public class Animal {

    // Static variables members
    protected static int animalsCount;

    // Instance variables
    protected int ref;
    protected String name;
    protected String specie;

    public Animal(String name, String specie) {
        animalsCount++;
        this.ref = animalsCount;
        this.name = name;
        this.specie = specie;
    }

    public int getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }
    
    public void makeSound() {
        System.out.println("No sound!");
    }

    public static int getAnimalsCount() {
        return animalsCount;
    }
}
