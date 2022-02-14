import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public abstract class StatementTest {

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/statement1.bl",
            FILE_NAME_2 = "test/statement2.bl",
            FILE_NAME_3 = "test/statement3.bl",
            FILE_NAME_4 = "test/statement4.bl",
            FILE_NAME_5 = "test/statement5.bl",
            FILE_NAME_6 = "test/statement6.bl",
            FILE_NAME_7 = "test/statement7.bl",
            FILE_NAME_8 = "test/statement8.bl",
            FILE_NAME_9 = "test/statement9.bl",
            FILE_NAME_10 = "test/statement10.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructorTest = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructorRef = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    @Test
    public final void testParseValidExample() {
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValid1() {
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_3);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_3);
        tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValid2() {
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_4);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_4);
        tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
        assertEquals(sRef, sTest);
    }

    @Test
    public final void testParseValid3() {
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_5);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_5);
        tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
        assertEquals(sRef, sTest);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseError1() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseError2() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseError3() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseError4() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }

    @Test(expected = RuntimeException.class)
    public final void testParseError5() {
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        sTest.parse(tokens);
    }
}
