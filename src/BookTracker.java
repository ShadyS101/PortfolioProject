import java.util.Map;

import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * A program that serves as a book tracker, keeping track of all the books the
 * user is reading and specific data points about them.
 */
public class BookTracker {

    /**
     * Tracks all the books.
     */
    private Map<String, String> books;
    /**
     * Tracks the status of books.
     */
    private Map<String, Boolean> status;

    /**
     * Constructs an empty BookTracker.
     */
    public BookTracker() {
        books = new Map1L<>();
        status = new Map1L<>();
    }

    //Kernel Methods

    /**
     * Adds a book to the tracker. But, at first, the book is marked as unread.
     *
     * @param title
     *            the title of the book
     * @param genre
     *            the genre of the book
     */
    public void addBook(String title, String genre) {
        books.add(title, genre);
        status.add(title, false);
    }

    /**
     * Removes a book from the tracker.
     *
     * @param title
     *            the title of the book to remove
     * @return what book was removed
     */
    public String removeBook(String title) {
        String result = "Removed: ";
        if (hasBook(title)) {
            books.remove(title);
            status.remove(title);
            result = title;
        }

        return result;
    }

    /**
     * Returns the status of the book.
     *
     * @param title
     *            the title of the book
     * @return "Read", "Unread", or "Book not found."
     */
    public String bookStatus(String title) {
        String result = "";
        if (hasBook(title)) {
            if (status.value(title)) {
                result = "Read";
            } else {
                result = "Unread";
            }
        } else {
            result = "Book not found.";
        }
        return result;
    }

    /**
     * Checks if there is any book with the specified genre.
     *
     * @param genre
     *            the genre to check
     * @return true if at least one book matches the genre, false otherwise
     */
    public boolean hasGenre(String genre) {
        boolean check = false;
        Map<String, String> temp = books.newInstance();
        temp.transferFrom(books);
        while (temp.size() > 0) {
            Map.Pair<String, String> pair = temp.removeAny();
            if (pair.value().equals(genre)) {
                check = true;
            }
            books.add(pair.key(), pair.value());
        }
        return check;
    }

    /**
     * Checks if the tracker contains a book with the given title.
     *
     * @param title
     *            the book title to check
     * @return true if the book exists, false otherwise
     */
    public boolean hasBook(String title) {
        boolean check = false;
        if (books.hasKey(title)) {
            check = true;
        }
        return check;
    }

    /**
     * Returns the number of books tracked.
     *
     * @return total number of books
     */
    public int size() {
        return books.size();
    }

    //Secondary Methods

    /**
     * Returns a set of all books belonging to a specific genre.
     *
     * @param genre
     *            the genre to filter by
     * @return set of book titles in the specified genre
     * @restores books
     */
    public Set<String> getBooksInGenre(String genre) {
        Set<String> bookGenres = new Set1L<>();
        Map<String, String> temp = books.newInstance();
        temp.transferFrom(books);
        while (temp.size() > 0) {
            Map.Pair<String, String> pair = temp.removeAny();
            if (pair.value().equals(genre)) {
                bookGenres.add(pair.key());
            }
            books.add(pair.key(), pair.value());
        }
        return bookGenres;
    }

    /**
     * Marks a book as read.
     *
     * @param title
     *            the title of the book to mark as read
     */
    public void markRead(String title) {
        if (status.hasKey(title)) {
            status.replaceValue(title, true);
        }
    }

    //Add the method countBooksByAuthors later

    /**
     * Main method to showcase how BookTracker works.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {
        BookTracker bTrack = new BookTracker();

        //Example field to showcase component, comments will give expected results

        //Adds the books to the map
        bTrack.addBook("The Secret History", "Fiction");
        bTrack.addBook("Six of Crows", "Fantasy");
        bTrack.addBook("1984", "Dystopian");

        //This will take out 1984
        bTrack.removeBook("1984");

        //Status would be unread
        System.out.println(
                "Status of Six of Crows: " + bTrack.bookStatus("Six of Crows"));
        bTrack.markRead("Six of Crows");
        //Status would now be read
        System.out.println(
                "Status of Six of Crows: " + bTrack.bookStatus("Six of Crows"));

        //These both should be true
        bTrack.hasBook("The Secret History");
        bTrack.hasGenre("Fiction");

        //This should be 2
        bTrack.size();

        //This should give you The Secret History
        System.out.println(
                "Books in Fiction: " + bTrack.getBooksInGenre("Fiction"));
    }
}
