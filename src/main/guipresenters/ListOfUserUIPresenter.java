package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.IAdminUserManagementUI;
import main.gui_interface.IListOfUsersUI;
import main.gui_interface.IUserManagementUI;
import main.guilisteners.BackButtonListener;

/**
 * the file contains the presenter to show the list of users
 */
@SuppressWarnings("FieldCanBeLocal")

public class ListOfUserUIPresenter implements BackButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private IListOfUsersUI iListOfUsersUI;
    private IUserManagementUI iUserManagementUI;
    private IAdminUserManagementUI iAdminUserManagementUI;

    public ListOfUserUIPresenter(IListOfUsersUI listOfUsersUI,
                                 ProgramController programController) {
        this.iListOfUsersUI = listOfUsersUI;
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        iListOfUsersUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        if (authController.getUserType().equals("Organizer")) {
            iUserManagementUI = iListOfUsersUI.goToUserManagementUI();
            new UserManagementUIPresenter(iUserManagementUI, programController);
        }
        else {
            programController.saveForNext();
            iAdminUserManagementUI = iListOfUsersUI.goToAdminUserManagementUI();
            new AdminUserManagementUIPresenter(iAdminUserManagementUI,
                    programController);
        }
    }

}

