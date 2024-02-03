
package com.manisoft;

/**
 *
 * @author Cyberbox
 */
public class ProgrammingAssignmentUnitTwo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LibraryInterface libInterface = new LibraryInterface();
        libInterface.getLibrary().addBook("Head First Java", "Kathy Sierra & Bert Bates", 5);
        libInterface.getLibrary().addBook("Java: A Beginner's Guide", "Herbert Schildt", 3);
        libInterface.getLibrary().addBook("Java for Dummies", "Barry A. Burd", 4);
        libInterface.getLibrary().addBook("Effective Java", "Joshua Bloch", 2);
        libInterface.getLibrary().addBook("Java: A Beginner's Guide", "Herbert Schildt", 2);
        libInterface.saveBooks("books.bk");
        libInterface.loadBooks("books.bk");
        libInterface.displayMenu();
    }
    
}
