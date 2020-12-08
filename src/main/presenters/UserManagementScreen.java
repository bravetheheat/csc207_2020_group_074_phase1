package main.presenters;

/**
 * The UserManagementScreen presents command line for user.
 *
 * @author Leyi Wang
 * @version 1.0
 */

public class  UserManagementScreen {

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
        System.out.println("1. Add User");
        System.out.println("2. Display user list");
        System.out.println("3. Exit the screen");
    }

    /**
     * Print available options for admin users to choose.
     */
    public void promptCommandAdminUser() {
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("1. Add User");
        System.out.println("2. Display user list");
        System.out.println("3. Delete User by ID");
        System.out.println("4. Delete User by username");
        System.out.println("5. Exit the screen");
    }

    /**
     * Ask user to input username, password and user type in order to add a user.
     */
    public void promptCreateUser() {
        System.out.println("You are adding a user, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user name: ");
        System.out.println("Line 2 -- Enter user password: ");
        System.out.println("Line 2 -- Enter user type: ");
    }

    /**
     * Ask user to input if in order to remove a user.
     */
    public void promptRemoveUserByID() {
        System.out.println("You are removing a user, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user ID: ");
    }

    /**
     * Ask user to input username in order to remove a user.
     */
    public void promptRemoveUserByName() {
        System.out.println("You are removing a user, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user name: ");
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
     * Print success of adding a user.
     */
    public void printValidAdding() {
        System.out.println("Successfully added user!");
    }

    /**
     * Print failure of adding a user.
     */
    public void printInvalidAdding() {
        System.out.println("Fail to add user! Please try again. This failure may due to repeated username.");
    }

    public void printValidRemoving() {
        System.out.println("Successfully removed user!");
    }

    public void printInvalidRemoving() {
        System.out.println("Fail to remove user! Please try again.");
    }
}
