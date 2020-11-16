package main.presenters;

/**
 * The EventSignUpScreen handles the sign up screen for a User(Attendee)
 *
 * @author Zewen Ma
 * @version 2.0
 * @since 2020-11-11
 */
public class EventSignUpScreen {

    public void printScreenMessage(){
        System.out.println("Sign up or Cancel for an event \n");
    }

    public void promptCommand(){
        System.out.println("Please choose from the following actions(Please enter a number, i.e., 1): ");
        System.out.println("0. Exit");
        System.out.println("1. Sign up for an event");
        System.out.println("2. Cancel an event");
    }

    public void promptEvents(String events){
        System.out.println("You are signing up for an event, the following are the events that you can sign up for:");
        System.out.println(events);
        System.out.println("Enter event index from the above list(Please enter a number, i.e., 1): ");
    }

    public void printErrorMessage(){
        System.out.println("Sorry, your input is invalid. Please try again.");
    }

    public void printSuccessMessage(){
        System.out.println("Your command is completed.");
    }

    public void printFailMessage(){
        System.out.println("You already made this command.");
    }

    public void printNoEventMessage(){
        System.out.println("There's no events you can sign up.");
    }
}
