import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;

public class example {

    /**
     * Definition of whitespace separators.
     */
    private static final String SEPARATORS = " \t\n\r";
    public static final String END_OF_INPUT = "### END OF INPUT ###";

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {

        int cnt = position;
        String result = "";

//        Set<Character> sep = new Set1L<Character>();
//
//        for (int i = 0; i < SEPARATORS.length(); i++) {
//            if (!sep.contains(SEPARATORS.charAt(i))) {
//                sep.add(SEPARATORS.charAt(i));
//            }
//        }
//
//        if (sep.contains(text.charAt(position))) {
//
//            while (cnt < text.length() && sep.contains(text.charAt(cnt))) {
//                cnt++;
//            }
//
//        } else {
//
//            while (cnt < text.length() && !sep.contains(text.charAt(cnt))) {
//                cnt++;
//            }
//
//        }
//
//        result = text.substring(position, cnt);

        boolean isWord = true;
        boolean hasWord = false;

        while (isWord) {
            for (int i = 0; i < SEPARATORS.length(); i++) {
                if (SEPARATORS.charAt(i) == text.charAt(position)) {
                    isWord = false;
                }
            }

            if (isWord) {
                cnt++;
                hasWord = true;
            } else if (hasWord) {
                result = text.substring(position, cnt);
            } else {
                result = text.substring(position, cnt + 1);
            }

        }
        return result;
    }

    /**
     * Tokenizes the entire input getting rid of all whitespace separators and
     * returning the non-separator tokens in a {@code Queue<String>}.
     *
     * @param in
     *            the input stream
     * @return the queue of tokens
     * @requires in.is_open
     * @ensures <pre>
     * tokens =
     *   [the non-whitespace tokens in #in.content] * <END_OF_INPUT>  and
     * in.content = <>
     * </pre>
     */
    public static Queue<String> tokens(SimpleReader in) {

        Queue<String> tok = new Queue1L<String>();

        while (!in.atEOS()) {
            String line = in.nextLine();
            int pos = 0;
            while (pos < line.length()) {
                String token = nextWordOrSeparator(line, pos);

                if (!(token.contains(" ") || token.contains("\r")
                        || token.contains("\n") || token.contains("\t"))) {

                    tok.enqueue(token);
                }

                pos += token.length();
            }

        }

        tok.enqueue(END_OF_INPUT);

        return tok;
    }

}
