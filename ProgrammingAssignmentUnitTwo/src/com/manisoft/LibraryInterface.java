package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyberbox
 */
public class LibraryInterface {

    private BooksLibrary library = new BooksLibrary();
    private final Scanner scan = new Scanner(System.in);

    public LibraryInterface() {
    }

    public BooksLibrary getLibrary() {
        return library;
    }

    public void displayBooks() {
        displayBooks(0, library.bookCount());
    }

    public void displayBooks(int start, int end) {
        System.out.println(String.format("%-60s %-20s %s", 
                "Book Title", "Author", "Avail/Count"));
        for (int i = start; i < end; i++) {
            BookCopies bk = library.get(i);
            System.out.println(bk);
        }
        System.out.println();
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println(" -- Welcome to the virtual Library -- \n");
            System.out.println("1. Add new book");
            System.out.println("2. Borrow books");
            System.out.println("3. Return books");
            System.out.println("4. List of books");
            System.out.println("9. Exit");

            System.out.print("Your choice, please? ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 ->
                    addNewBook();
                case 2 ->
                    borrowBooks();
                case 3 ->
                    returnBooks();
                case 4 ->
                    displayBooks();
                case 9 ->
                    System.out.println("Goodbye, see you soon!");
                default ->
                    System.out.println("Invalid choice!");
            }

            System.out.println();
        } while (choice != 9);
    }

    public String getString(String msg, int minLen, int maxLen) {
        String s;
        boolean valid;
        do {
            System.out.print(msg);
            s = BooksLibrary.removeSpaces(scan.nextLine().trim());
            valid = s.length() >= minLen
                    && s.length() <= maxLen;
            if (!valid) {
                System.out.println(String.format(
                        "Input should contain between %d and %d characters!",
                        minLen, maxLen));
            }
        } while (!valid);
        return s;
    }

    public int getInteger(String msg, int minVal, int maxVal) {
        int v;
        boolean valid;
        do {
            System.out.print(msg);
            v = scan.nextInt();
            valid = v >= minVal && v <= maxVal;
            if (!valid) {
                System.out.println(String.format(
                        "Input should range between %d and %d!",
                        minVal, maxVal));
            }
        } while (!valid);
        return v;
    }

    public void addNewBook() {
        System.out.println(" -- Add new book -- \n");
        String title = getString("Book title? ", 10, 60);
        String author = getString("Book author? ", 10, 60);
        int numCopies = getInteger("Book copies? ", 1, 100);

        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            System.out.println("New book!");
        } else {
            System.out.println("We already have this title!");
        }
        pos = library.addBook(title, author, numCopies);
        System.out.println("Book Successfully Added!\n");
        
        displayBooks(pos, pos+1);
    }

    public void borrowBooks() {
        System.out.println("\n -- Borrow books -- \n");
        String title = getString("Book title? ", 10, 60);

        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            System.out.println("This book is not found!");
            return;
        }
        BookCopies bc = library.get(pos);
        if (bc.getBooksAvailable() == 0) {
            System.out.println("No copies of the book are available!");
            return;
        }
        System.out.println(String.format(
                "There are %d copies available of this book.",
                bc.getBooksAvailable()));
        int numCopies = getInteger("How many copies, 0 to cancel? ",
                0, bc.getBooksAvailable());
        if (numCopies != 0) {
            library.borrowBook(bc, numCopies);
            System.out.println(
                    String.format("You just borrowed %d copies "
                            + "of '%s' book.\n",
                            numCopies, title));
            displayBooks(pos, pos+1);
        }
    }

    public void returnBooks() {
        System.out.println("\n -- Return books -- \n");
        String title = getString("Book title? ", 10, 60);

        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            System.out.println("This book is not ours!");
            return;
        }
        BookCopies bc = library.get(pos);
        int numCopies = getInteger("How many copies, 0 to cancel? ",
                0, 100);
        if (numCopies != 0) {
            if (numCopies > bc.getBorrowedCopies()) {
                System.out.println(
                        String.format("%d of these books are not ours!",
                                numCopies - bc.getBorrowedCopies()));
                return;
            }
            library.returnBook(bc, numCopies);
            System.out.println(
                    String.format("You just returned %d copies "
                            + "of '%s' book.\n"
                            + "%d books available.",
                            numCopies, title,
                            bc.getBooksAvailable()));
        }
    }

    public void loadBooks(String filePath) {
        ObjectInputStream ois = null;
        BookCopies bc;
        library.clear();
        try {
            File fichier = new File(filePath);
            ois = new ObjectInputStream(new FileInputStream(fichier));
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                Object obj = ois.readObject();
                if (obj != null) {
                    bc = (BookCopies) obj;
                    library.addBook(bc);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveBooks(String filePath) {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeInt(library.bookCount());
            for (BookCopies bc : library.getBooks()) {
                oos.writeObject(bc);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(BooksLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
