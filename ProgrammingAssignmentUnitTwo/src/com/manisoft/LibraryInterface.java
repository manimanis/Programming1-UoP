package com.manisoft;

/**
 *
 * @author Cyberbox
 */
public class LibraryInterface {

    private final BooksLibrary library;

    public LibraryInterface(String filePath) {
        library = new BooksLibrary(filePath);
    }

    /**
     * Returns BooksLibrary object.
     *
     * @return BooksLibrary object.
     */
    public BooksLibrary getLibrary() {
        return library;
    }

    /**
     * Display all books in the library.
     */
    public void displayBooks() {
        displayBooks(0, library.getBooksCount());
    }

    /**
     * Display the books ranging between start index and end index exclusive.
     *
     * @param start Start index inclusive.
     * @param end End index exclusive.
     */
    public void displayBooks(int start, int end) {
        System.out.println(String.format("%-4s %-60s %-20s %s",
                "Ref", "Book Title", "Author", "Avail/Count"));
        for (int i = start; i < end; i++) {
            BookCopy bk = library.get(i);
            System.out.println(
                    String.format("%-4d ", i) + bk);
        }
        System.out.println();
    }

    /**
     * Displays application main menu.
     */
    public void displayMenu() {
        int choice;
        do {
            System.out.println(" -- Welcome to the virtual Library -- \n");
            System.out.println("1. Add new book");
            System.out.println("2. Borrow books");
            System.out.println("3. Return books");
            System.out.println("------------------");
            System.out.println("4. List of books");
            System.out.println("------------------");
            System.out.println("9. Exit");

            choice = UtilityFunc.getInteger("Your choice, please? ", 1, 9);
            System.out.println();

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

    /**
     * Prompts the user for information about a new book.
     */
    public void addNewBook() {
        String title;
        String author = "";
        int numCopies;
        BookCopy book;

        System.out.println(" -- Add new book -- \n");

        title = UtilityFunc.toTitleCase(
                UtilityFunc.getString("Book title? ", 10, 60));
        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            author = UtilityFunc.toTitleCase(
                    UtilityFunc.getString("Book author? ", 3, 40));
        } else {
            book = library.get(pos);
            System.out.println("We already have this title!");
            System.out.println("It is authored by: " + book.getAuthor());
            System.out.println("We own " + book.getBooksCount() + " copies.");
        }
        numCopies = UtilityFunc.getInteger("Book copies? ", 1, 100);

        if (pos == -1) {
            pos = library.addBook(title, author, numCopies);
            System.out.println("Book Successfully Added!\n");
        } else {
            library.addBookCopies(pos, numCopies);
            System.out.println("Books count Successfully Updated!\n");
        }
        library.saveBooks();
        displayBooks(pos, pos + 1);
    }

    /**
     * Prompts the user for book they wish to borrow.
     */
    public void borrowBooks() {
        System.out.println("\n -- Borrow books -- \n");

        String title = UtilityFunc.toTitleCase(
                UtilityFunc.getString("Book title? ", 10, 60));

        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            System.out.println("This book is not found!");
            return;
        }

        BookCopy bc = library.get(pos);
        if (bc.getBooksAvailable() == 0) {
            System.out.println("No copies of the book are available!");
            return;
        }

        System.out.println(String.format(
                "There are %d copies available of this book.",
                bc.getBooksAvailable()));
        int numCopies = UtilityFunc.getInteger("How many copies, 0 to cancel? ",
                0, bc.getBooksAvailable());
        if (numCopies != 0) {
            library.borrowBook(bc, numCopies);
            System.out.println(
                    String.format("You just borrowed %d copies of '%s' book.\n",
                            numCopies, title));
            library.saveBooks();
            displayBooks(pos, pos + 1);
        }
    }

    /**
     * Prompts the user for book they want to return.
     */
    public void returnBooks() {
        System.out.println("\n -- Return books -- \n");
        String title = UtilityFunc.toTitleCase(
                UtilityFunc.getString("Book title? ", 10, 60));

        int pos = library.findBookByTitle(title);
        if (pos == -1) {
            System.out.println("This book is not ours!");
            return;
        }

        BookCopy bc = library.get(pos);
        if (bc.getBorrowedCopies() == 0) {
            System.out.println("No copies of this book were borrowed!");
            return;
        }

        System.out.println(bc.getBorrowedCopies() + " copies were borrowed!");
        int numCopies = UtilityFunc.getInteger(
                "How many copies to return, 0 to cancel? ",
                0, bc.getBorrowedCopies());
        if (numCopies != 0) {
            library.returnBook(bc, numCopies);
            System.out.println(
                    String.format("You just returned %d copies of '%s' book.\n"
                            + "Now, %d books are available.",
                            numCopies, title, bc.getBooksAvailable()));
            library.saveBooks();
            displayBooks(pos, pos + 1);
        }
    }
}
