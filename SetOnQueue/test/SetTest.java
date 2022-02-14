import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyAdd() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("aa");

        s.add("aa");

        assertEquals(sExpected, s);

    }

    @Test
    public final void testNonEmptyAdd() {

        Set<String> s = this.createFromArgsTest("aa");
        Set<String> sExpected = this.createFromArgsRef("aa", "bb");
        /*
         * Assert that values of variables match expectations
         */
        s.add("bb");

        assertEquals(sExpected, s);

    }

    @Test
    public final void testEmptyRemove() {

        Set<String> s = this.createFromArgsTest("aa");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        String str = s.remove("aa");

        assertEquals(sExpected, s);
        assertEquals(str, "aa");

    }

    @Test
    public final void testNonEmptyRemove() {
        Set<String> s = this.createFromArgsTest("aa", "bb", "cc");
        Set<String> sExpected = this.createFromArgsRef("cc");
        /*
         * Assert that values of variables match expectations
         */

        String str = s.remove("aa");
        String str2 = s.remove("bb");

        assertEquals(str, "aa");
        assertEquals(str2, "bb");
        assertEquals(sExpected, s);

    }

    @Test
    public final void testEmptyRemoveAny() {

        Set<String> s = this.createFromArgsTest("aa", "cc", "fff", "dd");
        Set<String> sExpected = this.createFromArgsRef("aa", "cc");

        s.removeAny();
        s.removeAny();

        assertEquals(s.size(), sExpected.size());

    }

    @Test
    public final void testNonEmptyContains() {

        Set<String> s = this.createFromArgsTest("aa", "bb");

        String str = "bb";
        boolean has = s.contains(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, true);

    }

    @Test
    public final void testNonEmptyContains2() {
        Set<String> s = this.createFromArgsTest("aa", "bb", "cc");

        String str = "cdc";
        boolean has = s.contains(str);
        boolean tmp = false;

        assertEquals(has, tmp);

    }

    @Test
    public final void testEmptySize() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(s.size(), sExpected.size());

    }

    @Test
    public final void testNonEmptySize() {

        Set<String> s = this.createFromArgsTest("aa", "bb");
        Set<String> sExpected = this.createFromArgsRef("cc", "dd");

        assertEquals(sExpected.size(), s.size());

    }

}
