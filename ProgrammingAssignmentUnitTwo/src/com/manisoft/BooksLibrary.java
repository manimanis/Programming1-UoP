package com.manisoft;

import java.util.ArrayList;

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
    
    public int bookCount() {
        return books.size();
    }
    
    public BookCopies get(int idx) {
        return books.get(idx);
    }
    
    public Iterable<BookCopies> getBooks() {
        return books;
    }
    
    public void clear() {
        books.clear();
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
    
    public void addBook(BookCopies bc) {
        addBook(bc.getTitle(), bc.getAuthor(), bc.getBooksCount());
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
    
    

    

    

    
}
