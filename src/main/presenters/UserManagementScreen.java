package main.presenters;

public class UserManagementScreen {

    public void printScreenName() {
        System.out.println("UserManagement Screen");
        System.out.println();
    }

    public void promptCommand() {
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("1. Add Speaker");
        System.out.println("2. Display user list");
        System.out.println("3. Exit the screen");
    }

    public void promptCreateSpeaker() {
        System.out.println("You are adding a speaker, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user name: ");
        System.out.println("Line 2 -- Enter user password: ");
    }


    public void listUser(String userList) {
        System.out.println(userList);
    }

    public void printInvalidInput() {
        System.out.println("Invalid input. Try again.");
    }

    public void printValidAdding() {
        System.out.println("Successfully added speaker!");
    }

    public void printInvalidAdding() {
        System.out.println("Fail to add speaker! Please try again.");
    }

}
