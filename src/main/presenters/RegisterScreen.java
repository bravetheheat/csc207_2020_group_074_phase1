package main.presenters;

/**
 * Command line interface of RegisterScreenController
 *
 * @author David Zhao
 */
public class RegisterScreen {

    public RegisterScreen() {

    }

    public void printScreenName() {
        System.out.println("Registration Screen");
        System.out.println();
    }

    public void promptUsername() {
        System.out.println("Enter your username");
    }

    public void promptPassword() {
        System.out.println("Enter your password");
    }

    public void promptUserType() {
        System.out.println("Select a user type:");
        System.out.println("1. Attendee");
        System.out.println("2. Organizer");
        System.out.println("3. AdminUser");
        System.out.println("4. VipUser");
    }

    public void success() {
        System.out.println("Successfully registered!");
    }

    public void error() {
        System.out.println("Sorry, your user couldn't be registered. Please try again");
    }

    public void invalidInput() {
        System.out.println("Sorry, invalid input. Please try again.");
        System.out.println();
    }


}
