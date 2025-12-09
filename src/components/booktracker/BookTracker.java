package components.booktracker;

import components.map.Map;
import components.set.Set;

/**
 * {@code BookTracker} enhanced with secondary methods.
 *
 * @author Shaili Sinha
 */
public interface BookTracker extends BookTrackerKernel {

    /**
     * Returns a set of all books belonging to a specific genre.
     *
     * @param genre
     *            the genre to filter by
     * @return set of book titles in the specified genre
     * @restores books
     * @ensures getBooksInGenre = {titles of books with genre = genre}
     */
    Set<String> getBooksInGenre(String genre);

    /**
     * Marks a book as read.
     *
     * @param title
     *            the title of the book to mark as read
     * @updates this
     * @requires this has book with the given title
     * @ensures status(title) = true
     */
    void markRead(String title);

    /**
     * Counts how many books are associated with each author.
     *
     * @return a map from an author's name to the number of books by them
     * @ensures countBooksByAuthors = map(author, number of books) paired up so
     *          number of books by author
     */
    Map<String, Integer> countBooksByAuthors();

    /**
     * Removes all the books from the tracker.
     *
     * @clears this
     * @ensures this = < >
     */
    void clear();
}
