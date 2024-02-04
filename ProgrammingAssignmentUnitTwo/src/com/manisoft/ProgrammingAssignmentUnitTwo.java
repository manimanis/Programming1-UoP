
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
        // Create a new library interface object to interact with user.
        LibraryInterface libInterface = new LibraryInterface("books.bk");
        // Extract the books library object to add new books.
        BooksLibrary bookLib = libInterface.getLibrary();
        // If the library is initially empty fill it with mokup books.
        if (bookLib.getBooksCount() == 0) {
            bookLib.addBook("Head First Java", "Kathy Sierra & Bert Bates", 5);
            bookLib.addBook("Java: A Beginner's Guide", "Herbert Schildt", 3);
            bookLib.addBook("Java for Dummies", "Barry A. Burd", 4);
            bookLib.addBook("Effective Java", "Joshua Bloch", 2);
            bookLib.addBook("Java: A Beginner's Guide", "Herbert Schildt", 2);
            bookLib.saveBooks();
        }
        // Start application main menu.
        libInterface.displayMenu();
    }
    
}
