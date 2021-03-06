import components.binarytree.BinaryTree;

public class HW_17 {

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        boolean ordered = false;

        BinaryTree<Integer> left = t.newInstance(), right = t.newInstance();

        if (t.size() > 0) {
            int root = t.disassemble(left, right);

            if (left.root() >= root && right.root() >= root) {
                ordered = satisfiesHeapOrdering(left)
                        && satisfiesHeapOrdering(right);
            }

            t.assemble(root, left, right);

        }

        return ordered;
    }

    public static void main(String[] args) {

    }

}
