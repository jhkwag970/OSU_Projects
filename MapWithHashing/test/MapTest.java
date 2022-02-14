import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Yuan Hong and Jiyong Kwag
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

    // TODO - add test cases for constructor, add,

    // hasKey, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testEmptyAdd() {

        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("aa", "bb");

        s.add("aa", "bb");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyAdd() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2");

        s.add("blue", "2");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyAdd2() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2", "yellow", "3", "green", "4");

        s.add("blue", "2");
        s.add("yellow", "3");
        s.add("green", "4");

        assertEquals(sExpected, s);
    }

    //remove, removeAny, value

    @Test
    public final void testNonEmptyRemove() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef();

        Pair<String, String> tmp = s.remove("red");
        String key = "red", value = "1";

        assertEquals(tmp.key(), key);
        assertEquals(tmp.value(), value);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyRemove2() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue", "2",
                "yellow", "3");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "yellow", "3");

        Pair<String, String> tmp = s.remove("blue");
        String key = "blue", value = "2";

        assertEquals(tmp.key(), key);
        assertEquals(tmp.value(), value);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyManyRemove() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue", "2",
                "yellow", "3", "green", "4", "skyBlue", "6");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "yellow", "3", "skyBlue", "6");

        Pair<String, String> tmp = s.remove("blue");
        Pair<String, String> tmp2 = s.remove("green");
        String key = "blue", value = "2";
        String key2 = "green", value2 = "4";

        assertEquals(tmp.key(), key);
        assertEquals(tmp.value(), value);

        assertEquals(tmp2.key(), key2);
        assertEquals(tmp2.value(), value2);

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyRemoveAny() {

        Map<String, String> s = this.createFromArgsTest("red", "1");
        Map<String, String> sExpected = this.createFromArgsRef();

        Pair<String, String> result = s.removeAny();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyRemoveAny2() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue", "2",
                "yellow", "3", "green", "4");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2", "yellow", "3", "green", "4");

        Pair<String, String> result = s.removeAny();
        Pair<String, String> result2 = s.removeAny();

        assertEquals(false, s.hasKey(result.key()));
        assertEquals(false, s.hasKey(result2.key()));

        s.add(result.key(), result.value());
        s.add(result2.key(), result2.value());

        assertEquals(sExpected, s);

    }

    @Test
    public final void testNonEmptyRemoveAny3() {

        Map<String, String> s = this.createFromArgsTest("red", "1", "blue",
                "2");
        Map<String, String> sExpected = this.createFromArgsRef("red", "1",
                "blue", "2");

        Pair<String, String> result = s.removeAny();

        assertEquals(false, s.hasKey(result.key()));

        s.add(result.key(), result.value());

        assertEquals(sExpected, s);
    }

    @Test
    public void testMapSize1() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        assertEquals(m.size(), mExpected.size());
    }

    @Test
    public void testMapSize2() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc");
        assertEquals(m.size(), mExpected.size());
    }

    @Test
    public void testMapSiz3() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        assertEquals(m.size(), mExpected.size());
    }

    @Test
    public void testMapHasKey1() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc");
        assertEquals(m.hasKey("2"), mExpected.hasKey("2"));
    }

    @Test
    public void testMapHasKey2() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc");
        assertEquals(m.hasKey("4"), mExpected.hasKey("4"));
    }

    @Test
    public void testMapHasKey3() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        assertEquals(m.hasKey("21"), mExpected.hasKey("21"));
    }

    @Test
    public void testMapHasKey4() {
        Map<String, String> m = this.createFromArgsTest();

        assertEquals(m.hasKey("21"), false);
    }

    @Test
    public void testMapValue1() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa", "2", "bbb",
                "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa", "2",
                "bbb", "3", "ccc", "11", "AAA", "12", "BBB", "13", "CCC");
        assertEquals(m.value("3"), mExpected.value("3"));
    }

    @Test
    public void testMapValue2() {
        Map<String, String> m = this.createFromArgsTest("1", "aaa");
        Map<String, String> mExpected = this.createFromArgsRef("1", "aaa");
        assertEquals(m.value("1"), mExpected.value("1"));
    }

}
