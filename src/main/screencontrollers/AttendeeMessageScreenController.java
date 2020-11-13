package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AttendeeMessageScreen;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttendeeMessageScreenController extends ScreenController{

    private AttendeeMessageScreen attendeeMessageScreen;
    private List<String> prompts;
    private List<UUID> users;
    private UsersManager usersManager;

    public AttendeeMessageScreenController (ProgramController programController) {
        super(programController);
        this.usersManager = programController.getUsersManager();
        this.users = this.usersManager.getAllUsers(); // take list of users from
        this.attendeeMessageScreen = new AttendeeMessageScreen(this.usersManager);
    }

    public void start() {
        this.run();
        this.end();
    }

    public void run() {
        this.attendeeMessageScreen.prompt();
        String next = scanner.nextLine();
        while (!this.prompts.contains(next)) {
            this.attendeeMessageScreen.prompt2(next);
            this.attendeeMessageScreen.prompt();
            next = this.scanner.nextLine();
        }
        switch (next) {
            case "":
                // create new message with to the relevant user
        }
    }
}
