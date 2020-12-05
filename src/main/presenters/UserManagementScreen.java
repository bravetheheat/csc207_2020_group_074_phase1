package main.presenters;

/**
 * The UserManagementScreen presents command line for user.
 *
 * @author Leyi Wang
 * @version 1.0
 */

public class UserManagementScreen {

    /**
     * Print the screen name for event management screen
     */
    public void printScreenName() {
        System.out.println("UserManagement Screen");
        System.out.println();
    }

    /**
     * Print available options for users to choose.
     */
    public void promptCommand() {
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("1. Add Speaker");
        System.out.println("2. Display user list");
        System.out.println("3. Exit the screen");
    }

    /**
     * Ask user to input username and password of speaker in order to add a speaker.
     */
    public void promptCreateSpeaker() {
        System.out.println("You are adding a speaker, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user name: ");
        System.out.println("Line 2 -- Enter user password: ");
    }

    /**
     * Print users information include UUID, username, and password.
     *
     * @param userList representation of the registered user
     */
    public void listUser(String userList) {
        System.out.println(userList);
    }

    /**
     * Print error of input.
     */
    public void printInvalidInput() {
        System.out.println("Invalid input. Try again.");
    }

    /**
     * Print success of adding a speaker.
     */
    public void printValidAdding() {
        System.out.println("Successfully added speaker!");
    }

    /**
     * Print failure of adding a speaker.
     */
    public void printInvalidAdding() {
        System.out.println("Fail to add speaker! Please try again.");
    }

}
