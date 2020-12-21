package main.presenters;

import java.util.List;

/**
 * Command line interface of InboxScreenController
 *
 * @author David Zhao
 */
@Deprecated
public class InboxScreen {

    public void welcomeMessage() {
        System.out.println("Inbox Screen");
        System.out.println();
    }

    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Go back");
        System.out.println("1. Open my inbox");
    }

    public void listMessages(List<String> messageStrings) {
        System.out.println("You have " + messageStrings.size() + " messages:");
        for (int i =1; i <= messageStrings.size(); i++){
            System.out.println(i +". " + messageStrings.get(i-1));
        }
    }

    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
        System.out.println();
    }

    public void inboxIsEmpty() {
        System.out.println("Your inbox is currently empty.");
        System.out.println("Go message some people!");
    }

    public void selectMessagePrompt() {
        System.out.println("Enter the number of the message you wish to open or enter 0 to return.");
    }


}
