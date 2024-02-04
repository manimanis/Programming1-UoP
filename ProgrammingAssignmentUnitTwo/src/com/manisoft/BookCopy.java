package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Cyberbox
 */
public class BookCopy implements Comparable<BookCopy>, Serializable {

    /**
     * Book title.
     */
    protected String title;

    /**
     * Book author.
     */
    protected String author;

    /**
     * Book copies owned by the library.
     */
    protected int booksCount;

    /**
     * Book copies available in the library.
     */
    protected int booksAvailable;

    public BookCopy(String title, String author, int booksCount) {
        this.title = title;
        this.author = author;
        this.booksCount = booksCount;
        this.booksAvailable = booksCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getBooksAvailable() {
        return booksAvailable;
    }
    
    /**
     * Number of borrowed books.
     * 
     * @return number of borrowed books.
     */
    public int getBorrowedCopies() {
        return booksCount - booksAvailable;
    }

    /**
     * Add new copies.
     *
     * @param booksCount Number of new copies.
     */
    public void addCopies(int booksCount) {
        if (booksCount <= 0) {
            throw new IllegalArgumentException(
                    "Books count should be positive.");
        }
        this.booksCount += booksCount;
        this.booksAvailable += booksCount;
    }

    /**
     * Indicate if the user can borrow this quantity of books.
     * 
     * @param numCopies Number of books.
     * 
     * @return true if the user can borrow this number, false otherwise.
     */
    public boolean canBorrow(int numCopies) {
        return booksAvailable >= numCopies;
    }

    /**
     * Update the available books after the numCopies of book are borrowed.
     * 
     * @param numCopies Number of books.
     */
    public void borrowCopies(int numCopies) {
        if (!canBorrow(numCopies)) {
            throw new IllegalArgumentException(
                    String.format("Cannot borrow more than %d available books.", 
                            booksCount));
        }
        booksAvailable -= numCopies;
    }

    /**
     * Indicate if the user can return this quantity of books.
     * 
     * @param numCopies Number of books.
     * 
     * @return true if the user can return this number, false otherwise.
     */
    public boolean canReturn(int numCopies) {
        return booksAvailable + numCopies <= booksCount;
    }

    /**
     * Update the available books after the numCopies of book are returned.
     * 
     * @param numCopies Number of books.
     */
    public void returnCopies(int numCopies) {
        if (!canReturn(numCopies)) {
            throw new IllegalArgumentException(
                    String.format("Cannot return more than %d missing books.", 
                            booksCount - booksAvailable));
        }
        booksAvailable += numCopies;
    }

    /**
     * Format book details in tabular format.
     * 
     * @return books details.
     */
    @Override
    public String toString() {
        return String.format("%-40s %-20s %d/%d",
                UtilityFunc.formatStr(title, 40),
                UtilityFunc.formatStr(author, 20),
                booksAvailable, booksCount);
    }

    /**
     * Used to compare two books.
     * @param o A book to search.
     * @return 
     */
    @Override
    public int compareTo(BookCopy o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return this.title.compareToIgnoreCase(o.title);
    }

    /**
     * Reads a book from the input stream.
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.title = ois.readUTF();
        this.author = ois.readUTF();
        this.booksCount = ois.readInt();
        this.booksAvailable = ois.readInt();
    }

    /**
     * Write a book to the output stream.
     * @param oos
     * @throws IOException 
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeUTF(title);
        oos.writeUTF(author);
        oos.writeInt(booksCount);
        oos.writeInt(booksAvailable);
    }
}
