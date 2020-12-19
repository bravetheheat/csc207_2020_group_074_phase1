package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.IAdminMainUI;
import main.gui_interface.IAdminUserManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteUserButtonListener;

import java.util.ArrayList;

public class AdminUserManagementUIPresenter extends UserManagementUIPresenter
        implements BackButtonListener, DeleteUserButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private IAdminUserManagementUI iAdminUserManagementUI;

    public AdminUserManagementUIPresenter(IAdminUserManagementUI adminUserManagementUI, ProgramController programController) {
//        super(adminUserManagementUI, programController);
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        this.iAdminUserManagementUI = adminUserManagementUI;
        iAdminUserManagementUI.addBackButtonListener(this);
        iAdminUserManagementUI.addGetUserListButtonListener(this);
        iAdminUserManagementUI.addUserManagementButtonListener(this);
        iAdminUserManagementUI.addDeleteUserButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        IAdminMainUI iAdminMainUI = this.iAdminUserManagementUI.goToAdminMainUI();
        new AdminMainUIPresenter(iAdminMainUI, this.programController);
    }

    @Override
    public void onDeleteUserButtonClicked() {
        String userToBeDeleted = iAdminUserManagementUI.getDeletedUser();
        if (userToBeDeleted.equals("")) {
            iAdminUserManagementUI.deleteUserError();
            return;
        }
        if (programController.getUsersManager().removeUserbyUsername(userToBeDeleted)) {
            programController.saveForNext();
            iAdminUserManagementUI.deleteUserSuccessful();
        }
        else {
            iAdminUserManagementUI.deleteUserError();
        }
    }

    @Override
    public void onUserManagementButtonClicked() {
        String userType = iAdminUserManagementUI.getUserType();
        String username = iAdminUserManagementUI.getUserName();
        String password = iAdminUserManagementUI.getPwd();

        if (userType.equals("") || username.equals("") || password.equals("")) {
            iAdminUserManagementUI.registerNewUserError();
        }
        else if (!userType.equalsIgnoreCase("Attendee") &&
                !userType.equalsIgnoreCase("Organizer") &&
                !userType.equalsIgnoreCase("Speaker") &&
                !userType.equalsIgnoreCase("Admin")) {
            iAdminUserManagementUI.registerNewUserError();
        }
        else {
            boolean success = this.authController.registerUser(
                    username, password, userType);
            if (!success) {
                iAdminUserManagementUI.registerNewUserError();
            } else {
                programController.saveForNext();
                iAdminUserManagementUI.registerNewUserSuccessful();
            }
        }
    }

    @Override
    public void onGetUserListButtonClicked() {
        ArrayList<String> listOfUserInfo = this.programController.
                getUsersManager().getUserInfoFormatted();
        iListOfUsersUI = iAdminUserManagementUI.goToListOfUsersUI(listOfUserInfo);
        new ListOfUserUIPresenter(iListOfUsersUI, programController);
    }
}


