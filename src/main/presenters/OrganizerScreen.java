package main.presenters;
/**
 * The OrganizerScreen is a presenter class that displays attendee options.
 *
 * @author Ruoming Ren
 * @version 1.0
 * @since 2020-11-11
 */
public class OrganizerScreen {
    /**
     * Constructor of an OrganizerScreen.
     */
    public OrganizerScreen() {

    }

    public void printScreenName(){
        System.out.println("Here is the organizer screen");
    }

    public void prompt() {
        System.out.println("Choose from the following options by entering a number:");
        System.out.println("0. Logout");
        System.out.println("1. User management");
        System.out.println("2. Manage an event or create a room");
        System.out.println("3. Registered events");
        System.out.println("4. Messages");
        System.out.println("5. Inbox");
        System.out.println("6. Data Management");
    }

    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}