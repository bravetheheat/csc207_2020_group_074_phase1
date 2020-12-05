package main.presenters;

import java.util.List;

public class SpeakerMessageScreen {


    public void printScreenName() {
        System.out.println("Speaker Message Screen");
    }

    public void promt() {
        System.out.println("Input 1 if you want to reply to a user");
        System.out.println("Input 2 if you want to broadcast");
        System.out.println("Input 3 if you want to read messages you received");
        System.out.println("Input 0 if you want to quit");
    }

    public void invalidInput(String input) {
        System.out.println(input + " was not one of the options.");
        System.out.println("Please input a new option.");
    }

    public void broadCastOptions(List<String> events) {
        System.out.println("Which event would you like to choose");
        int count = 1;
        for (String event : events) {
            System.out.println(count + ". " + event);
            count++;
        }
    }

    public void whatMessage() {
        System.out.println("please input the message you want to send below");
        System.out.println("the message should be in one line");
    }

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

    public void numOfEvents() {
        System.out.println("How many events would you like to broadcast to");
    }

    public void congratulations() {
        System.out.println("Successful");
        System.out.println();
    }

    public void noEvent(){
        System.out.println("You have no event right now");
        System.out.println("You could not broadcast to anyone.");
    }

    public void willBeReturned(){
        System.out.println("You will be returned back to speaker screen");
    }

}
