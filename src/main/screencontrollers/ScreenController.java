package main.screencontrollers;

import main.controllers.ProgramController;

import java.util.Scanner;

/**
 * The ScreenController is an abstract class for all controllers that interact with a presenter class.
 */
public abstract class ScreenController {

    public ProgramController programController;
    protected Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for a ScreenController.
     *
     * @param programController Pre-defined ProgramController.
     */
    public ScreenController(ProgramController programController) {
        this.programController = programController;
    }

    /**
     * Abstract method for starting a Screen.
     */
    abstract public void start();

    /**
     * Ends the screen by calling the programController to run the next ScreenController.
     */
    protected void end() {
        this.programController.nextScreenController();
    }

    protected void goToPreviousScreenController() {
        this.programController.goToPreviousScreenController();

    }

}
