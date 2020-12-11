package main.gui_interface;

import main.gui.ListOfUsersUI;
import main.gui.OrganizerMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.GetUserListButtonListener;
import main.guilisteners.UserManagementButtonListener;


import java.util.ArrayList;

public interface IUserManagementUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addUserManagementButtonListener(UserManagementButtonListener listener);
    void addGetUserListButtonListener(GetUserListButtonListener listener);
    void notifyListenerOnBackButtonClicked();
    OrganizerMainUI goToOrganizerMainUI();
    String getUserType();
    String getUserName();
    String getPwd();
    void registerNewUserSuccessful();
    void registerNewUserError();
    ListOfUsersUI goToListOfUsersUI(ArrayList<String> listOfUserInfo);
}

