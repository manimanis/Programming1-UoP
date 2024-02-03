
package com.manisoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyberbox
 */
public class LibraryInterface {
    BooksLibrary library = new BooksLibrary();
    
    public void displayBooks() {
        displayBooks(0, library.bookCount());
    }
    
    public void displayBooks(int start, int end) {
        for (int i = start; i < end; i++) {
            BookCopies bk = library.get(i);
            System.out.println(bk);
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
