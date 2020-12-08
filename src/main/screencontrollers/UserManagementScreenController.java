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
    String userType;

    /**
     * Constructor for UserManagementScreenController
     *
     * @param programController instance of ProgramController
     */
    public UserManagementScreenController(ProgramController programController, String userType) {
        super(programController);
        organizerController = new OrganizerController(programController);
        usersManager = programController.getUsersManager();
        this.userType = userType;
    }

    /**
     * Start and exit the UserManagement Screen to organizer screen base on input
     */
    @Override
    public void start() {
        this.presenter.printScreenName();
        if(this.userType.equals("AdminUser")) {this.userManagementAdminUser();}
        else {this.userManagement();}
        ScreenController nextScreenController = new UserScreenController(this.programController, userType);
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
                if (createUser()) {
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

    public void userManagementAdminUser() {
        presenter.promptCommandAdminUser();
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                if (createUser()) {
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
                if(removeUserbyID())this.presenter.printValidRemoving();
                else this.presenter.printInvalidRemoving();
                break;
            case "4":
                if(removeUserbyName())this.presenter.printValidRemoving();
                else this.presenter.printInvalidRemoving();
                break;
            default:
                presenter.printInvalidInput();
                userManagement();
        }
    }
    /**
     * Creating a user based on input command.
     */
    public boolean createUser() {
        this.presenter.promptCreateUser();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        String userType = scanner.nextLine();
        return this.usersManager.addUser(username, password, userType);
    }

    /**
     * Removing a user based on input command.
     */
    public boolean removeUserbyID(){
        this.presenter.promptRemoveUserByID();
        String id = scanner.nextLine();
        return this.usersManager.removeUserbyID(id);
    }

    /**
     * Removing a user based on input command.
     */
    public boolean removeUserbyName(){
        this.presenter.promptRemoveUserByName();
        String username = scanner.nextLine();
        return this.usersManager.removeUserbyUsername(username);
    }
}
