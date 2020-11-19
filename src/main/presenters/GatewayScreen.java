package main.presenters;

/**
 * Command line interface of GatewayScreenController
 *
 * @author David Zhao
 */
public class GatewayScreen {

    public void welcomeMessage() {
        System.out.println("Gateway Screen");
        System.out.println();
    }

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

    public void saveUsers() {
        System.out.println("Saving users...");
        System.out.println();
    }

    public void saveRooms() {
        System.out.println("Saving rooms...");
        System.out.println();
    }

    public void saveEvents() {
        System.out.println("Saving events...");
        System.out.println();
    }

    public void saveMessages() {
        System.out.println("Saving messages...");
        System.out.println();
    }

    public void saveInboxes() {
        System.out.println("Saving inboxes...");
        System.out.println();
    }

    public void success() {
        System.out.println("Success!");
    }

    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
    }
}

