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
    
    public static String removeSpaces(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            if (car != ' ' || (i > 0 && sb.charAt(sb.length() - 1) != ' ')) {
                sb.append(car);
            }
        }
        return sb.toString();
    }
    
    public static boolean isAlphanumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            boolean valid = (Character.isAlphabetic(car) 
                    || Character.isDigit(car) 
                    || Character.isSpaceChar(car));
            if (!valid) {
                return false;
            }
        }
        return true;
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
    
    public int addBook(BookCopies bc) {
        return addBook(bc.getTitle(), bc.getAuthor(), bc.getBooksCount());
    }

    public int addBook(String title, String author, int numCopies) {
        int pos = findBookByTitle(toTitleCase(title));
        if (pos == -1) {
            pos = books.size();
            books.add(new BookCopies(numCopies, title, author));
        } else {
            books.get(pos).addCopies(numCopies);
        }
        return pos;
    }

    public boolean borrowBook(BookCopies book, int numCopies) {
        if (!book.canBorrow(numCopies)) {
            return false;
        }
        book.borrowCopies(numCopies);
        return true;
    }
    
    public boolean borrowBook(int index, int numCopies) {
        return borrowBook(books.get(index), numCopies);
    }

    public boolean returnBook(BookCopies book, int numCopies) {
        if (!book.canReturn(numCopies)) {
            return false;
        }
        book.returnCopies(numCopies);
        return true;
    }
    
    public boolean returnBook(int index, int numCopies) {
        return returnBook(books.get(index), numCopies);
    }
    
}
