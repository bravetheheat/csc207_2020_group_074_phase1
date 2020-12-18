package main.screencontrollers;
import main.controllers.ProgramController;
import main.presenters.AdminUserScreen;
import main.presenters.OrganizerScreen;

import java.util.Arrays;
import java.util.List;

/**
 * The AdminUserScreenController is a controller class that tells AdminUserScreen what to display, receives
 * input from ControllerScreen, and tells the ProgramController what screen to go next.
 *
 * @author Leyi Wang
 * @version 2.0
 */
public class AdminUserScreenController extends ScreenController{

    private AdminUserScreen adminUserScreen;
    private List<String> prompts;
    private String userType;

    public AdminUserScreenController(ProgramController programController, String userType) {
        super(programController);
        this.adminUserScreen = new AdminUserScreen();
        String[] options = {"0", "1", "2", "3", "4", "5", "6"};
        this.prompts = (Arrays.asList(options));
        this.userType = userType;
    }

    public void start() {
        this.adminUserScreen.printScreenName();
        this.run();
        this.end();
    }

    /**
     * Checks valid input from Organizer and tells ProgramController what screen to go next.
     */
    public void run() {
        this.adminUserScreen.prompt();
        String next = this.scanner.nextLine();
        while (!this.prompts.contains(next)){
            this.adminUserScreen.prompt2(next);
            this.adminUserScreen.prompt();
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
                nextScreenController = new EventSignUpScreenController(this.programController);
                break;
            case "3":
                nextScreenController = new AttendeeMessageScreenController(this.programController);
                break;
            case "4":
                nextScreenController = new InboxScreenController(this.programController);
                break;
            case "5":
                nextScreenController  = new GatewayScreenController(this.programController);
                break;
            case "6":
                nextScreenController = new ExportEventScreenController(this.programController);
                break;
            default:
                nextScreenController = new AnonymousScreenController(this.programController);

        }
        this.programController.setNewScreenController(nextScreenController);
    }


}
