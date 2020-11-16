package main.presenters;

import java.util.ArrayList;

/**
 * The SpeakerScreen displays the interface that a Speaker will see.
 *
 * @author Yile Xie
 * @version 1.0
 * @since 2020-11-12
 */
public class SpeakerScreen {
    /**
     * Empty constructor of an instance of SpeakerScreen.
     */
    public SpeakerScreen() {
    }

    public void welcomeMessage(){
        System.out.println("Welcome, our speakers!");
    }

    /**
     * Prints out the available options for a speaker.
     */
    public void prompt() {
        System.out.println("Choose one of the following options by entering a number:");
        System.out.println("0. Return");
        System.out.println("1. List of talks you are giving");
        System.out.println("2. Messages");

    }

    /**
     * Print a list of talks that this speaker is giving in the conference.
     * list of String of talks(events) that the speaker is giving
     */
    public void talkList(ArrayList<String> talks) {
        System.out.println("You will be giving the following talks:" + "\n");
        for (String talk : talks) {
            System.out.println(talk);
            System.out.println("\n");
        }
    }

    /**
     * Prints out the error message if the collected input is not one of the options.
     */
    public void errorMessage() {
        System.out.println("Sorry, the input is invalid. Please re-enter a valid input.");
    }
}
