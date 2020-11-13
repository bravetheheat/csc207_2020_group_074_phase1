package main.screencontrollers;
import main.controllers.ProgramController;
import main.presenters.OrganizerScreen;

import java.util.Arrays;
import java.util.List;

/**
 * The OrganizerScreenController is a controller class that tells ControllerScreen what to display, receives
 * input from ControllerScreen, and tells the ProgramController what screen to go next.
 *
 * @author Ruoming Ren
 * @version 1.0
 * @since 2020-11-11
 */
public class OrganizerScreenController extends ScreenController{

    private OrganizerScreen organizerScreen;
    private List<String> prompts;

    public OrganizerScreenController(ProgramController programController) {
        super(programController);
        this.organizerScreen = new OrganizerScreen();
        String[] options = {"1", "2", "3", "4"};
        this.prompts = (Arrays.asList(options));
    }

    public void start() {
        this.organizerScreen.printScreenName();
        this.run();
        this.end();
    }

    /**
     * Checks valid input from Organizer and tells ProgramController what screen to go next.
     */
    public void run() {
        this.organizerScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)){
            this.organizerScreen.prompt2(next);
            this.organizerScreen.prompt();
            next = this.scanner.nextLine();
        }
        // for avoiding error which is nextScreenController may not be initialize
        ScreenController nextScreenController = new AnonymousScreenController(this.programController);
        switch (next) {
            case "1": // for holder
                nextScreenController = new AnonymousScreenController(this.programController);
            case "2":
                nextScreenController = new EventsManagementScreenController(this.programController);
            case "3":
                nextScreenController = new EventSignUpScreenController(this.programController);
            case "4":
                nextScreenController = new OrganizerMessageScreen(this.programController);
        }
        this.programController.setCurrentScreenController(nextScreenController);
    }


}
