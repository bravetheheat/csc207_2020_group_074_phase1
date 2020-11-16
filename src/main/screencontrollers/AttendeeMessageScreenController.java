package main.screencontrollers;

import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.presenters.AttendeeMessageScreen;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;

/**
 * AttendeeMessageScreenController is the controller in charge of input and output of the AttendeeMessageScreen.
 *
 * @author Yi Tao Li
 * @version 1.1
 * @since 11/13/2020
 */
public class AttendeeMessageScreenController extends ScreenController {

    protected AttendeeMessageScreen attendeeMessageScreen;
    protected List<String> prompts;
    protected List<String> recipients;
    protected UsersManager usersManager;
    protected MessageController messageController;
    protected String loggedInUser;

    /**
     * Constructor of AttendeeMessageScreenController
     *
     * @param programController pre-defined ProgramController
     */
    public AttendeeMessageScreenController(ProgramController programController) {
        super(programController);
        this.loggedInUser = this.programController.getAuthController().fetchLoggedInUser();
        this.usersManager = programController.getUsersManager();
        this.messageController = programController.getMessageController();
        this.recipients = this.messageController.receiversForAttendeeAndOrganizer(loggedInUser);
        this.prompts = new ArrayList<>();
        for (int i = 0; i <= recipients.size(); i++) {
            this.prompts.add("" + i);
        }
        this.attendeeMessageScreen = new AttendeeMessageScreen(usersManager,
                this.messageController.receiversForAttendeeAndOrganizer(loggedInUser));
    }

    /**
     * Asks attendeeMessageScreen to display the appropriate prompts, receives input from the attendee, and processes
     * them accordingly to create and send messages.
     */
    public void start() {
        this.attendeeMessageScreen.prompt();
        String next = scanner.nextLine();
        while (!next.equals("0")) {
            while (!this.prompts.contains(next)) {
                this.attendeeMessageScreen.prompt2(next);
                next = this.scanner.nextLine();
            }
            if (next.equals("0")) {
                break;
            }
            sendMessage(Integer.parseInt(next) - 1);
            this.attendeeMessageScreen.prompt();
            next = this.scanner.nextLine();
        }
        this.goToPreviousScreenController();
        this.end();
    }

    private void sendMessage(Integer usersIndex) {
        this.attendeeMessageScreen.messagePrompt();
        String next = scanner.nextLine();
        if (next.equals("0")) {
            return;
        }
        this.messageController.sendMessage(loggedInUser, recipients.get(usersIndex), next);
        this.attendeeMessageScreen.successMessage();
    }
}
