package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.IListOfUsersUI;
import main.gui_interface.IOrganizerMainUI;
import main.gui_interface.IUserManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.GetUserListButtonListener;
import main.guilisteners.UserManagementButtonListener;

import java.util.ArrayList;

/**
 * the file contains the presenter for the users management screen
 */
public class UserManagementUIPresenter implements UserManagementButtonListener,
        BackButtonListener, GetUserListButtonListener {

    ProgramController programController;
    AuthController authController;
    IUserManagementUI iUserManagementUI;
    IOrganizerMainUI iOrganizerMainUI;
    IListOfUsersUI iListOfUsersUI;

    public UserManagementUIPresenter() {

    }

    public UserManagementUIPresenter(IUserManagementUI userManagementUI,
                                     ProgramController programController) {
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        this.iUserManagementUI = userManagementUI;
        iUserManagementUI.addBackButtonListener(this);
        iUserManagementUI.addUserManagementButtonListener(this);
        iUserManagementUI.addGetUserListButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iOrganizerMainUI = iUserManagementUI.goToOrganizerMainUI();
        new OrganizerMainUIPresenter(iOrganizerMainUI, programController);
    }

    @Override
    public void onUserManagementButtonClicked() {
        String userType = iUserManagementUI.getUserType();
        String username = iUserManagementUI.getUserName();
        String password = iUserManagementUI.getPwd();

        if (userType.equals("") || username.equals("") || password.equals("")) {
            iUserManagementUI.registerNewUserError();
        }
        else if (!userType.equals("Attendee") && !userType.equals("Organizer") &&
                !userType.equals("Speaker")) {
            iUserManagementUI.registerNewUserError();
        }
        else {
            boolean success = this.authController.registerUser(
                    username, password, userType);
            if (!success) {
                iUserManagementUI.registerNewUserError();
            } else {
                programController.saveForNext();
                iUserManagementUI.registerNewUserSuccessful();
            }
        }
    }

    @Override
    public void onGetUserListButtonClicked() {
        ArrayList<String> listOfUserInfo = this.programController.
                getUsersManager().getUserInfoFormatted();
        iListOfUsersUI = iUserManagementUI.goToListOfUsersUI(listOfUserInfo);
        new ListOfUserUIPresenter(iListOfUsersUI, programController);
    }
}
