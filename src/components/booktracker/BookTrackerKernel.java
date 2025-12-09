package components.booktracker;

import components.standard.Standard;

/**
 * Kernel Interface Component for {@code BookTracker} with primary methods.
 *
 * @author Shaili Sinha
 *
 * @mathsubtypes <pre>
 * BookTrackerKernel is modeled by
 * (books: string of (title, genre, status))
 * </pre>
 * @mathmodel instance is a finite Map for tracking books
 * @initially <pre>
 * this = < >
 * </pre>
 */
public interface BookTrackerKernel extends Standard<BookTracker> {

    /**
     * Adds a book to the tracker. But, at first, the book is marked as unread.
     *
     * @param title
     *            the title of the book
     * @param genre
     *            the genre of the book
     * @updates this
     * @requires title and genre aren't empty or null
     * @ensures this = #this * <(title, genre, false)>
     */
    void addBook(String title, String genre);

    /**
     * Removes a book from the tracker.
     *
     * @param title
     *            the title of the book to remove
     * @return what book was removed
     * @updates this
     * @requires title is not empty or null
     * @ensures given book title is removed
     */
    String removeBook(String title);

    /**
     * Returns the status of the book.
     *
     * @param title
     *            the title of the book
     * @return "Read", "Unread", or "Book not found."
     * @ensures bookStatus = "Read" if status(title) = true, "Unread" if
     *          status(title) = false, "Book Not Found" otherwise
     */
    String bookStatus(String title);

    /**
     * Checks if there is any book with the specified genre.
     *
     * @param genre
     *            the genre to check
     * @return true if at least one book matches the genre, false otherwise
     * @ensures hasGenre = (genre is in this)
     */
    boolean hasGenre(String genre);

    /**
     * Checks if the tracker contains a book with the given title.
     *
     * @param title
     *            the book title to check
     * @return true if the book exists, false otherwise
     * @ensures hasBook = (title is in this)
     */
    boolean hasBook(String title);

    /**
     * Returns the number of books tracked.
     *
     * @return total number of books
     * @ensures size = |this|
     */
    int size();
}
