package main.presenters;

/**
 * Command line interface of LoginScreenController
 *
 * @author David Zhao, Leyi Wang
 */
public class LoginScreen {

    /**
     * Print the screen name for event management screen
     */
    public void printScreenName() {
        System.out.println("Login Screen");
        System.out.println();
    }

    /**
     * Print available options for users to choose.
     */
    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Return to previous screen.");
        System.out.println("1. Login");
    }

    /**
     * Displaying command line for user to input username.
     */
    public void promptUsername() {
        System.out.println("Enter your username");
    }

    /**
     * Displaying command line for user to input password.
     */
    public void promptPassword() {
        System.out.println("Enter your password");
    }

    /**
     * Print failure of logging in.
     */
    public void fail() {
        System.out.println("Sorry, please log out first.");
        System.out.println();
    }

    /**
     * Print success of logging in.
     */
    public void success() {
        System.out.println("Successfully logged in!");
        System.out.println();
    }

    /**
     * Print success of logging out.
     */
    public void signout() {
        System.out.println("Successfully logged out!");
        System.out.println();
    }

    /**
     * Print invalid username or password.
     */
    public void error() {
        System.out.println("Sorry, your username or password is incorrect. Please try again.");
        System.out.println();

    }

    /**
     * Print error of input.
     */
    public void invalidInput(){
        System.out.println("Sorry, your input is invalid. Please try again.");
        System.out.println();
    }

}
