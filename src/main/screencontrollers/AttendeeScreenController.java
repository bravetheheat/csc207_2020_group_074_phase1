package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AttendeeScreen;
import java.util.Arrays;
import java.util.List;

/**
 * The AttendeeScreenController is a controller class that tells AttendeeScreen what to display, receives input from
 * AttendeeScreen, and tells the ProgramController what screen to go next.
 *
 * @author Yi Tao Li
 * @version 1.1
 * @since 2020-11-11
 */
@Deprecated
public class AttendeeScreenController extends ScreenController {

    private AttendeeScreen attendeeScreen;
    private List<String> prompts;

    /**
     * Constructor of an AttendeeScreenController.
     */
    public AttendeeScreenController(ProgramController programController) {
        super(programController);
        this.attendeeScreen = new AttendeeScreen();
        String[] options = {"0", "1", "2", "3"};
        this.prompts = (Arrays.asList(options));
    }

    /**
     * Asks attendeeScreen to display the options, receives user input, and sets the next screen for programController
     * to call.
     */
    public void start() {
        this.optionsPrompt();
        this.end();
    }

    /**
     * Prompts the user for a screen option
     */
    public void optionsPrompt() {
        this.attendeeScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)) {
            this.attendeeScreen.prompt2(next);
            this.attendeeScreen.prompt();
            next = this.scanner.nextLine();
        }
        switch (next) {
            case "0":
                this.programController.getAuthController().logout();
                this.end();
                return;
            case "1":
                this.programController.setNewScreenController(new EventSignUpScreenController(this.programController));
                break;
            /*case "2":
                this.programController.setNewScreenController(new EventsManagementScreenController(this.programController));
                break;*/
            case "2":
                this.programController.setNewScreenController(new AttendeeMessageScreenController(this.programController));
                break;
            case "3":
                this.programController.setNewScreenController(new InboxScreenController(this.programController));
                break;
        }
    }
}
