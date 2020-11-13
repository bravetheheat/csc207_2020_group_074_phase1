package main.presenters;

import main.controllers.EventController;

/**
 * The EventSignUpScreen handles the sign up screen for a User(Attendee)
 *
 * @author Zewen Ma
 * @version 1.0
 * @since 2020-11-11
 */
public class EventSignUpScreen {

    public void printscreenMessage(){
        System.out.println("Sign up or Cancel for an event");
    }

    public void promptCommand(){
        System.out.println("Please choose from the following actions: ");
        System.out.println("1. Sign up for an event");
        System.out.println("2. Cancel an event");
        System.out.println("3. Exit");
    }

    public void promptEventid(){
        System.out.println("Please enter the id of the event: ");
    }

    public void promptUserid(){
        System.out.println("Please enter your userid: ");
    }

    public void printErrorMessage(){
        System.out.println("Sorry, your input is invalid. Please try again.");
    }

    public void printSuccessMessage(){
        System.out.println("Your command is completed.");
    }
}
