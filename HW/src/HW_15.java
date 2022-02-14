import java.util.Comparator;

import components.queue.Queue;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

public class HW_15 {

    private static class IntegerGE implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {

        int length = q.length();
        Queue<T> qTmp = q.newInstance();

        while (length > 0 && order.compare(q.front(), x) < 0) {
            T entry = q.dequeue();
            qTmp.enqueue(entry);
            length--;
        }
        qTmp.enqueue(x);
        qTmp.append(q);
        q.transferFrom(qTmp);

    }

    public static void main(String[] args) {
        Comparator<Integer> ci = new IntegerGE();
        SortingMachine<Integer> si = new SortingMachine1L<>(ci);

        si.add(0);
        System.out.println(si);
        si.add(7);
        System.out.println(si);
        si.add(-1);
        System.out.println(si);

        si.changeToExtractionMode();
        System.out.println(si);

        int i = si.removeFirst();
        System.out.println(si);
        System.out.println(i);

        si.clear();
        System.out.println(si);
        System.out.println(i);

    }

}
