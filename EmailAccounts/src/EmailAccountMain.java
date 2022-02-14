import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Set<String> emailSet = new Set1L<String>();

        out.println("Enter the full name: ");
        String name = in.nextLine();

        while (!name.equals("")) {
            int index = name.indexOf(" ");
            String firstName = name.substring(0, index);
            String lastName = name.substring(index + 1);

            EmailAccount myAccount = new EmailAccount1(firstName, lastName);

            emailSet.add(myAccount.emailAddress());

            for (String email : emailSet) {
                out.println(email);
            }

            out.println("==============================");
            out.println("Enter the full name: ");
            name = in.nextLine();
        }

        out.println("Ending the program");
        in.close();
        out.close();
    }
}
