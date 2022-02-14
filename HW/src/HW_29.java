import components.queue.Queue;

public class HW_29 {

    public static boolean valueOfBoolExpr(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";

        boolean result = true;
        while (tokens.length() != 0) {
            String val = tokens.dequeue();

            if (val.equals("T")) {

                result = true;
            } else if (val.equals("F")) {
                result = false;
            } else if (val.equals("NOT")) {
                result = !valueOfBoolExpr(tokens);
            } else if (val.equals("(")) {
                result = valueOfBoolExpr(tokens);
            } else if (val.equals(")")) {
                result = valueOfBoolExpr(tokens);
            } else if (val.equals("AND")) {
                result = result && valueOfBoolExpr(tokens);
            } else if (val.equals("OR")) {
                result = result || valueOfBoolExpr(tokens);
            }

        }
        // This line added just to make the program compilable.
        return result;
    }
}
