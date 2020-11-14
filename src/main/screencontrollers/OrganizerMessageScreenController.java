package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.OrganizerMessageScreen;

import java.util.ArrayList;
import java.util.UUID;

/**
 * OrganizerMessageScreenController is the screen where the organizer can choose who to message.
 *
 * @author Yi Tao Li
 * @version 1.0
 * @since 2020-11-13
 */
public class OrganizerMessageScreenController extends AttendeeMessageScreenController {

    private final OrganizerMessageScreen organizerMessageScreen;

    /**
     * Constructor of OrganizerMessageScreenController
     *
     * @param programController pre-defined ProgramController
     */
    public OrganizerMessageScreenController(ProgramController programController) {
        super(programController);
        this.organizerMessageScreen = new OrganizerMessageScreen(this.usersManager);
    }

    /**
     * Asks OrganizerMessageScreen to display the appropriate prompts, receives input from the organizer, and processes
     * them accordingly to create and send messages.
     */
    public void start() {
        this.organizerMessageScreen.prompt();
        String next = scanner.nextLine();
        while (!next.equals("0")) {
            if (next.equals("all")) {
                messageAll();
            } else {
                ArrayList<String> inputs = new ArrayList<>();
                for (int i = 0; i < next.length(); i += 2) {
                    inputs.add((next.substring(i, i + 1)));
                }
                while (!isValid(inputs)) {
                    this.organizerMessageScreen.prompt2(next);
                    next = this.scanner.nextLine();
                }
                if (next.equals("0")) {
                    break;
                }
                sendMessage(inputs);
            }
            this.organizerMessageScreen.prompt();
            next = this.scanner.nextLine();
        }
        this.programController.setCurrentScreenController(previousScreenController);
        this.end();
    }

    private void sendMessage(ArrayList<String> inputs) {
        this.organizerMessageScreen.messagePrompt();
        String next = scanner.nextLine();
        if (next.equals("0")) {
            return;
        }
        for (String input : inputs) {
            this.messageManager.createMessage(next, users.get(Integer.parseInt(input) - 1));
        }
        this.organizerMessageScreen.successMessage();
    }

    private void messageAll() {
        this.organizerMessageScreen.messagePrompt();
        String next = scanner.nextLine();
        if (next.equals("0")) {
            return;
        }
        for (UUID user : users) {
            this.messageManager.createMessage(next, user);
        }
        this.organizerMessageScreen.successMessage();
    }

    private boolean isValid(ArrayList<String> inputs) {
        for (String input : inputs) {
            if (!isNumeric(input)) {
                return false;
            }
            if (!(0 < Integer.parseInt(input) && Integer.parseInt(input) <= users.size())) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
