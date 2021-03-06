import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;

public class HW9 {

    /**
     * Finds pair with first component {@code key} and, if such exists, moves it
     * to the front of {@code q}.
     *
     * @param <K>
     *            type of {@code Pair} key
     * @param <V>
     *            type of {@code Pair} value
     * @param q
     *            the {@code Queue} to be searched
     * @param key
     *            the key to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if there exists value: V (<(key, value)> is substring of q)
     *  then there exists value: V (<(key, value)> is prefix of q)
     * </pre>
     */
    private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {

        int length = q.length();

        Queue<Pair<K, V>> qTmp = new Queue1L<Pair<K, V>>();
        for (int i = 0; i < length; i++) {
            Pair<K, V> tmp = q.dequeue();
            if (tmp.key().equals(key)) {
                qTmp.enqueue(tmp);
            } else {
                q.enqueue(tmp);
            }

        }

        qTmp.append(q);

        q.transferFrom(qTmp);

    }

    public static void main(String[] args) {

        Map<String, String> tmp = new Map1L<String, String>();
        Map<String, String> tmp2 = new Map1L<String, String>();

        tmp.add("asd", "qwe");

        Pair<String, String> p = tmp.remove("asd");

        System.out.println(p);

        tmp.add("asde", "qwe");

        p = tmp.remove("asde");

        System.out.println(p);

    }

}
