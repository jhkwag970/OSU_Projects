import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class HW7_TestCases {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddEmpty() {

        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("aa");

        s.add(0, "aa");

        assertEquals(sExpected, s);

    }

    @Test
    public final void testNonEmptyAdd() {

        Sequence<String> s = this.createFromArgsTest("aa");
        Sequence<String> sExpected = this.createFromArgsRef("aa", "bb", "cc");

        s.add(1, "bb");
        s.add(2, "cc");

        assertEquals(s, sExpected);

    }

    @Test
    public final void testreplaceAdd() {

        Sequence<String> s = this.createFromArgsTest("aa", "bb", "cc");
        Sequence<String> sExpected = this.createFromArgsRef("aa", "dd", "cc");

        s.add(1, "dd");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveEmpty() {

        Sequence<String> s = this.createFromArgsTest("aa");
        Sequence<String> sExpected = this.createFromArgsRef();

        String str = s.remove(0);

        assertEquals(str, "aa");
        assertEquals(s, sExpected);

    }

    @Test
    public final void testRemoveNonEmpty() {

        Sequence<String> s = this.createFromArgsTest("aa", "bb", "cc");
        Sequence<String> sExpected = this.createFromArgsRef("aa");

        s.remove(1);
        s.remove(2);

        assertEquals(s, sExpected);
    }

    @Test
    public final void testLengthEmpty() {

        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef();

        int sLength = s.length();
        int sExLength = sExpected.length();

        assertEquals(sLength, sExLength);

    }

    @Test
    public final void testLengthNonEmpty() {

        Sequence<String> s = this.createFromArgsRef("aa", "bb", "cc", "dd");
        Sequence<String> sExpected = this.createFromArgsRef("aa", "bb", "cc",
                "dd");

        assertEquals(s.length(), sExpected.length());
    }

    @Test
    public final void testLengthAfterAdd() {

        Sequence<String> s = this.createFromArgsTest("aa");
        Sequence<String> sExpected = this.createFromArgsRef("aa", "bb");

        s.add(1, "bb");

        assertEquals(s.length(), sExpected.length());
    }

    @Test
    public final void testLengthAfterRemove() {

        Sequence<String> s = this.createFromArgsTest("aa", "bb");
        Sequence<String> sExpected = this.createFromArgsRef("aa");

        s.remove(1);

        assertEquals(s.length(), sExpected.length());
    }

}