package main.presenters;

import main.controllers.ProgramController;

public class MainScreen extends Screen{

    public MainScreen(ProgramController programController) {
        super(programController);
    }

    public void start() {
        System.out.println("Welcome to Program X");
        this.screenChooser();
        this.end();

    };

    private void screenChooser() {

        System.out.println("Please choose from one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. User Management");
        String choice = this.scanner.nextLine();
        Screen newScreen;
        switch (choice) {
            case "1":
                newScreen = new RegisterScreen(this.programController);
                break;
            case "2":
                newScreen = new UserControlScreen(this.programController);
                break;

            default:
                System.out.println("Sorry, your choice was invalid.");
                System.out.println();
                this.screenChooser();
                return;
        }
        this.programController.setScreen(newScreen);

    }

    public void end() {
        this.programController.nextScreen();
    }

}
