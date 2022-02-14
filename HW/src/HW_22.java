import components.set.Set;
import components.set.Set1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

public class HW_22 {

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

                s.disassembleIf(sTmp);

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

                Condition sIfTmp = s.disassembleIfElse(sIf, sElse);

                count = countOfPrimitiveCalls(sIf)
                        + countOfPrimitiveCalls(sElse);

                s.disassembleIfElse(sIf, sElse);

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

                s.disassembleWhile(sTmp);

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

    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case

                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement tmp = s.removeFromBlock(i);
                    simplifyIfElse(tmp);
                    s.addToBlock(i, tmp);
                }

                break;
            }
            case IF: {

                Statement sTmp = s.newInstance();
                Condition tmp = s.disassembleIf(sTmp);

                simplifyIfElse(sTmp);
                s.assembleIf(tmp, sTmp);

                break;
            }
            case IF_ELSE: {

                Statement sIf = s.newInstance();
                Statement sElse = s.newInstance();

                Condition sIfTmp = s.disassembleIfElse(sIf, sElse);

                if (sIfTmp.values().equals("NEXT_IS_NOT_EMPTY")) {
                    sIfTmp = Condition.NEXT_IS_EMPTY;
                    simplifyIfElse(sIf);
                    simplifyIfElse(sElse);
                    s.assembleIfElse(sIfTmp, sIf, sElse);

                }

                if (sIfTmp.values().equals("NEXT_IS_NOT_ENEMY")) {
                    sIfTmp = Condition.NEXT_IS_ENEMY;
                    simplifyIfElse(sIf);
                    simplifyIfElse(sElse);
                    s.assembleIfElse(sIfTmp, sIf, sElse);
                }
                if (sIfTmp.values().equals("NEXT_IS_NOT_FRIEND")) {
                    sIfTmp = Condition.NEXT_IS_FRIEND;
                    simplifyIfElse(sIf);
                    simplifyIfElse(sElse);
                    s.assembleIfElse(sIfTmp, sIf, sElse);
                }

                if (sIfTmp.values().equals("NEXT_IS_NOT_WALL")) {
                    sIfTmp = Condition.NEXT_IS_WALL;
                    simplifyIfElse(sIf);
                    simplifyIfElse(sElse);
                    s.assembleIfElse(sIfTmp, sIf, sElse);
                }

                break;
            }
            case WHILE: {

                Statement sTmp = s.newInstance();
                Condition tmp = s.disassembleWhile(sTmp);

                simplifyIfElse(sTmp);

                s.assembleWhile(tmp, sTmp);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }
}
