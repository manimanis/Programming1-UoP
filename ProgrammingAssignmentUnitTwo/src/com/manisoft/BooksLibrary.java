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

    private ArrayList<BookCopies> books = new ArrayList<>();

    public static String toTitleCase(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || (s.charAt(i) != ' ' && s.charAt(i - 1) == ' ')) {
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public void loadBooks(String filePath) {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        BookCopies bc = null;
        books.clear();
        try {
            File fichier = new File(filePath);
            fis = new FileInputStream(fichier);
            ois = new ObjectInputStream(fis);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                Object obj = ois.readObject();
                if (obj != null) {
                    bc = (BookCopies) obj;
                    books.add(bc);
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
            oos.writeInt(books.size());
            for (BookCopies bc : books) {
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
    
    public int count() {
        return books.size();
    }
    
    public BookCopies get(int idx) {
        return books.get(idx);
    }
    
    public int findBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            BookCopies bc = books.get(i);
            if (bc.getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    public void addBook(String title, String author, int numCopies) {
        int pos = findBookByTitle(toTitleCase(title));
        if (pos == -1) {
            books.add(new BookCopies(numCopies, title, author));
        } else {
            books.get(pos).addCopies(numCopies);
        }
    }

    public boolean borrowBook(String title, int numCopies) {
        int pos = findBookByTitle(toTitleCase(title));
        BookCopies book = books.get(pos);
        if (pos == -1 || !book.canBorrow(numCopies)) {
            return false;
        }
        book.borrowCopies(numCopies);
        return true;
    }

    public boolean returnBook(String title, int numCopies) {
        int pos = findBookByTitle(toTitleCase(title));
        BookCopies book = books.get(pos);
        if (pos == -1 || !book.canReturn(numCopies)) {
            return false;
        }
        book.returnCopies(numCopies);
        return true;
    }
    
    public void displayBooks() {
        for (int i = 0; i < books.size(); i++) {
            BookCopies bk = books.get(i);
            System.out.println(bk);
        }
    }
}
