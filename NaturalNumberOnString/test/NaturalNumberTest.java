import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    @Test
    public final void testNoArgumentConstructor() {

        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();

        assertEquals(s, sExpected);

    }

    @Test
    public final void testZeroStringArgumentConstructor() {

        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");

        assertEquals(s, sExpected);

    }

    @Test
    public final void testMaxIntStringArgumentConstructor() {

        NaturalNumber s = this.constructorTest("2147483648");
        NaturalNumber sExpected = this.constructorRef("2147483648");

        assertEquals(s, sExpected);

    }

    @Test
    public final void testIntegerArgumentConstructor() {

        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);

        assertEquals(s, sExpected);

    }

    @Test
    public final void testIntegerArgumentConstructor2() {

        NaturalNumber s = this.constructorTest(1);
        NaturalNumber sExpected = this.constructorRef(1);

        assertEquals(s, sExpected);

    }

    @Test
    public final void testNNArgumentConstructor() {

        NaturalNumber tmp = new NaturalNumber1L(3);

        NaturalNumber s = this.constructorTest(tmp);
        NaturalNumber sExpected = this.constructorRef(tmp);

        assertEquals(s, sExpected);

    }

    @Test
    public final void testNNArgumentConstructor2() {

        NaturalNumber tmp = new NaturalNumber1L(0);

        NaturalNumber s = this.constructorTest(tmp);
        NaturalNumber sExpected = this.constructorRef(tmp);

        assertEquals(s, sExpected);

    }

    @Test
    public final void testDivieBy10OneDigit() {

        int num = 3;

        NaturalNumber s = this.constructorTest(num);
        NaturalNumber sExpected = this.constructorRef(0);

        int result = s.divideBy10();
        int rExpected = 3;

        assertEquals(s, sExpected);
        assertEquals(result, rExpected);

    }

    @Test
    public final void testDivieBy10Zero() {

        int num = 0;

        NaturalNumber s = this.constructorTest(num);
        NaturalNumber sExpected = this.constructorRef(0);

        int result = s.divideBy10();
        int rExpected = 0;

        assertEquals(s, sExpected);
        assertEquals(result, rExpected);

    }

    @Test
    public final void testDivieBy10LargeNum() {

        NaturalNumber num = new NaturalNumber1L(123456789);

        NaturalNumber s = this.constructorTest(num);
        NaturalNumber sExpected = this.constructorRef(12345678);

        int result = s.divideBy10();
        int rExpected = 9;

        assertEquals(s, sExpected);
        assertEquals(result, rExpected);

    }

    @Test
    public final void testMultiplyBy10_1() {
        NaturalNumber s = this.constructorTest("5");
        NaturalNumber sExpected = this.constructorRef("5");
        int num = 3;
        s.multiplyBy10(num);
        sExpected.multiplyBy10(num);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testMultiplyBy10_2() {
        NaturalNumber s = this.constructorTest("253");
        NaturalNumber sExpected = this.constructorRef("253");
        int num = 2;
        s.multiplyBy10(num);
        sExpected.multiplyBy10(num);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testMultiplyBy10_3() {
        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");
        int num = 2;
        s.multiplyBy10(num);
        sExpected.multiplyBy10(num);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testMultiplyBy10_4() {
        NaturalNumber s = this.constructorTest("2");
        NaturalNumber sExpected = this.constructorRef("2");
        int num = 0;
        s.multiplyBy10(num);
        sExpected.multiplyBy10(num);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testisZero_1() {
        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");
        assertEquals(s.isZero(), sExpected.isZero());
    }

    @Test
    public final void testisZero_2() {
        NaturalNumber s = this.constructorTest("23");
        NaturalNumber sExpected = this.constructorRef("23");
        assertEquals(s.isZero(), sExpected.isZero());
    }

}
