package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.UserManagementScreen;
import main.usecases.UsersManager;

import java.util.UUID;

public class UserManagementScreenController extends ScreenController{

    UsersManager usersManager = new UsersManager();
    UserManagementScreen presenter = new UserManagementScreen();

    public UserManagementScreenController(ProgramController programController) {
        super(programController);
    }

    @Override
    public void start() {
        this.presenter.printScreenName();
        this.userManagement();
        //ScreenController nextScreenController = new OrganizerScreenController(this.programController);
        //this.programController.setCurrentScreenController(nextScreenController);
        this.end();
    }

    public void userManagement(){
        presenter.promptCommand();
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                if(addUser()) {this.presenter.printValidAdding();}
                else{this.presenter.printInvalidAdding();}
                userManagement();
                break;
            case "2":
                if(removeUser()){this.presenter.printValidRemove();}
                else{this.presenter.printInvalidRemove();}
                userManagement();
                break;
            case "3":
                String userList = this.usersManager.toString();
                this.presenter.listUser(userList);
                break;
            case "4":
                break;
            default:
                presenter.printInvalidInput();
                userManagement();
        }
    }

    public boolean addUser(){
        this.presenter.promptAddUser();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        String userType = scanner.nextLine();
        return this.usersManager.addUser(username,password,userType);
    }

    public boolean removeUser(){
        this.presenter.promptDeleteUser();
        String userID = scanner.nextLine();
        return this.usersManager.removeUser(UUID.fromString(userID));
    }

}
