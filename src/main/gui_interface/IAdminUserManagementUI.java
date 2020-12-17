package main.gui_interface;

import main.gui.AdminMainUI;
import main.guilisteners.DeleteUserButtonListener;

public interface IAdminUserManagementUI extends IUserManagementUI {
    void addDeleteUserButtonListener(DeleteUserButtonListener listener);
    String getDeletedUser();
    void deleteUserSuccessful();
    void deleteUserError();

    AdminMainUI goToAdminMainUI();
}
