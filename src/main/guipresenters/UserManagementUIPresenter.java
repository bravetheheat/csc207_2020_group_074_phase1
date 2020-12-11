package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IUserManagementUI;

public class UserManagementUIPresenter {
    ProgramController programController;
    IUserManagementUI iUserManagementUI;

    public UserManagementUIPresenter(IUserManagementUI iUserManagementUI, ProgramController programController) {
        this.programController = programController;
        this.iUserManagementUI = iUserManagementUI;
    }
}
