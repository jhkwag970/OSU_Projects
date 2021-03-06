import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyPushk() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("aa");

        s.push("aa");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyPush2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("bb", "aa");

        s.push("aa");
        s.push("bb");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyPush() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa");
        Stack<String> sExpected = this.createFromArgsRef("bb", "aa");

        s.push("bb");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testNonEmptyPush2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa");
        Stack<String> sExpected = this.createFromArgsRef("cc", "bb", "aa");

        s.push("bb");
        s.push("cc");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptyPop() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa");
        Stack<String> sExpected = this.createFromArgsRef();

        String result = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(result, "aa");
    }

    @Test
    public final void testEmptyPop2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa", "bb");
        Stack<String> sExpected = this.createFromArgsRef();

        String result = s.pop();
        String result2 = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(result, "aa");
        assertEquals(result2, "bb");
    }

    @Test
    public final void testNonEmptyPop() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("cc", "kk", "ff", "dd");
        Stack<String> sExpected = this.createFromArgsRef("kk", "ff", "dd");

        String result = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(result, "cc");
    }

    @Test
    public final void testNonEmptyPop2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("cc", "kk", "ff", "dd");
        Stack<String> sExpected = this.createFromArgsRef("ff", "dd");

        String result = s.pop();
        String result2 = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(result, "cc");
        assertEquals(result2, "kk");
    }

    @Test
    public final void testEmptyLength() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();

        int length = s.length(), length2 = sExpected.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(length, length2);
        assertEquals(length, 0);
    }

    @Test
    public final void testNonEmptyLength() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa", "bb", "cc", "dd");
        Stack<String> sExpected = this.createFromArgsRef("aa", "bb", "cc",
                "dd");

        int length = s.length(), length2 = sExpected.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(length, length2);
        assertEquals(length, 4);
    }

    @Test
    public final void testNonEmptyLength2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa", "bb", "cc", "dd", "ee",
                "ff", "gg", "ll", "hh", "pp");
        Stack<String> sExpected = this.createFromArgsRef("aa", "bb", "cc", "dd",
                "ee", "ff", "gg", "ll", "hh", "pp");

        int length = s.length(), length2 = sExpected.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(length, length2);
        assertEquals(length, 10);
    }

    @Test
    public final void testNonEmptyTop() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("aa");
        Stack<String> sExpected = this.createFromArgsRef("aa");

        String str = s.top();
        String result = "aa";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(str, result);

    }

    @Test
    public final void testNonEmptyTop2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("kk", "bb", "cc");
        Stack<String> sExpected = this.createFromArgsRef("kk", "bb", "cc");

        String str = s.top();
        String result = "kk";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(str, result);

    }

    @Test
    public final void testNonEmptyReplaceTop() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("kk");
        Stack<String> sExpected = this.createFromArgsRef("hh");

        String str = s.replaceTop("hh");
        String result = "kk";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(str, result);

    }

    @Test
    public final void testNonEmptyReplaceTop2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("ii", "ss", "pp");
        Stack<String> sExpected = this.createFromArgsRef("fff", "ss", "pp");

        String str = s.replaceTop("fff");
        String result = "ii";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(str, result);

    }

    @Test
    public final void testEmptyClear() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();

        s.clear();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);

    }

    @Test
    public final void testNonEmptyClear() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("ii", "ss", "pp");
        Stack<String> sExpected = this.createFromArgsRef();

        s.clear();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);

    }

    @Test
    public final void testEmptyNewInstance() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = s.newInstance();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);

    }

}
