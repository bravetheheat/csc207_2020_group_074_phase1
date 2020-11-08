package main.presenters;

import main.controllers.ProgramController;
import main.usecases.UsersManager;

public class UserControlScreen extends Screen {

    UsersManager usersManager;


    public UserControlScreen(ProgramController programController){
        super(programController);
        this.usersManager = programController.getUsersManager();
    }

    public void start(){

        this.listUsers();
        this.end();

    }

    public void end() {
        this.returnToMain();
        super.end();
    }

    private void listUsers() {
        System.out.println("Current list of users:");
        System.out.println(this.usersManager.toString());

    }
}
