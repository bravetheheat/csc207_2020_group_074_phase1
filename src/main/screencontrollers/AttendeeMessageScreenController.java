package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AttendeeMessageScreen;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttendeeMessageScreenController extends ScreenController {

    private AttendeeMessageScreen attendeeMessageScreen;
    private List<String> prompts;
    private List<UUID> users;
    private UsersManager usersManager;
    private MessageManager messageManager;

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

    public void start() {
        this.run();
        this.end();
    }

    public void run() {
        this.attendeeMessageScreen.prompt();
        String next = scanner.nextLine();
        while (!next.equals("0")) {
            while (!this.prompts.contains(next)) {
                this.attendeeMessageScreen.prompt2(next);
                next = this.scanner.nextLine();
            }
            sendMessage(Integer.parseInt(next) - 1);
            this.attendeeMessageScreen.prompt();
            next = this.scanner.nextLine();
        }
        this.programController.setCurrentScreenController(previousScreenController);
    }

    public void sendMessage(Integer usersIndex) {
        this.attendeeMessageScreen.messagePrompt();
        String next = scanner.nextLine();
        if (next.equals("0")) {
            return;
        }
        this.messageManager.createMessage(next, users.get(usersIndex));
        this.attendeeMessageScreen.successMessage();
    }
}
