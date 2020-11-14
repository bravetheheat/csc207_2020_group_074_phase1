package main.screencontrollers;

import main.controllers.ProgramController;
import main.entities.User;
import main.presenters.UserManagementScreen;
import main.usecases.UsersManager;
import main.controllers.OrganizerController;

import java.util.UUID;

public class UserManagementScreenController extends ScreenController{

    OrganizerController organizerController;
    UserManagementScreen presenter = new UserManagementScreen();
    UsersManager usersManager = new UsersManager();

    public UserManagementScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
    }

    @Override
    public void start() {
        this.presenter.printScreenName();
        this.userManagement();
        ScreenController nextScreenController = new OrganizerScreenController(this.programController);
        this.programController.setCurrentScreenController(nextScreenController);
        this.end();
    }

    public void userManagement(){
        presenter.promptCommand();
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                if(createSpeaker()) {this.presenter.printValidAdding();}
                else{this.presenter.printInvalidAdding();}
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

    public boolean createSpeaker(){
        this.presenter.promptCreateSpeaker();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        return this.organizerController.createSpeaker(username,password);
    }


}
