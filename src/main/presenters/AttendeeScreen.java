package main.presenters;

/**
 * The AttendeeScreen is a presenter class that displays attendee options.
 *
 * @author Yi Tao Li
 * @version 1.0
 * @since 2020-11-11
 */
public class AttendeeScreen {

    /**
     * Constructor of an AttendeeScreen.
     */
    public AttendeeScreen() {
    }

    /**
     * Displays options for the attendee.
     */
    public void prompt() {
        System.out.println("Choose from the following options by entering a number:");
        System.out.println("1. Register for events");
        System.out.println("2. Manage events");
        System.out.println("3. Message");
        System.out.println("4. Inbox");
        System.out.println("0. Go back");
    }

    /**
     * Lets the attendee know that they entered an invalid input.
     *
     * @param input The invalid input entered by the attendee.
     */
    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}
