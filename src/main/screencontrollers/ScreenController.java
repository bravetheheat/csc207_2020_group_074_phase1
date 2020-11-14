package main.screencontrollers;

import main.controllers.ProgramController;

import java.util.Scanner;

public abstract class ScreenController {

    protected Scanner scanner = new Scanner(System.in);
    public ProgramController programController;
    protected ScreenController previousScreenController;

    public ScreenController(ProgramController programController) {
        this.programController = programController;
        this.previousScreenController = programController.getCurrentScreenController();
    }

    abstract public void start();

    protected void end() {
        this.programController.nextScreenController();
    }

}
