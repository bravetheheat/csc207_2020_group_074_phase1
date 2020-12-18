package main.presenters;

/**
 * The AdminUserScreen is a presenter class that displays Admin user options.
 *
 * @author Leyi Wang
 * @version 1.0
 */
public class AdminUserScreen {
    /**
     * Constructor of an Admin User Screen.
     */
    public AdminUserScreen() {

    }

    public void printScreenName() {
        System.out.println("Here is the Admin User screen");
    }

    public void prompt() {
        System.out.println("Choose from the following options by entering a number:");
        System.out.println("0. Logout");
        System.out.println("1. User management");
        System.out.println("2. Register for events");
        System.out.println("3. Message");
        System.out.println("4. Inbox");
        System.out.println("5. Data Management");
        System.out.println("6. Export Event to HTML");
    }

    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}