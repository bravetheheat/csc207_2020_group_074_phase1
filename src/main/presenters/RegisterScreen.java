package main.presenters;
import main.controllers.AuthController;
import main.controllers.ProgramController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterScreen extends Screen {

    AuthController authController;

    /**
     * Builder pattern
     */
    String username;
    String password;
    String userType;


    public RegisterScreen(ProgramController programController) {
        super(programController);
        this.authController = programController.getAuthController();

    }

    public void start() {
        this.register();
        this.end();
    }

    private void register() {
        System.out.println("Registration Screen");
        this.createUser();
    }

    private void createUser() {
        System.out.println();
        System.out.println("Please enter the following required fields");
        System.out.println("Enter your username");
        this.username = this.scanner.nextLine();
        System.out.println("Enter your password");
        this.password = this.scanner.nextLine();
        this.queryUserType();
        boolean success = this.authController.registerUser(this.username, this.password,this.userType);
        if (success) {
            System.out.println("Successfully registered!");
            return;
        }
        System.out.println("Sorry, your user couldn't be registered. Please try again");
        this.createUser();
    }

    public void end() {
        this.returnToMain();
        super.end();

    }

    private void queryUserType() {
        System.out.println("Choose from the following user types:");
        System.out.println("1. Speaker");
        System.out.println("2. Attendee");
        System.out.println("3. Organizer");
        String userType = this.scanner.nextLine();
        switch(userType) {
            case "1":
                this.userType = "Speaker";
                return;
            case "2":
                this.userType = "Attendee";
                return;
            case "3":
                this.userType = "Organizer";
                return;
            default:
                System.out.println("Invalid option. Please try again");
                this.queryUserType();
                return;
        }

    }



}
