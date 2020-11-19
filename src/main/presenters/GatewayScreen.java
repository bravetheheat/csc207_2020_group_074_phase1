package main.presenters;

/**
 * Command line interface of GatewayScreenController
 *
 * @author David Zhao
 */
public class GatewayScreen {

    /**
     * Print out the welcome message
     */
    public void welcomeMessage() {
        System.out.println("Gateway Screen");
        System.out.println();
    }

    /**
     * Print out a list of options
     */
    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Return to previous screen.");
        System.out.println("1. Save all");
        System.out.println("2. Save users");
        System.out.println("3. Save rooms");
        System.out.println("4. Save events");
        System.out.println("5. Save messages");
        System.out.println("6. Save inboxes");
    }

    /**
     * Notify that the program is saving the user data
     */
    public void saveUsers() {
        System.out.println("Saving users...");
        System.out.println();
    }

    /**
     * Notify that the program is saving the room data
     */
    public void saveRooms() {
        System.out.println("Saving rooms...");
        System.out.println();
    }

    /**
     * Notify that the program is saving the event data
     */
    public void saveEvents() {
        System.out.println("Saving events...");
        System.out.println();
    }

    /**
     * Notify that the program is saving the message data
     */
    public void saveMessages() {
        System.out.println("Saving messages...");
        System.out.println();
    }

    /**
     * Notify that the program is saving the inbox data
     */
    public void saveInboxes() {
        System.out.println("Saving inboxes...");
        System.out.println();
    }

    /**
     * Notify that the operation is successful
     */
    public void success() {
        System.out.println("Success!");
    }

    /**
     * Tell the user that the option they entered is invalid
     */
    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
    }
}

