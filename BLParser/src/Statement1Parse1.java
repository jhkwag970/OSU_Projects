import static components.utilities.Reporter.assertElseFatalError;
import static components.utilities.Tokenizer.isCondition;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";
        tokens.dequeue();

        String conditionS = tokens.dequeue();
        assertElseFatalError(isCondition(conditionS),
                "Error: invalid condition in IF statement, but find: "
                        + conditionS);
        Condition ifCondition = parseCondition(conditionS);

        String thenS = tokens.dequeue();
        assertElseFatalError(thenS.equals("THEN"),
                "Error: Expected 'THEN' in IF statement, but find: " + thenS);

        Statement ifStatement1 = s.newInstance();
        ifStatement1.parseBlock(tokens);

        if (tokens.front().equals("ELSE")) {
            tokens.dequeue();
            Statement ifStatement2 = s.newInstance();
            ifStatement2.parseBlock(tokens);
            s.assembleIfElse(ifCondition, ifStatement1, ifStatement2);
            String endS = tokens.dequeue();
            assertElseFatalError(endS.equals("END"),
                    "Error: Expected 'END' in IF statement, but find: " + endS);
        } else if (tokens.front().equals("END")) {
            tokens.dequeue();
            s.assembleIf(ifCondition, ifStatement1);
        } else {
            assertElseFatalError(false,
                    "Error: Expected 'ELSE' or 'END' in IF statement, but find: "
                            + tokens.front());
        }

        String endIf = tokens.dequeue();
        assertElseFatalError(endIf.equals("IF"),
                "Error: Expected 'IF' in IF statement ending, but find: "
                        + endIf);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        tokens.dequeue();

        String conditionS = tokens.dequeue();
        assertElseFatalError(isCondition(conditionS),
                "Error: invalid condition in While statement, find: "
                        + conditionS);
        Condition whileCondition = parseCondition(conditionS);

        String doS = tokens.dequeue();
        assertElseFatalError(doS.equals("DO"),
                "Error: Expected 'DO', but find: " + doS);

        Statement whileStatement = s.newInstance();
        whileStatement.parseBlock(tokens);
        s.assembleWhile(whileCondition, whileStatement);

        String endS = tokens.dequeue();
        assertElseFatalError(endS.equals("END"),
                "Error: Expected 'END' in WHILE statement, but find: " + endS);

        String whileS = tokens.dequeue();
        assertElseFatalError(whileS.equals("WHILE"),
                "Error: Expected 'WHILE' in WHILE statement, but find: "
                        + whileS);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        String nextcall = tokens.dequeue();
        s.assembleCall(nextcall);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        if (tokens.front().equals("WHILE")) {
            parseWhile(tokens, this);
        } else if (tokens.front().equals("IF")) {
            parseIf(tokens, this);
        } else if (Tokenizer.isIdentifier(tokens.front())) {
            parseCall(tokens, this);
        } else {
            assertElseFatalError(false,
                    "Expected 'WHILE' or 'IF' or any vaild Identifier, but find: "
                            + tokens.dequeue());
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        int pos = 0;
        while (!tokens.front().equals("END") && !tokens.front().equals("ELSE")
                && !tokens.front().equals(Tokenizer.END_OF_INPUT)) {
            Statement s = this.newInstance();
            s.parse(tokens);
            this.addToBlock(pos, s);
            pos++;
        }

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
