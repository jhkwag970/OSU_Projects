import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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
    public final void testEmptyAdd2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("aa", "bb", "cc");

        s.add("aa");
        s.add("bb");
        s.add("cc");
        /*
         * Assert that values of variables match expectations
         */
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
    public final void testNonEmptyAdd2() {

        Set<String> s = this.createFromArgsTest("aa");
        Set<String> sExpected = this.createFromArgsRef("aa", "bb", "cc", "dd");
        /*
         * Assert that values of variables match expectations
         */
        s.add("bb");
        s.add("cc");
        s.add("dd");

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

        assertEquals(sExpected, s);

    }

    @Test
    public final void testEmptyRemoveAny() {

        Set<String> s = this.createFromArgsTest("aa");

        String str = s.removeAny();

        assertEquals(s.contains(str), false);
        assertEquals(str, "aa");
        assertEquals(s.size(), 0);

    }

    @Test
    public final void testEmptyRemoveAny2() {

        Set<String> s = this.createFromArgsTest("aa", "cc", "fff", "dd");

        String str = s.removeAny();
        String str2 = s.removeAny();

        assertEquals(s.contains(str), false);
        assertEquals(s.contains(str2), false);

        assertEquals(s.size(), 2);

    }

    @Test
    public final void testRemoveAny3() {
        Set<String> s = this.createFromArgsTest("3", "4", "5", "2");
        Set<String> sExpected = this.createFromArgsRef("3", "4", "5", "2");
        String removedS = s.removeAny();
        assertTrue(sExpected.contains(removedS));
        assertTrue(!s.contains(removedS));
        sExpected.remove(removedS);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testNonEmptyContains() {

        Set<String> s = this.createFromArgsTest("aa");

        String str = "aa";
        boolean has = s.contains(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, true);

    }

    @Test
    public final void testNonEmptyContains2() {
        Set<String> s = this.createFromArgsTest("aa", "bb", "cc");

        String str = "bb";
        boolean has = s.contains(str);

        assertEquals(has, true);

    }

    @Test
    public final void testNonEmptyContains3() {
        Set<String> s = this.createFromArgsTest("aa", "bb", "cc");

        String str = "cc";
        boolean has = s.contains(str);

        assertEquals(has, true);

    }

    @Test
    public final void testNonEmptyContains4() {
        Set<String> s = this.createFromArgsTest();

        String str = "aa";
        boolean has = s.contains(str);

        assertEquals(has, false);

    }

    @Test
    public final void testNonEmptyContains5() {
        Set<String> s = this.createFromArgsTest("aa", "bb", "cc", "dd", "ee",
                "ff");

        String str = "hh";
        boolean has = s.contains(str);

        assertEquals(has, false);

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

    @Test
    public final void testNonEmptySize2() {

        Set<String> s = this.createFromArgsTest("aa", "bb", "cc", "dd", "ee",
                "ff", "jj");
        Set<String> sExpected = this.createFromArgsRef("cc", "dd", "kk", "tt",
                "yy", "jj", "ll");

        assertEquals(sExpected.size(), s.size());

    }

    @Test
    public final void testNonEmptySize3() {
        Set<String> s = this.createFromArgsTest("1", "2", "12", "31");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "12", "31");
        assertEquals(s.size(), sExpected.size());
    }

}
