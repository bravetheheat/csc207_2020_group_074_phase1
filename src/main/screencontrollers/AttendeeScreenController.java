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
public class AttendeeScreenController {

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
     * Checks valid input from attendee and tells ProgramController what screen to go next.
     */
    public String run() {
        this.attendeeScreen.prompt();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!this.prompts.contains(next)){
            this.attendeeScreen.prompt2(next);
            this.attendeeScreen.prompt();
            next = sc.nextLine();
        }
        switch (next) {
            case "1":
                return "all events";
            case "2":
                return "registered events";
            case "3":
                return "contact";
        }
        return "messages";
    }

    @Override
    public void start() {

    }
}
