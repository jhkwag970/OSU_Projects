import components.array.Array;
import components.array.Array1L;

public class HW11 {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each "X"
     * is a digit '0'-'9'.
     */
    public HW11(String pNum) {
        this.rep = pNum;
    }

    @Override
    public int hashCode() {
        int length = this.rep.length() - 1, sum = 0;

        for (int i = 0; i <= length; i++) {
            char digit = this.rep.charAt(i);
            if (digit != '-') {
                sum += Character.digit(digit, 36);
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        Array<String> array = new Array1L<>(7);

        array.setEntry(0, "green");
        String str = array.entry(0);
        System.out.println(array.entry(0));

        array.makeUnexaminable(0);

        System.out.println(str);
        System.out.println(array.entry(0));
    }

}
