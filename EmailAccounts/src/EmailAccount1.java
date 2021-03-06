import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */
    private String firstName;
    private String lastName;
    private String emailAddress;
    private static Map<String, Integer> lastNum = new Map1L<String, Integer>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        String lastNameTmp = lastName.toLowerCase();

        if (EmailAccount1.lastNum.hasKey(lastNameTmp)) {
            int num = EmailAccount1.lastNum.value(lastNameTmp) + 1;
            this.emailAddress = lastNameTmp + "." + num + "@osu.edu";
            EmailAccount1.lastNum.replaceValue(lastNameTmp, num);
        } else {
            this.emailAddress = lastName.toLowerCase() + ".1@osu.edu";
            EmailAccount1.lastNum.add(lastNameTmp, 1);
        }

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        return this.firstName + " " + this.lastName;
    }

    @Override
    public String emailAddress() {

        return this.emailAddress;
    }

    @Override
    public String toString() {

        return "Name: " + this.name() + ", Email: " + this.emailAddress();
    }

}
