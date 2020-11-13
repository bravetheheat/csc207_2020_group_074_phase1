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
        System.out.println("1. Showing all events");
        System.out.println("2. Manage an event");
        System.out.println("3. Registered events");
        System.out.println("4. Messages");
    }

    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}