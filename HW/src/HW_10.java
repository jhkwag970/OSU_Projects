public class HW_10 {

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {

        int result = a % b;

        if (a < 0 && result != 0) {
            result = result + b;
        }

        return result;
    }

    public static int mod2(int a, int b) {

        int mod = 0;

        if (a < 0) {
            a *= -1;
        }

        while (a > 9) {
            a /= 10;
        }

        mod = a % b;

        return mod;
    }

    public static void main(String[] args) {

        int[] arr = { 432, 17, 54, -788, -101, 84, 0, -6, -195, 90 };

        for (int i = 0; i < 9; i++) {
            System.out.println(arr[i]);
            System.out.println(mod2(arr[i], 10));
            System.out.println("====================");
        }

    }

}
