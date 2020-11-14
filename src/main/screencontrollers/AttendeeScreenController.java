package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AttendeeScreen;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 * The AttendeeScreenController is a controller class that tells AttendeeScreen what to display, receives input from
 * AttendeeScreen, and tells the ProgramController what screen to go next.
 *
 * @author Yi Tao Li
 * @version 1.1
 * @since 2020-11-11
 */
public class AttendeeScreenController extends ScreenController {

    private AttendeeScreen attendeeScreen;
    private List<String> prompts;

    /**
     * Constructor of an AttendeeScreenController.
     */
    public AttendeeScreenController(ProgramController programController) {
        super(programController);
        this.attendeeScreen = new AttendeeScreen();
        String[] options = {"1", "2", "3", "4"};
        this.prompts = (Arrays.asList(options));
    }

    /**
     * Asks attendeeScreen to display the options, receives user input, and sets the next screen for programController
     * to call.
     */
    public void start() {
        this.attendeeScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)) {
            this.attendeeScreen.prompt2(next);
            this.attendeeScreen.prompt();
            next = this.scanner.nextLine();
        }
        switch (next) {
            case "0":
                this.programController.setCurrentScreenController(this.previousScreenController);
                // placeholders for screencontrollers
            case "1":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            case "2":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            case "3":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            case "4":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
        }
        this.end();
    }
}
