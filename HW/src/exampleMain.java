import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;

public class exampleMain {

    public static void main(String[] args) {
        NaturalNumber n1 = new NaturalNumber2();
        NaturalNumber n2 = new NaturalNumber1L();
        boolean b = n1.equals(n2);

        System.out.println(b);
    }

}