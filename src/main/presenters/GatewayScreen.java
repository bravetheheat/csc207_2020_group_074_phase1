package main.presenters;

public class GatewayScreen {

    public void welcomeMessage() {
        System.out.println("Gateway Screen");
        System.out.println();
    }

    public void optionsPrompt() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("0. Return to previous screen.");
        System.out.println("1. Save users");
    }

    public void saveUsers() {
        System.out.println("Saving users...");
        System.out.println();
    }

    public void success() {
        System.out.println("Success!");
    }

    public void invalidOption() {
        System.out.println("Invalid option. Please try again.");
    }
}

