package main.presenters;

/**
 * Command line interface of LoginScreenController
 *
 * @author David Zhao
 */
public class LoginScreen {

    public void printScreenName() {
        System.out.println("Login Screen");
        System.out.println();
    }

    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Return to previous screen.");
        System.out.println("1. Login");
    }

    public void promptUsername() {
        System.out.println("Enter your username");
    }

    public void promptPassword() {
        System.out.println("Enter your password");
    }

    public void fail() {
        System.out.println("Sorry, please log out first.");
        System.out.println();
    }

    public void success() {
        System.out.println("Successfully logged in!");
        System.out.println();
    }

    public void signout() {
        System.out.println("Successfully logged out!");
        System.out.println();
    }

    public void error() {
        System.out.println("Sorry, your username or password is incorrect. Please try again.");
        System.out.println();

    }

    public void invalidInput(){
        System.out.println("Sorry, your input is invalid. Please try again.");
        System.out.println();
    }

}
