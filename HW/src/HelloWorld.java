import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {

        if (leftStack.length() > newLeftLength) {
            leftStack.flip();
            int i = 0;
            while (i < (leftStack.length() - newLeftLength)) {
                T value = leftStack.pop();
                rightStack.push(value);
            }

            leftStack.flip();
        } else if (leftStack.length() < newLeftLength) {
            leftStack.flip();
            int i = 0;
            while (i < (rightStack.length() - newLeftLength)) {
                T value = rightStack.pop();
                leftStack.push(value);
            }

            leftStack.flip();
        }

    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {

        Queue<T> tmp = q.newInstance();

        int length = q.length() - 1;
        for (int i = 0; i <= length; i++) {
            T value = q.dequeue();
            if (!value.equals(x)) {
                q.enqueue(value);
            } else {
                tmp.enqueue(value);
            }

        }

        tmp.append(q);
        q.transferFrom(tmp);
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {

        int cnt = 1;
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();

        if (t.height() != 0) {
            T root = t.disassemble(left, right);

            cnt++;
            cnt += size(left) + size(right);

            t.assemble(root, left, right);

        }

        return cnt;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size2(BinaryTree<T> t) {

        int cnt = 1;

        for (T x : t) {
            cnt++;
        }

        return cnt;
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {

        String str = "";
        BinaryTree<T> left = t.newInstance(), right = t.newInstance();

        if (t.height() > 0) {
            T root = t.disassemble(left, right);
            str = root + "(" + treeToString(left) + treeToString(right) + ")";
            t.assemble(root, left, right);
        } else {
            str += "()";
        }

        return str;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> copy = t.newInstance(), left = t.newInstance(),
                right = t.newInstance();

        if (t.height() > 0) {
            int root = t.disassemble(left, right);
            copy.assemble(root, left, right);
            t.assemble(root, left, right);
        }

        return copy;
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {

        boolean hasValue = false;
        BinaryTree<T> left = t.newInstance(), right = t.newInstance();

        if (t.height() > 0) {
            T root = t.disassemble(left, right);
            if (root.compareTo(x) == 0) {
                hasValue = true;
            } else if (root.compareTo(x) > 0) {
                hasValue = isInTree(left, x);
            } else if (root.compareTo(x) < 0) {
                hasValue = isInTree(right, x);
            }
            t.assemble(root, left, right);

        }

        return hasValue;
    }

    private static void prependRepFromInt(Stack<Integer> digits, int i) {
        /**
         * @decreases i
         */
        if (i > 0) {
            int lastDigit = i % 10;
            prependRepFromInt(digits, i / 10);
            digits.push(lastDigit);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        Map<String, String> str = new Map1L<String, String>();

        Queue<String> q = new Queue1L<String>();
        Set<String> s = new Set1L<String>();
        Sequence<String> sq = new Sequence1L<String>();
        Stack<Integer> st = new Stack1L<Integer>();

        prependRepFromInt(st, 134);

        System.out.println(st);

    }

}
