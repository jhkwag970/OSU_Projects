package components.waitingline;

/**
 * {@code QueueKernel} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 *
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * Report the value of index in {@code this}
     *
     * @param x
     *            index of {@code this}
     * @update this
     * @requires this /= <> and x is in this
     * @ensure remove the value of index in entries(this)
     * @return the value of given index
     */
    T removeFromLine(int x);

    /**
     * Report the index of {@code x} in {@code this}
     *
     * @param value
     *            the value that is looking for
     * @requires this /= <> and x is in this
     * @ensures <pre>
     *
     * </pre>
     * @return the index of {@code x} in {@code this}
     */
    int findPosition(T value);

    /**
     * Replace the front of {@code this} with {@code x}, and returns the old
     * front
     *
     * @param x
     *            the new front entry
     * @aliases reference {@code x}
     * @update this
     * @requires this /=<>, {@code x} is not already within {@code this}
     * @return the old front entry
     * @ensures <pre>
     * <replaceFront> is prefix of #this and
     * this = <x> * #this[1,|#this|)
     * </pre>
     */
    T replaceFrontWaitingLine(T x);

    /**
     * Reports the front of {@code this}
     *
     * @aliases reference {@code x}
     * @requires this /= <>
     * @ensures <frontOfWaitingLine> is prefix of {@code this}
     * @return the front entry of {@code this}
     */
    T frontOfWaitingLine();
}
