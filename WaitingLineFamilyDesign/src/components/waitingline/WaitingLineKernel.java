package components.waitingline;

import components.standard.Standard;

/**
 * First-in-first-out (FIFO) queue kernel component with primary methods.
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 * @mathmodel type WaitingLineKernel is modeled by string of T with no
 *            duplicates
 * @initially <pre>
 * default:
 *  ensures
 *   this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Add {@code x} to the last entry of the {@code this} if {@code x} is not
     * already in the {@code this}
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code this}
     * @update this
     * @requires x is not in entries(this)
     * @ensures this = #this * <x>
     */
    void addToWaitingLine(T x);

    /**
     * Remove and returns the front entry of {@code this}
     *
     * @updates {@code this}
     * @requires this /= <>
     * @ensures #this = <removeFromWaitingLine> * this
     * @return the first entry of {@code this}
     */
    T removeFromWaitingLine();

    /**
     * Report the length of the {@code this}
     *
     * @return the length of the {@code this}
     * @ensures length = |this|
     */
    int length();

    /**
     * Report if the {@code x} is in {@code this}
     *
     * @param x
     *            the entry to be checked
     * @return true if the {@code x} is in {@code this}, false otherwise
     * @ensures <isInLine> = x is in entries({@code this})
     */
    boolean isInLine(T x);

}
