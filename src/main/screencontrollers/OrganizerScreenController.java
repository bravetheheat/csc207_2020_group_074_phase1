package main.screencontrollers;

import java.util.Scanner;

/**
 * The OrganizerScreenController is a controller class that tells ControllerScreen what to display, receives
 * input from ControllerScreen, and tells the ProgramController what screen to go next.
 *
 * @author Ruoming Ren
 * @version 1.0
 * @since 2020-11-11
 */
public class OrganizerScreenController {

    private OrganizerScreen organizerScreen;

    public OrganizerScreenController() {
        this.organizerScreen = new OrganizerScreen();
    }

    /**
     * Checks valid input from Organizer and tells ProgramController what screen to go next.
     */
    public String run() {
        this.organizerScreen.prompt();
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


}
