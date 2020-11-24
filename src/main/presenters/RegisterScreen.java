package main.presenters;

/**
 * Command line interface of RegisterScreenController
 *
 * @author David Zhao
 */
public class RegisterScreen {

    /**
     * No parameter is need to instantiate an instance of RegisterScreen
     */
    public RegisterScreen() {}

    /**
     * Print out the screen name
     */
    public void printScreenName() {
        System.out.println("Registration Screen");
        System.out.println();
    }

    /**
     * Print out the message that prompts the user to enter their username
     */
    public void promptUsername() {
        System.out.println("Enter your username");
    }

    /**
     * Print out the message that prompts the user to enter their password
     */
    public void promptPassword() {
        System.out.println("Enter your password");
    }

    /**
     * Print out the message that prompts the user to select a user type they wish to register as
     */
    public void promptUserType() {
        System.out.println("Select a user type:");
        System.out.println("1. Attendee");
        System.out.println("2. Organizer");
    }

    /**
     * Notify the user that they have registered successfully
     */
    public void success() {
        System.out.println("Successfully registered!");
    }

    /**
     * Print out the error message when the program cannot register a new user with the
     * given username and password
     */
    public void error() {
        System.out.println("Sorry, your user couldn't be registered. Please try again");
    }

    /**
     * Print out the error message when the user input is invalid
     */
    public void invalidInput() {
        System.out.println("Sorry, invalid input. Please try again.");
        System.out.println();
    }


}
