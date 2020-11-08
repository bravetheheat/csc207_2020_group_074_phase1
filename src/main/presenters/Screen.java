package main.presenters;

import main.controllers.ProgramController;

import java.util.Scanner;

public abstract class Screen {

    public static Scanner scanner = new Scanner(System.in);
    public ProgramController programController;

    public Screen(ProgramController programController) {
        this.programController = programController;

    }

    abstract public void start();

    abstract public void end();

    public void returnToMain() {
        System.out.println("You will now be returned to the main screen.");
        System.out.println();
        this.programController.setScreen(new MainScreen(this.programController));
        this.programController.nextScreen();
    }
}
