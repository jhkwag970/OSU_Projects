import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "yellow");
        m.add("yellow");

        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "yellow", "blue", "white");
        m.add("yellow");
        m.add("blue");
        m.add("white");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "7",
                "7", "7", "1", "4", "2", "0", "3", "6");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "7", "7", "7", "1", "4", "2", "0", "3", "6");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "21",
                "18", "22", "3", "32", "11", "11", "19", "52", "14");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "21", "18", "22", "3", "32", "11", "11", "19", "52", "14");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "0",
                "2", "1", "2", "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "0", "2", "1", "2", "1");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        m.removeFirst();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "6", "4", "7", "9", "11");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "6", "4", "7", "9", "11");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(m.removeFirst(), mExpected.removeFirst());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(m.removeFirst(), mExpected.removeFirst());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(m.removeFirst(), mExpected.removeFirst());
        assertEquals(m.removeFirst(), mExpected.removeFirst());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInsertionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");

        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSize() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");

        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSize3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(m.order(), mExpected.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrder2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");

        assertEquals(m.order(), mExpected.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrder3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "19",
                "8", "17", "5", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "19", "8", "17", "5", "4");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(m.order(), mExpected.order());
        assertEquals(mExpected, m);
    }

}
