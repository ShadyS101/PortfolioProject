package components.booktracker;

import components.map.Map;
import components.map.Map1L;

/**
 * Kernel Implementation of {@code BookTracker} Component.
 *
 * @convention
 *
 *             <pre>
 *  Every book would have exactly one genre, one author, and one
 *  read/unread status. No book entry can be missing from any of the
 *  three maps. Each title appears at most once in each map, and no
 *  key/value is null.
 *             </pre>
 *
 * @correspondence
 *
 *                 <pre>
 * title → (genre, author, read?)
 * For each title t in books:
 *     book(t).genre = books.value(t)
 *     book(t).author = authors.value(t)
 *     book(t).read? = status.value(t)
 *                 </pre>
 *
 * @author Shaili Sinha
 */
public class BookTracker1L extends BookTrackerSecondary {

    /**
     * Tracks all the books.
     */
    private Map<String, String> books;

    /**
     * Tracks the status of books.
     */
    private Map<String, Boolean> status;

    /**
     * Tracks the books' authors.
     */
    private Map<String, String> authors;

    /**
     * Creator of initial rep.
     */
    private void createNewRep() {
        this.books = new Map1L<>();
        this.status = new Map1L<>();
        this.authors = new Map1L<>();
    }

    /**
     * Constructs an empty {@code BookTracker}.
     */
    public BookTracker1L() {
        this.createNewRep();
    }

    //Standard Methods

    @Override
    public final BookTracker newInstance() {
        return new BookTracker1L();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(BookTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof BookTracker1L : ""
                + "Violation of: dynamic type of source is BookTracker1L";

        BookTracker1L localSource = (BookTracker1L) source;

        this.books = localSource.books;
        this.status = localSource.status;
        this.authors = localSource.authors;

        localSource.createNewRep();
    }

    //Kernel Methods

    /**
     * Adds a book to the tracker. But, at first, the book is marked as unread.
     *
     * @param title
     *            the title of the book
     * @param genre
     *            the genre of the book
     * @param author
     *            the author of the book
     */
    @Override
    public void addBook(String title, String genre, String author) {
        books.add(title, genre);
        authors.add(title, author);
        status.add(title, false);
    }

    /**
     * Removes a book from the tracker.
     *
     * @param title
     *            the title of the book to remove
     * @return what book was removed
     */
    @Override
    public String removeBook(String title) {
        String result = "";
        if (hasBook(title)) {
            books.remove(title);
            status.remove(title);
            authors.remove(title);
            result = "Removed: " + title;
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
    @Override
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
    @Override
    public boolean hasGenre(String genre) {
        boolean check = false;
        for (String title : books.keys()) {
            if (books.value(title).equals(genre)) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks if there is any book with the specified author.
     *
     * @param author
     *            the author to check
     * @return true if at least one book matches the author, false otherwise
     */
    @Override
    public boolean hasAuthor(String author) {
        boolean check = false;
        for (String title : authors.keys()) {
            if (authors.value(title).equals(author)) {
                check = true;
            }
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
    @Override
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
    @Override
    public int size() {
        return books.size();
    }

}
