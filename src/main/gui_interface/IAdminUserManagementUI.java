package main.gui_interface;

import main.gui.AdminMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteUserButtonListener;

public interface IAdminUserManagementUI extends IUserManagementUI {
    void addBackButtonListener(BackButtonListener listener);
    void addDeleteUserButtonListener(DeleteUserButtonListener listener);
    String getDeletedUser();
    void deleteUserSuccessful();
    void deleteUserError();

    AdminMainUI goToAdminMainUI();
}
