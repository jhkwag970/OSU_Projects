import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyAdd() {

        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2");

        s.add("red", "1");
        s.add("blue", "2");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyAdd() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue",
                "2");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2", "yellow", "3");

        s.add("yellow", "3");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyRemove() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue", "2",
                "yellow", "3");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2");

        s.remove("yellow");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyRemove() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef();

        s.remove("red");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyRemoveAny() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef();

        Pair<String, String> tmp = s.removeAny();

        assertEquals(sExpected, s);
        assertEquals(tmp.key(), "red");
        assertEquals(tmp.value(), "1");
    }

    @Test
    public final void testValue() {

        Map<String, String> s = this.createFromArgsTest("red", "1");

        assertEquals(s.value("red"), "1");
    }

    @Test
    public final void testHasKey() {

        Map<String, String> s = this.createFromArgsTest("red", "1");

        assertEquals(s.hasKey("red"), true);
        assertEquals(s.hasKey("blue"), false);
    }

    @Test
    public final void testSize() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue",
                "2");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2");

        assertEquals(s.size(), sExpected.size());
        assertEquals(s.size(), 2);
    }

}