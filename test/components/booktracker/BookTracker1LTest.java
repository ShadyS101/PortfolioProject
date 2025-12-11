import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for kernel and standard methods of BookTracker1L.
 *
 * @author Shaili Sinha
 */
public abstract class BookTracker1LTest {

    /**
     * Construtor that creates a tracker with one book.
     *
     * @param title
     *            the title of the book
     * @param genre
     *            the genre of the book
     * @param author
     *            the author of the book
     * @return tracker for one book
     */
    private BookTracker make(String title, String genre, String author) {
        BookTracker bt = new BookTracker1L();
        bt.addBook(title, genre, author);
        return bt;
    }

    // Kernel Method Tests

    /**
     * Test adding a book.
     */
    @Test
    public void testAddBookOne() {
        BookTracker bt = new BookTracker1L();
        bt.addBook("T1", "Fantasy", "A1");

        BookTracker expected = new BookTracker1L();
        expected.addBook("T1", "Fantasy", "A1");

        assertEquals(expected, bt);
    }

    /**
     * Test removing a book.
     */
    @Test
    public void testRemoveBookOne() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        bt.removeBook("T1");

        BookTracker expected = new BookTracker1L();
        assertEquals(expected, bt);
    }

    /**
     * Test marking a book as unread.
     */
    @Test
    public void testBookStatusUnread() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertEquals("Unread", bt.bookStatus("T1"));
    }

    /**
     * Test marking a book as read.
     */
    @Test
    public void testBookStatusRead() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        bt.markRead("T1");
        assertEquals("Read", bt.bookStatus("T1"));
    }

    /**
     * Test if book exists is true.
     */
    @Test
    public void testHasBookTrue() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertTrue(bt.hasBook("T1"));
    }

    /**
     * Test if book exists is false.
     */
    @Test
    public void testHasBookFalse() {
        BookTracker bt = new BookTracker1L();
        assertFalse(bt.hasBook("No"));
    }

    /**
     * Test if a genre there is true.
     */
    @Test
    public void testHasGenreTrue() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertTrue(bt.hasGenre("Fantasy"));
    }

    /**
     * Test if a genre there is false.
     */
    @Test
    public void testHasGenreFalse() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertFalse(bt.hasGenre("Sci-Fi"));
    }

    /**
     * Test if an author there is true.
     */
    @Test
    public void testHasAuthorTrue() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertTrue(bt.hasAuthor("A1"));
    }

    /**
     * Test if an author there is false.
     */
    @Test
    public void testHasAuthorFalse() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        assertFalse(bt.hasAuthor("Unknown Author"));
    }

    /**
     * Test size if it's not empty.
     */
    @Test
    public void testSizeNonzero() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        bt.addBook("T2", "Horror", "A2");
        assertEquals(2, bt.size());
    }

    /**
     * Test size if it's empty.
     */
    @Test
    public void testSizeZero() {
        BookTracker bt = new BookTracker1L();
        assertEquals(0, bt.size());
    }

    // Standard Methods

    /**
     * Test clear of booktracker.
     */
    @Test
    public void testClear() {
        BookTracker bt = make("T1", "Fantasy", "A1");
        bt.clear();

        BookTracker expected = new BookTracker1L();
        assertEquals(expected, bt);
    }

    /**
     * Test creating a new instance of booktracker.
     */
    @Test
    public void testNewInstance() {
        BookTracker bt = new BookTracker1L();
        BookTracker bt2 = bt.newInstance();

        assertEquals(new BookTracker1L(), bt2);
    }

    /**
     * Test transfering booktracker to another place.
     */
    @Test
    public void testTransferFrom() {
        BookTracker src = make("T1", "Fantasy", "A1");
        BookTracker dst = new BookTracker1L();

        dst.transferFrom(src);

        BookTracker expectedDst = make("T1", "Fantasy", "A1");
        BookTracker expectedSrc = new BookTracker1L();

        assertEquals(expectedDst, dst);
        assertEquals(expectedSrc, src);
    }
}
