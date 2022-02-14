import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void sampleTest() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    // TODO: add here other test cases for BinarySearchTreeMethods.isInTree
    // (and for BinarySearchTreeMethods.removeSmallest)

    @Test
    public void RightEmpty() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("e", "a", "c", "b");
        BinaryTree<String> t2 = createBSTFromArgs("e", "a", "c", "b");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void LeftEmpty() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("a", "b", "c", "d");
        BinaryTree<String> t2 = createBSTFromArgs("a", "b", "c", "d");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void bottomValue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        BinaryTree<String> t2 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "e");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void middleValue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        BinaryTree<String> t2 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void rootValue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        BinaryTree<String> t2 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void falseValue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        BinaryTree<String> t2 = createBSTFromArgs("c", "d", "b", "a", "f", "h",
                "g", "k", "s", "e");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "j");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void smallest1() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        System.out.println(BinaryTreeUtility.treeToString(t1));
        /*
         * Call method under test
         */
        String inTree = BinarySearchTreeMethods.removeSmallest(t1);
        String inTree2 = BinarySearchTreeMethods.removeSmallest(t2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(inTree, "a");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest2() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("f", "a", "b", "e", "d");
        BinaryTree<String> t2 = createBSTFromArgs("f", "a", "b", "e", "d");
        /*
         * Call method under test
         */
        String inTree = BinarySearchTreeMethods.removeSmallest(t1);
        String inTree2 = BinarySearchTreeMethods.removeSmallest(t2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(inTree, "a");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest3() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("a", "j", "b", "e", "d", "k",
                "f");
        BinaryTree<String> t2 = createBSTFromArgs("a", "j", "b", "e", "d", "k",
                "f");
        /*
         * Call method under test
         */
        String inTree = BinarySearchTreeMethods.removeSmallest(t1);
        String inTree2 = BinarySearchTreeMethods.removeSmallest(t2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(inTree, "a");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest4() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c", "a", "b", "d", "f", "h",
                "g", "k");
        BinaryTree<String> t2 = createBSTFromArgs("c", "a", "b", "d", "f", "h",
                "g", "k");
        /*
         * Call method under test
         */
        String inTree = BinarySearchTreeMethods.removeSmallest(t1);
        String inTree2 = BinarySearchTreeMethods.removeSmallest(t2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(inTree, "a");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest5() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("c");
        BinaryTree<String> t2 = createBSTFromArgs("c");
        /*
         * Call method under test
         */
        String inTree = BinarySearchTreeMethods.removeSmallest(t1);
        String inTree2 = BinarySearchTreeMethods.removeSmallest(t2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(inTree, "c");
        assertEquals(t2, t1);
    }

}
