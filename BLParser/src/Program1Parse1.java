import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        String instr = tokens.dequeue();
        Reporter.assertElseFatalError(instr.equals("INSTRUCTION"),
                "The Instruction is not correctly named");

        String startName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(startName),
                "Not correct Identifier name");

        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Not correct Identifier name");

        body.parseBlock(tokens);

        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Not correct end identifier name");

        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(startName.equals(endName),
                "The begining name and end name of the instruction are not equal");

        return startName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /// Program Name

        String pro = tokens.dequeue();
        Reporter.assertElseFatalError(pro.equals("PROGRAM"),
                "The Program does not start proper name");

        String proName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(proName),
                "Not proper Program name");

        this.setName(proName);

        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Not Proper Program name");

        /// Context

        Map<String, Statement> cntxt = this.newContext();
        Set<String> primInstr = new Set1L<String>();

        primInstr.add("move");
        primInstr.add("turnleft");
        primInstr.add("turnright");
        primInstr.add("infect");
        primInstr.add("skip");

        while (tokens.front().equals("INSTRUCTION")) {
            Statement body = this.newBody();

            String instr = parseInstruction(tokens, body);
            Reporter.assertElseFatalError(!cntxt.hasKey(instr),
                    "Already Existing Instruction Name of the file");

            if (!primInstr.contains(instr)) {
                cntxt.add(instr, body);
            } else {
                boolean instrNamePrim = false;
                Reporter.assertElseFatalError(instrNamePrim,
                        "Instruction name is Primitive Instruction");
            }

        }

        ////

        this.swapContext(cntxt);
        Statement blockBody = this.newBody();

        String beg = tokens.dequeue();
        Reporter.assertElseFatalError(beg.equals("BEGIN"),
                "Body of program must starts with BEGIN");

        blockBody.parseBlock(tokens);
        this.swapBody(blockBody);

        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Not proper Program END");

        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(proName.equals(endName),
                "Begining and end Program name do not match");

        Reporter.assertElseFatalError(
                Tokenizer.END_OF_INPUT.equals(tokens.front()),
                "Must be end of the Program");

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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
