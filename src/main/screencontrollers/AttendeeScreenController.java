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
 * @version 1.0
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
    public void start(){
        this.run();
        this.end();
    }

    /**
     * Checks valid input from attendee and tells ProgramController what screen to go next.
     */
    public void run() {
        this.attendeeScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)){
            this.attendeeScreen.prompt2(next);
            this.attendeeScreen.prompt();
            next = this.scanner.nextLine();
        }
        switch (next) {
            // placeholder for all events screen
            case "1":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            // placeholder for registered events screen
            case "2":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            // placeholder for contacts screen
            case "3":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
            // placeholder for message screen
            case "4":
                this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
        }
    }
}
