import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.tree.Tree;

public class HW_20 {

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {

        Sequence<Tree<T>> child = new Sequence1L<Tree<T>>();
        int cnt = 0;

        if (t.numberOfSubtrees() > 0) {
            T root = t.disassemble(child);
            for (int i = 0; i < t.numberOfSubtrees(); i++) {
                cnt = cnt + size(child.entry(i));
            }
            cnt++;
            t.assemble(root, child);
        }

        return cnt;
    }

    public static <T> int size2(Tree<T> t) {
        int cnt = 0;
        for (T x : t) {
            cnt++;
        }
        return cnt;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {

        Sequence<Tree<T>> child = new Sequence1L<Tree<T>>();
        int height = 0;
        T root = t.disassemble(child);

        for (int i = 0; i < child.length(); i++) {
            int subHeight = height(child.entry(i));
            if (subHeight > height) {
                height = subHeight;
            }
        }

        return height + 1;
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        Sequence<Tree<Integer>> tmp = new Sequence1L<Tree<Integer>>();
        int max = 0;

        if (t.numberOfSubtrees() > 0) {

            int root = t.disassemble(tmp);
            for (int i = 0; i < tmp.length(); i++) {
                max = max(tmp.entry(i));
            }

            if (max < root) {
                max = root;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
