package main.presenters;

/**
 * Command line interface of AnonymousScreenController
 *
 * @author David Zhao
 */
public class AnonymousScreen {

    /**
     * Base no-arg constructor
     */
    public AnonymousScreen() {
    }

    /**
     * Prints a welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome to Program X!");
    }

    /**
     * Prints a list of options
     */
    public void printOptions() {
        System.out.println("Please choose from the following screens:");
        System.out.println("0. Exit");
        System.out.println("1. Login Screen");
        System.out.println("2. Registration");

    }

    /**
     * Prints an invalid input error message
     */
    public void printInvalidInputErrorMessage() {
        System.out.println("Sorry, your input was invalid. Please try again.");
    }



}
