package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IUserManagementUI;

public class UserManagementUIPresenter {
    ProgramController programController;
    IUserManagementUI iUserManagementUI;

    public UserManagementUIPresenter(IUserManagementUI userManagementUI, ProgramController programController) {
        this.programController = programController;
        this.iUserManagementUI = userManagementUI;
    }
}
