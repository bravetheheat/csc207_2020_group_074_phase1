package main.screencontrollers;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.presenters.UserManagementScreen;
import main.usecases.UsersManager;

/**
 * The UserManagementScreen handles adding speaker and displaying the user list of the program
 *
 * @author Leyi Wang
 * @version 1.0
 */
public class UserManagementScreenController extends ScreenController {

    OrganizerController organizerController;
    UserManagementScreen presenter = new UserManagementScreen();
    UsersManager usersManager;

    /**
     * Constructor for UserManagementScreenController
     *
     * @param programController instance of ProgramController
     */
    public UserManagementScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        usersManager = programController.getUsersManager();
    }

    /**
     * Start and exit the UserManagement Screen to organizer screen base on input
     */
    @Override
    public void start() {
        this.presenter.printScreenName();
        this.userManagement();
        ScreenController nextScreenController = new OrganizerScreenController(this.programController);
        this.programController.setNewScreenController(nextScreenController);
        this.end();
    }

    /**
     * Managing user and displaying user list base on input command,
     * print the success or failure this command.
     */
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

    /**
     * Creating a speaker user based on input command.
     */
    public boolean createSpeaker() {
        this.presenter.promptCreateSpeaker();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        return this.organizerController.createSpeaker(username, password);
    }


}
