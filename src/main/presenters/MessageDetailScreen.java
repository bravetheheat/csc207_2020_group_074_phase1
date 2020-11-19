package main.presenters;
/**
 * The MessageDetailScreen displays the detail of a message that the logged in user receives
 *
 * @author Yile Xie
 */
public class MessageDetailScreen {
    /**
     * No parameter is required to instantiate a MessageDetailScreen
     */
    public MessageDetailScreen() {}

    /**
     * Print out detail of the message including the text, the sender, and the time sent
     * @param detail
     */
    public void printDetails(String detail){
        System.out.println("Message");
        System.out.println(detail);
        System.out.println();
    }

    /**
     * Print out instruction for returning to the Inbox Screen
     */
    public void returnPrompt(){
        System.out.println("If you wish to return to the Inbox Screen, enter 0");
    }

    /**
     * Print out error message for invalid input
     */
    public void errorMessage(){
        System.out.println("Your input is not valid, please enter again.");
    }
}
