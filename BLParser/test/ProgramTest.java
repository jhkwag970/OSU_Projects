import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.program.Program;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Program}'s constructor and kernel methods.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public abstract class ProgramTest {

    /**
     * The names of a files containing a (possibly invalid) BL programs.
     */
    private static final String FILE_NAME_1 = "test/program1.bl",
            FILE_NAME_2 = "test/program2.bl",
            FILE_NAME_3 = "test/testcases/programInvalid1.bl",
            FILE_NAME_4 = "test/testcases/programInvalid2.bl",
            FILE_NAME_5 = "test/testcases/programInvalid3.bl",
            FILE_NAME_6 = "test/testcases/programInvalid4.bl",
            FILE_NAME_7 = "test/testcases/programInvalid5.bl",
            FILE_NAME_8 = "test/testcases/programInvalid6.bl",
            FILE_NAME_9 = "test/testcases/programInvalid7.bl",
            FILE_NAME_10 = "test/testcases/programInvalid8.bl",
            FILE_NAME_11 = "test/testcases/programInvalid9.bl",
            FILE_NAME_12 = "test/testcases/programInvalid10.bl",
            FILE_NAME_13 = "test/testcases/programInvalid11.bl",
            FILE_NAME_14 = "test/testcases/programInvalid12.bl",
            FILE_NAME_15 = "test/testcases/programInvalid13.bl",
            FILE_NAME_16 = "test/testcases/programInvalid14.bl",
            FILE_NAME_17 = "test/testcases/programInvalid15.bl",
            FILE_NAME_18 = "test/testcases/programInvalid16.bl",
            FILE_NAME_19 = "test/testcases/programInvalid17.bl",
            FILE_NAME_20 = "test/testcases/programInvalid18.bl",
            FILE_NAME_21 = "test/testcases/programInvalid19.bl",
            FILE_NAME_22 = "test/testcases/programInvalid20.bl",
            FILE_NAME_23 = "test/testcases/programInvalid21.bl",
            FILE_NAME_24 = "test/testcases/programInvalid22.bl",
            FILE_NAME_25 = "test/testcases/programInvalid23.bl",
            FILE_NAME_26 = "test/testcases/programInvalid24.bl",

            FILE_NAME_27 = "test/testcases/valids/program1.bl",
            FILE_NAME_28 = "test/testcases/valids/program2.bl",
            FILE_NAME_29 = "test/testcases/valids/program3.bl",
            FILE_NAME_30 = "test/testcases/valids/program4.bl";

    /**
     * Invokes the {@code Program} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorTest = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorTest();

    /**
     * Invokes the {@code Program} constructor for the reference implementation
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorRef = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    ///////////////////////

    public final void testParseValid1() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_27);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_27);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    public final void testParseValid2() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_28);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_28);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    public final void testParseValid3() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_29);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_29);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    public final void testParseValid4() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_30);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_30);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs
    // TODO - add more test cases for as many distinct syntax errors as possible

    //instruction identifier name match (x)
    //program identifier name match (x)
    //unique instruction identifier name (x)
    //instruction identifier should not be primitive instruction

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_3);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_4);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier3() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier4() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier5() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrIdentifier6() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorProgIndentifier() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_5);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorProgIndentifier2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_11);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorProgIndentifier3() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_12);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrSameName() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrSameName2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_13);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrPrimitive() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_14);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrPrimitive2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_15);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorInstrPrimitive3() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_16);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    //////////////////////

    @Test(expected = RuntimeException.class)
    public final void testParseErrorPROGRAM() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_17);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorPROGRAM2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_26);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorProgramIS() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_18);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorProgramEND() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_19);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTION() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_20);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTIONIS() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_21);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTIONEND() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_22);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTION2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_23);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTIONIS2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_24);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorINSTRUCTIONEND2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_25);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

}
