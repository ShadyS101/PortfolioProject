import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Tests for secondary methods and object methods of the abstract class of
 * BookTracker.
 *
 * @author Shaili Sinha
 */
public abstract class BookTrackerSecondaryTest {

    /**
     * Construtor that creates a tracker with books.
     *
     * @param books
     *            tracker for testing
     * @return tracker for books
     */
    private BookTracker build(String[][] books) {
        BookTracker bt = new BookTracker1L();
        for (String[] entry : books) {
            bt.addBook(entry[0], entry[1], entry[2]);
        }
        return bt;
    }

    // Secondary Methods

    /**
     * Test getting books from a specific genre.
     */
    @Test
    public void testGetBooksInGenre() {
        BookTracker bt = build(new String[][] { { "T1", "Fiction", "A1" },
                { "T2", "Horror", "A2" }, { "T3", "Fiction", "A3" } });

        Set<String> expected = new Set1L<>();
        expected.add("T1");
        expected.add("T3");

        assertEquals(expected, bt.getBooksInGenre("Fiction"));
    }

    /**
     * Test marking a book as read.
     */
    @Test
    public void testMarkRead() {
        BookTracker bt = build(new String[][] { { "T1", "Fiction", "A1" } });

        bt.markRead("T1");

        BookTracker expected = build(
                new String[][] { { "T1", "Fiction", "A1" } });
        expected.markRead("T1");

        assertEquals(expected, bt);
    }

    /**
     * Test counting books by a specific author.
     */
    @Test
    public void testCountBooksByAuthor() {
        BookTracker bt = build(new String[][] { { "T1", "Fiction", "A1" },
                { "T2", "Horror", "A2" }, { "T3", "SciFi", "A1" } });

        Map<String, Integer> expected = new Map1L<>();
        expected.add("A1", 2);
        expected.add("A2", 1);

        assertEquals(expected, bt.countBooksByAuthor());
    }

    // Object Methods

    /**
     * Test if two books in booktracker is true.
     */
    @Test
    public void testEqualsTrue() {
        BookTracker bt1 = build(new String[][] { { "T1", "Fiction", "A1" } });

        BookTracker bt2 = build(new String[][] { { "T1", "Fiction", "A1" } });

        assertTrue(bt1.equals(bt2));
    }

    /**
     * Test if two books in booktracker is false.
     */
    @Test
    public void testEqualsFalse() {
        BookTracker bt1 = build(new String[][] { { "T1", "Fiction", "A1" } });

        BookTracker bt2 = build(new String[][] { { "T2", "Fiction", "A1" } });

        assertFalse(bt1.equals(bt2));
    }

    /**
     * Test if the right hashcode is returned.
     */
    @Test
    public void testHashCode() {
        BookTracker bt1 = build(new String[][] { { "T1", "Fiction", "A1" } });

        BookTracker bt2 = build(new String[][] { { "T1", "Fiction", "A1" } });

        assertEquals(bt1.hashCode(), bt2.hashCode());
    }

    /**
     * Test if string output matches when toString is called.
     */
    @Test
    public void testToString() {
        BookTracker bt = build(new String[][] { { "T1", "Fiction", "A1" } });

        String result = bt.toString();
        String expected = "BookTracker with the following books:\n"
                + "Title: T1, Genre: Fiction, Author: A1\n";

        assertEquals(expected, result);
    }
}
