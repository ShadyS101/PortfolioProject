package components.booktracker;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods for {@code BookTracker}.
 *
 * @author Shaili Sinha
 */
public abstract class BookTrackerSecondary implements BookTracker {

    /**
     * Returns the internal map of books (title, genre).
     *
     * @return stores the names of the books in a map.
     */
    protected abstract Map<String, String> books();

    /**
     * Returns the internal map of status (title, read/unread).
     *
     * @return stores the status of whether something has been read or not.
     */
    protected abstract Map<String, Boolean> status();

    /**
     * Returns the internal map of status (title, author).
     *
     * @return stores the books' authors in a map.
     */
    protected abstract Map<String, Boolean> authors();

    /*
     * Secondary Methods
     */

    /**
     * Returns a set of all books belonging to a specific genre.
     *
     * @param genre
     *            the genre to filter by
     * @return set of book titles in the specified genre
     */
    @Override
    public Set<String> getBooksInGenre(String genre) {
        Set<String> bookGenres = new Set1L<>();

        if (hasGenre(genre)) {
            for (String title : books().keys()) {
                if (books().value(title).equals(genre)) {
                    bookGenres.add(title);
                }
            }
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
        if (hasBook(title)) {
            status().replaceValue(title, true);
        }
    }

    /**
     * Counts how many books are associated with each author.
     *
     * @return a map from an author's name to the number of books by them
     */
    @Override
    public Map<String, Integer> countBooksByAuthor() {
        //Added hasAuthor which does the same thing as hasGenre except for authors
        Map<String, Integer> authorCount = new Map1L<>();

        for (String title : booksAuthors().keys()) {
            String author = booksAuthors().value(title);
            if (hasAuthor(author)) {
                if (authorCount.hasKey(author)) {
                    authorCount.replaceValue(author,
                            authorCounts.value(author) + 1);
                } else {
                    authorCount.add(author, 1);
                }
            }
        }

        return authorCount;
    }

    /**
     * Removes all the books from the tracker.
     */
    @Override
    public void clear() {
        books().clear();
        status().clear();
        booksAuthors().clear();
    }

    /*
     * Object Methods
     */

    /**
     * Returns a string representation of the current state of the BookTracker
     * with the title, genre, and author for each book.
     *
     * @return string representation of the BookTracker
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookTracker with the following books:\n");

        for (String title : books().keys()) {
            String genre = books().value(title);
            String author = booksAuthors().value(title);

            sb.append("Title: ").append(title).append(", Genre: ").append(genre)
                    .append(", Author: ").append(author).append("\n");
        }

        return sb.toString();
    }

    /**
     * Checks if two BookTrackerSecondary objects are equal and are considered
     * equal if they have the same books, statuses, and authors.
     *
     * @param obj
     *            object to compare this instance with
     * @return true if the two objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        boolean check = false;
        if (obj instanceof BookTrackerSecondary) {
            BookTrackerSecondary other = (BookTrackerSecondary) obj;
            check = this.books().equals(other.books())
                    && this.status().equals(other.status())
                    && this.booksAuthors().equals(other.booksAuthors());
        }
        return check;
    }

    /**
     * Computes the hash code for the BookTrackerSecondary object based on the
     * books, statuses, and authors maps.
     *
     * @return hash code of this BookTrackerSecondary object
     */
    @Override
    public int hashCode() {
        return books().hashCode() + status().hashCode()
                + booksAuthors().hashCode();
    }

}
