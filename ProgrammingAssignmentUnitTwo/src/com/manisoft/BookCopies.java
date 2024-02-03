package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Cyberbox
 */
public class BookCopies extends Book {

    protected int booksCount;
    protected int booksAvailable;

    public BookCopies(int booksCount, String title, String author) {
        super(title, author);
        this.booksCount = booksCount;
        this.booksAvailable = booksCount;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getBooksAvailable() {
        return booksAvailable;
    }

    public void addCopies(int booksCount) {
        if (booksCount <= 0) {
            throw new IllegalArgumentException("Books count should be positive.");
        }
        this.booksCount += booksCount;
        this.booksAvailable += booksCount;
    }

    public boolean canBorrow(int numCopies) {
        return booksAvailable >= numCopies;
    }

    public void borrowCopies(int numCopies) {
        if (!canBorrow(numCopies)) {
            throw new IllegalArgumentException(String.format("Cannot borrow more than %d available books.", booksCount));
        }
        booksAvailable -= numCopies;
    }
    
    public int getBorrowedCopies() {
        return booksCount - booksAvailable;
    }

    public boolean canReturn(int numCopies) {
        return booksAvailable + numCopies <= booksCount;
    }

    public void returnCopies(int numCopies) {
        if (!canReturn(numCopies)) {
            throw new IllegalArgumentException(String.format("Cannot return more than %d missing books.", booksCount - booksAvailable));
        }
        booksAvailable += numCopies;
    }

    @Override
    public String toString() {
        return super.toString() + " " + booksAvailable + "/" + booksCount;
    }  

    @Override
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.booksCount = ois.readInt();
        this.booksAvailable = ois.readInt();
    }

    @Override
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeInt(booksCount);
        oos.writeInt(booksAvailable);
    }
}
