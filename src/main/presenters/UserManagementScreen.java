package main.presenters;

import main.controllers.ProgramController;

public class UserManagementScreen{

    public void printScreenName() {
        System.out.println("UserManagement Screen");
        System.out.println();
    }

    public void promptCommand(){
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("1. Add user");
        System.out.println("2. Remove user");
        System.out.println("3. Display user list");
        System.out.println("4. Exit the screen");
    }
    public void promptAddUser(){
        System.out.println("You are adding a user, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter user name: ");
        System.out.println("Line 2 -- Enter user password: ");
        System.out.println("Line 3 -- Enter user type: ");
    }

    public void promptDeleteUser(){
        System.out.println("You are deleting a user, please enter the following information line by line:");
        System.out.println("Enter user id: ");
    }

    public void listUser(String userList) {
        System.out.println(userList);
    }

    public void printInvalidInput() { System.out.println("Invalid input. Try again.");}

    public void printValidAdding(){ System.out.println("Successfully added user!"); }

    public void printInvalidAdding(){
        System.out.println("Fail to add user! Please try again.");
    }

    public void printValidRemove(){
        System.out.println("Successfully removed user!");
    }

    public void printInvalidRemove(){
        System.out.println("Fail to remove user! Please try again.");
    }

}
