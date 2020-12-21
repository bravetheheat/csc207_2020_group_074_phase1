package main.presenters;

/**
 * The EventSignUpScreen handles the sign up screen for a User(Attendee)
 *
 * @author Zewen Ma
 * @version 2.0
 * @since 2020-11-11
 */
@Deprecated
public class EventSignUpScreen {

    /**
     * Print the screen name for event management screen
     */
    public void printScreenMessage(){
        System.out.println("Sign up or Cancel for an event \n");
    }

    /**
     * Print the available options and functionalities
     */
    public void promptCommand(){
        System.out.println("Please choose from the following actions(Please enter a number, i.e., 1): ");
        System.out.println("0. Exit");
        System.out.println("1. Sign up for an event");
        System.out.println("2. Cancel an event");
        System.out.println("3. Check the event that already signed up.");
    }

    /**
     * Print the events information for the user to register
     *
     * @param events String representation of all the events
     */
    public void promptSignupEvents(String events){
        System.out.println("You are signing up for an event, the following are the events that you can sign up for:");
        System.out.println(events);
        System.out.println("Enter event index from the above list(Please enter a number, i.e., 1): ");
    }

    /**
     * Print the events information for the user to cancel
     *
     * @param events String representation of the registered events
     */
    public void promptCancelEvents(String events){
        System.out.println("You are cancelling an event, the following are the events that you can cancel:");
        System.out.println(events);
        System.out.println("Enter event index from the above list(Please enter a number, i.e., 1): ");
    }

    /**
     * Print request to ask user to input again due to invalid input
     */
    public void printErrorMessage(){
        System.out.println("Sorry, your input is invalid. Please try again.");
    }

    /**
     * Print verification showing one modification has been done successfully
     */
    public void printSuccessMessage(){
        System.out.println("Your command is completed.");
    }

    /**
     * Print message when there is no input for the current modification
     */
    public void printFailMessage(){
        System.out.println("You cannot make this command.");
    }

    /**
     * Print message when there is no corresponding event for user to input
     */
    public void printNoEventMessage(){
        System.out.println("There's no events here.");
    }

    public void promptEventSchedule(String events){
        System.out.println("Here are the events you signed up: \n");
        System.out.println(events);
    }
}
