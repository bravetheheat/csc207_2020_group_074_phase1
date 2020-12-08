package main.screencontrollers;
import main.controllers.ProgramController;
import main.presenters.UserScreen;

import java.util.Arrays;
import java.util.List;
/**
 * The OrganizerScreenController is a controller class that tells ControllerScreen what to display, receives
 * input from ControllerScreen, and tells the ProgramController what screen to go next.
 *
 * @author Ruoming Ren
 * @version 2.0
 * @since 2020-11-11
 */
public class UserScreenController extends ScreenController{

    private UserScreen userScreen;
    private List<String> prompts;
    private String userType;

    public UserScreenController(ProgramController programController, String userType) {
        super(programController);
        this.userScreen = new UserScreen();
        String[] options = {"0", "1", "2", "3", "4", "5", "6"};
        this.prompts = (Arrays.asList(options));
        this.userType = userType;
    }

    public void start() {
        this.userScreen.printScreenName();
        this.run();
        this.end();
    }

    /**
     * Checks valid input from Organizer and tells ProgramController what screen to go next.
     */
    public void run() {
        this.userScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)){
            this.userScreen.prompt2(next);
            this.userScreen.prompt();
            next = this.scanner.nextLine();
        }
        // for avoiding error which is nextScreenController may not be initialize
        ScreenController nextScreenController;
        switch (next) {
            case "0":
                this.programController.getAuthController().logout();
                return;
            case "1":
                nextScreenController = new UserManagementScreenController(this.programController, userType);
                break;
            case "2":
                nextScreenController = new EventsManagementScreenController(this.programController);
                break;
            case "3":
                nextScreenController = new EventSignUpScreenController(this.programController);
                break;
            case "4":
                nextScreenController = new OrganizerMessageScreenController(this.programController);
                break;
            case "5":
                nextScreenController = new InboxScreenController(this.programController);
                break;
            case "6":
                nextScreenController  = new GatewayScreenController(this.programController);
                break;
            default:
                nextScreenController = new AnonymousScreenController(this.programController);

        }
        this.programController.setNewScreenController(nextScreenController);
    }


}
