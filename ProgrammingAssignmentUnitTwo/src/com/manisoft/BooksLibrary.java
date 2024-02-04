package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyberbox
 */
public class BooksLibrary {

    private final String filePath;
    private final ArrayList<BookCopy> books = new ArrayList<>();

    public BooksLibrary(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        if (file.exists()) {
            loadBooks();
        }
    }

    /**
     * Clear all the books.
     */
    public void clear() {
        books.clear();
    }

    /**
     * Returns the book count in the library.
     * @return 
     */
    public int getBooksCount() {
        return books.size();
    }

    /**
     * Returns a BookCopy by its index.
     * @param idx index of the book.
     * @return 
     */
    public BookCopy get(int idx) {
        return books.get(idx);
    }

    /**
     * Used to iterate over books using for each loop.
     * @return 
     */
    public Iterable<BookCopy> getBooks() {
        return books;
    }

    /**
     * Used to find a book by its title.
     * @param title Title of the book.
     * @return The book index, or -1 if does not exist.
     */
    public int findBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            BookCopy bc = books.get(i);
            if (bc.getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Used to add new copies of an existant book.
     * @param idx The book index.
     * @param numCopies Number of new copies.
     */
    public void addBookCopies(int idx, int numCopies) {
        books.get(idx).addCopies(numCopies);
    }

    /**
     * Add a new book or increase existant books count.
     * 
     * @param bc BookCopy object.
     * @return The book index.
     */
    public int addBook(BookCopy bc) {
        int pos = findBookByTitle(bc.getTitle());
        if (pos == -1) {
            pos = books.size();
            books.add(bc);
        } else {
            addBookCopies(pos, bc.getBooksCount());
        }
        return pos;
    }

    /**
     * Add a new book by its title, author name and number of copies.
     * @param title Book's title.
     * @param author Book's author.
     * @param numCopies Number of copies.
     * @return The inserted books index in the library.
     */
    public int addBook(String title, String author, int numCopies) {
        return addBook(new BookCopy(title, author, numCopies));
    }

    /**
     * Borrow numCopies of the book.
     * @param book The borrowed book.
     * @param numCopies Number of borrowed copies.
     * @return true if successfully borrowed.
     */
    public boolean borrowBook(BookCopy book, int numCopies) {
        if (!book.canBorrow(numCopies)) {
            return false;
        }
        book.borrowCopies(numCopies);
        return true;
    }

    /**
     * Borrow numCopies of the book by its index.
     * @param index The borrowed book's index.
     * @param numCopies Number of borrowed copies.
     * @return true if successfully borrowed.
     */
    public boolean borrowBook(int index, int numCopies) {
        return borrowBook(books.get(index), numCopies);
    }

    /**
     * Return numCopies of the book.
     * @param book The borrowed book.
     * @param numCopies Number of returned books.
     * @return true if the book is returned.
     */
    public boolean returnBook(BookCopy book, int numCopies) {
        if (!book.canReturn(numCopies)) {
            return false;
        }
        book.returnCopies(numCopies);
        return true;
    }

    /**
     * Return numCopies of the book by its index.
     * @param index The borrowed book's index.
     * @param numCopies Number of returned books.
     * @return true if the book is returned.
     */
    public boolean returnBook(int index, int numCopies) {
        return returnBook(books.get(index), numCopies);
    }
    
    public final void loadBooks() {
        ObjectInputStream ois = null;
        BookCopy bc;
        clear();
        try {
            File fichier = new File(filePath);
            ois = new ObjectInputStream(new FileInputStream(fichier));
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                Object obj = ois.readObject();
                if (obj != null) {
                    bc = (BookCopy) obj;
                    addBook(bc);
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

    public final void saveBooks() {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeInt(getBooksCount());
            for (BookCopy bc : getBooks()) {
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
