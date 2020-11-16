package main.screencontrollers;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.presenters.UserManagementScreen;
import main.usecases.UsersManager;

public class UserManagementScreenController extends ScreenController {

    OrganizerController organizerController;
    UserManagementScreen presenter = new UserManagementScreen();
    UsersManager usersManager;

    public UserManagementScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        usersManager = programController.getUsersManager();
    }

    @Override
    public void start() {
        this.presenter.printScreenName();
        this.userManagement();
        ScreenController nextScreenController = new OrganizerScreenController(this.programController);
        this.programController.setNewScreenController(nextScreenController);
        this.end();
    }

    public void userManagement() {
        presenter.promptCommand();
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                if (createSpeaker()) {
                    this.presenter.printValidAdding();
                } else {
                    this.presenter.printInvalidAdding();
                }
                userManagement();
                break;
            case "2":
                String userList = this.usersManager.toString();
                this.presenter.listUser(userList);
                break;
            case "3":
                break;
            default:
                presenter.printInvalidInput();
                userManagement();
        }
    }

    public boolean createSpeaker() {
        this.presenter.promptCreateSpeaker();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        return this.organizerController.createSpeaker(username, password);
    }


}
