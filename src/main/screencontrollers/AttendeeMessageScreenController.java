package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AttendeeMessageScreen;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    protected List<UUID> users;
    protected UsersManager usersManager;
    protected MessageManager messageManager;

    /**
     * Constructor of AttendeeMessageScreenController
     *
     * @param programController pre-defined ProgramController
     */
    public AttendeeMessageScreenController(ProgramController programController) {
        super(programController);
        this.usersManager = programController.getUsersManager();
        this.messageManager = programController.getMessageManager();
        this.users = this.usersManager.getAllUsers();
        this.prompts = new ArrayList<>();
        for (int i = 0; i <= users.size(); i++) {
            this.prompts.add("" + i);
        }
        this.attendeeMessageScreen = new AttendeeMessageScreen(this.usersManager);
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
        this.programController.setCurrentScreenController(previousScreenController);
        this.end();
    }

    private void sendMessage(Integer usersIndex) {
        this.attendeeMessageScreen.messagePrompt();
        String next = scanner.nextLine();
        if (next.equals("0")) {
            return;
        }
        this.messageManager.createMessage(next, users.get(usersIndex));
        this.attendeeMessageScreen.successMessage();
    }
}
