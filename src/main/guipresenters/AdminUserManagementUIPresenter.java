package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.IAdminMainUI;
import main.gui_interface.IAdminUserManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteUserButtonListener;

public class AdminUserManagementUIPresenter extends UserManagementUIPresenter
        implements BackButtonListener, DeleteUserButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private IAdminUserManagementUI iAdminUserManagementUI;

    public AdminUserManagementUIPresenter(IAdminUserManagementUI adminUserManagementUI, ProgramController programController) {
        super(adminUserManagementUI, programController);
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        this.iAdminUserManagementUI = adminUserManagementUI;
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
            iAdminUserManagementUI.deleteUserSuccessful();
        }
        else {
            iAdminUserManagementUI.deleteUserError();
        }
    }


}
