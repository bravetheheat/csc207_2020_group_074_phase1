package main.presenters;

import main.controllers.ProgramController;

public class AnonymousScreen {

    public AnonymousScreen() {
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Program X!");
    }

    public void printOptions() {
        System.out.println("Please choose from the following screens:");
        System.out.println("1. Login Screen");
        System.out.println("2. Registration");
        System.out.println("3. Exit");
    }

    public void printInvalidInputErrorMessage() {
        System.out.println("Sorry, your input was invalid. Please try again.");
    }



}
