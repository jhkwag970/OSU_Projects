import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> avgValue = new Sequence1L<Integer>();
        int length = s1.length();

        for (int i = 0; i < s1.length() - 1; i++) {
            long result = ((long) s1.entry(i) + (long) s1.entry(i + 1)) / 2;

            avgValue.add(i, (int) result);

        }

        s2.transferFrom(avgValue);

    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth2(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        if (s1.length() > 1) {
            int first = s1.remove(0);

            int second = s1.remove(0);

            s1.add(0, second);
            smooth2(s1, s2);
            long result = ((long) first + (long) second) / 2;

            s2.add(0, (int) result);

            s1.add(0, first);

        } else {
            s2.clear();
        }

    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @returns a average of two consecutive pairs of the given sequence
     * @requires |s1| >= 1
     *
     * @ensures <pre>
     * |avgValue| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        avgValues = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth3(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> avgValue = new Sequence1L<Integer>();

        for (int i = 0; i < s1.length() - 1; i++) {
            long result = ((long) s1.entry(i) + (long) s1.entry(i + 1) / 2);

            avgValue.add(i, (int) result);
        }

        return avgValue;

    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @returns a average of two consecutive pairs of the given sequence
     * @requires |s1| >= 1
     *
     * @ensures <pre>
     * |avgValue| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        avgValues = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth4(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> avgValue = new Sequence1L<Integer>();

        if (s1.length() > 1) {
            int first = s1.remove(0);

            int second = s1.remove(0);

            s1.add(0, second);
            avgValue = smooth3(s1);
            long result = ((long) first + (long) second) / 2;

            avgValue.add(0, (int) result);

            s1.add(0, first);

        }

        return avgValue;

    }

    public static void main(String args[]) {
        Sequence<Integer> seq1 = new Sequence1L<Integer>();
        Sequence<Integer> seq2 = new Sequence1L<Integer>();

        seq1.add(0, 1073741823);
        seq1.add(1, 1073741823);

        smooth(seq1, seq2);

        System.out.println(seq2);

    }

}