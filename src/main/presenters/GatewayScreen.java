package main.presenters;

/**
 * Command line interface of GatewayScreenController
 *
 * @author David Zhao
 */
public class GatewayScreen {

    /**
     * Welcome message for the Gateway screen
     */
    public void welcomeMessage() {
        System.out.println("Gateway Screen");
        System.out.println();
    }

    /**
     * Lists the different options available in the Gateway screen
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
     * Notification that the program is saving users
     */
    public void saveUsers() {
        System.out.println("Saving users...");
        System.out.println();
    }

    /**
     * Notification that the program is saving users
     */
    public void saveRooms() {
        System.out.println("Saving rooms...");
        System.out.println();
    }

    /**
     * Notification that the program is saving users
     */
    public void saveEvents() {
        System.out.println("Saving events...");
        System.out.println();
    }

    /**
     * Notification that the program is saving messages
     */
    public void saveMessages() {
        System.out.println("Saving messages...");
        System.out.println("Warning: Please save Inboxes as well or else your store will not match up.");
        System.out.println();
    }

    /**
     * Notification that the program is saving inboxes
     */
    public void saveInboxes() {
        System.out.println("Saving inboxes...");
        System.out.println("Warning: Please save Messages as well or else your store will not match up.");
        System.out.println();
    }

    /**
     * Success message
     */
    public void success() {
        System.out.println("Success!");
    }

    /**
     * Invalid option message
     */
    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
    }
}

