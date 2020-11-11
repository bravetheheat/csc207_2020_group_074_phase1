package main.presenters;

/**
 * The UserController is a presenter class that displays attendee options.
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
        System.out.println("Choose from the following options:");
        System.out.println("All events");
        System.out.println("Registered events");
        System.out.println("Contacts");
        System.out.println("Messages");
    }

    /**
     * Temporary method for not choosing one of the options.
     */
    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}
