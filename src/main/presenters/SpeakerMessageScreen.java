package main.presenters;

import java.util.List;

/**
 * A SpeakerMessageScreen display the information a speaker would need for messaging other users
 */
public class SpeakerMessageScreen {

    /**
     * Print out the screen name
     */
    public void printScreenName() {
        System.out.println("Speaker Message Screen");
    }

    /**
     * Print out the options that a speaker has
     */
    public void prompt() {
        System.out.println("Input 0 if you want to quit");
        System.out.println("Input 1 if you want to reply to a user");
        System.out.println("Input 2 if you want to broadcast");
        System.out.println("Input 3 if you want to read messages you received");
    }

    /**
     * Print out the error message if the user input is invalid
     */
    public void invalidInput(String input) {
        System.out.println(input + " was not one of the options.");
        System.out.println("Please input a new option.");
    }

    /**
     * Prompt the speaker to choose an event to broadcast
     */
    public void broadCastOptions(List<String> events) {
        System.out.println("Which event would you like to choose");
        int count = 1;
        for (String event : events) {
            System.out.println(count + ". " + event);
            count++;
        }
    }

    /**
     * Prompt the speaker for the message they wish to send
     */
    public void whatMessage() {
        System.out.println("please input the message you want to send below");
        System.out.println("the message should be in one line");
    }

    /**
     * Prompts the speaker to select the attendees they want to reply
     */
    public void replyOptions(List<String> users) {
        if(users.size() == 0){
            System.out.println("There is no attendees in your events have messaged you.");
            System.out.println("You are not able to message anyone.");
        }else{
            System.out.println("Who would you like to reply");
            int count = 1;
            for (String user : users) {
                System.out.println(count + ". " + user);
                count++;
            }
        }

    }

    /**
     * Prompt the speaker to enter the number of events they want to broadcast
     */
    public void numOfEvents() {
        System.out.println("How many events would you like to broadcast to");
    }

    /**
     * Print out the message if the action is successful
     */
    public void congratulations() {
        System.out.println("Successful");
        System.out.println();
    }

    /**
     * Print out the message if the speaker is not talking at any event
     */
    public void noEvent(){
        System.out.println("You have no event right now");
        System.out.println("You could not broadcast to anyone.");
    }

    /**
     * Print out the message if the speaker chooses to return to the Speaker Screen
     */
    public void willBeReturned(){
        System.out.println("You will be returned back to speaker screen");
    }

}
