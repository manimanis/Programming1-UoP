
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
        BooksLibrary booksLibrary = new BooksLibrary();
//        booksLibrary.addBook("Book 1", "Author 1", 5);
//        booksLibrary.addBook("Book 2", "Author 2", 10);
//        booksLibrary.addBook("Book 3", "Author 2", 10);
//        booksLibrary.addBook("Book 4", "Author 2", 13);
//        booksLibrary.addBook("Book 2", "Author 2", 10);
//        booksLibrary.saveBooks("books.bk");
        
        
        LibraryInterface library = new LibraryInterface();
        library.loadBooks("books.bk");
        library.displayBooks();
    }
    
}
