package com.manisoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Cyberbox
 */
public class Book implements Comparable<Book>, Serializable {

    protected String title;
    protected String author;

    public Book(String title, String author) {
        this.title = BooksLibrary.toTitleCase(title);
        this.author = BooksLibrary.toTitleCase(author);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return this.title.equalsIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return String.format("%-60s %-20s", 
                title.length() > 60 ? (title.substring(0, 57) + "...") : title, 
                author.length() > 20 ? (author.substring(0, 17) + "..."): author);
    }

    @Override
    public int compareTo(Book o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return this.title.compareToIgnoreCase(o.title);
    }

    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.title = ois.readUTF();
        this.author = ois.readUTF();
    }

    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeUTF(title);
        oos.writeUTF(author);
    }
}
