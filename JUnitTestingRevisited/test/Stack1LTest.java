import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> st = new Stack1L<String>();

        return st;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> st = new Stack3<String>();
        return st;
    }

}