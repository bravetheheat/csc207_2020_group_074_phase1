package main.presenters;

public class MessageDetailScreen {
    public MessageDetailScreen() {}

    public void printDetails(String detail){
        System.out.println("Message");
        System.out.println(detail);
        System.out.println();
    }

    public void returnPrompt(){
        System.out.println("If you wish to return to the Inbox Screen, enter 0");
    }

    public void errorMessage(){
        System.out.println("Your input is not valid, please enter again.");
    }
}
