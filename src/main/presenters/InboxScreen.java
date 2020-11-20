package main.presenters;

import java.util.List;

/**
 * Command line interface of InboxScreenController
 *
 * @author David Zhao
 */
public class InboxScreen {

    /**
     * Prints a welcome message
     */
    public void welcomeMessage() {
        System.out.println("Inbox Screen");
        System.out.println();
    }

    /**
     * Prints a list of user options
     */
    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Go back");
        System.out.println("1. Open my inbox");
    }

    /**
     * Prints a list of message
     *
     * @param messageStrings A list of String that represents Messages
     */
    public void listMessages(List<String> messageStrings) {
        System.out.println("You have " + messageStrings.size() + " messages:");
        for (int i = 1; i <= messageStrings.size(); i++) {
            System.out.println(i + ". " + messageStrings.get(i - 1));
        }
    }

    /**
     * Prints an invalid option error message
     */
    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
        System.out.println();
    }

    /**
     * Prints a warning that the user's inbox is empty
     */
    public void inboxIsEmpty() {
        System.out.println("Your inbox is currently empty.");
        System.out.println("Go message some people!");
    }

    /**
     * Prints an option to open a message in more detail
     */
    public void selectMessagePrompt() {
        System.out.println("Enter the number of the message you wish to open or enter 0 to return.");
    }


}
