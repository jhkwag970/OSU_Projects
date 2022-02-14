import components.set.Set;
import components.set.Set1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {

                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement tmp = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(tmp);
                    s.addToBlock(i, tmp);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                // TODO - fill in case
                Statement sTmp = s.newInstance();
                Condition tmp = s.disassembleIf(sTmp);

                count = countOfPrimitiveCalls(sTmp);

                s.assembleIf(tmp, sTmp);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case

                Statement sIf = s.newInstance();
                Statement sElse = s.newInstance();

                Condition tmp = s.disassembleIfElse(sIf, sElse);

                count = countOfPrimitiveCalls(sIf)
                        + countOfPrimitiveCalls(sElse);

                s.assembleIfElse(tmp, sIf, sElse);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                // TODO - fill in case

                Statement sTmp = s.newInstance();
                Condition tmp = s.disassembleWhile(sTmp);

                count = countOfPrimitiveCalls(sTmp);

                s.assembleWhile(tmp, sTmp);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case

                String str = s.disassembleCall();

                Set<String> prim = new Set1L<String>();

                prim.add("turnright");
                prim.add("turnleft");
                prim.add("move");
                prim.add("infect");
                prim.add("skip");

                if (prim.contains(str)) {
                    count = 1;
                } else {
                    count = 0;
                }
                s.assembleCall(str);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
