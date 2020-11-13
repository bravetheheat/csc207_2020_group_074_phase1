package main.presenters;

public class OrganizerMessageScreen extends MessageScreen {

    public void messageScreenStart() {
        System.out.println();
        System.out.println("0. Return to main menu.");
        System.out.println("1. Select a chat room.");
        System.out.println("2. Create a new chat room.");
        System.out.println("3. Message all the attendees of an event");
        System.out.println("4. Message all the speakers of an event");
    }
}
