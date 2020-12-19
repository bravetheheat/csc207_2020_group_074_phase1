package main.gui_interface;

import main.gui.AdminUserManagementUI;
import main.gui.UserManagementUI;
import main.guilisteners.BackButtonListener;

public interface IListOfUsersUI {
    void notifyListenerOnBackButtonClicked();
    UserManagementUI goToUserManagementUI();
    void addBackButtonListener(BackButtonListener listener);
    AdminUserManagementUI goToAdminUserManagementUI();
}

