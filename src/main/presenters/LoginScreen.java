package main.presenters;

public class LoginScreen {

    public void printScreenName() {
        System.out.println("Login Screen");
        System.out.println();
    }

    public void promptUsername() {
        System.out.println("Enter your username");
    }

    public void promptPassword() {
        System.out.println("Enter your password");
    }

    public void fail() {
        System.out.println("Sorry, please log out first.");
    }

    public void success() {
        System.out.println("Successfully logged in!");
    }

    public void signout() {
        System.out.println("Successfully logged out!");
    }

    public void error() {
        System.out.println("Sorry, your username or password is incorrect. Please try again");
    }

}
