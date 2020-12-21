package main.gui_interface;

import main.gui.ListOfUsersUI;
import main.gui.OrganizerMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.GetUserListButtonListener;
import main.guilisteners.UserManagementButtonListener;


import java.util.ArrayList;

/**
 * The <code>UserManagementUIPresenter</code> will depend on this interface which is
 * implemented by <code>UserManagementUI</code>
 *
 * @author Ruoming Ren
 */
public interface IUserManagementUI extends IView {

    /**
     * Listens to actions performed on the back button and returns to main screen
     *
     * @param listener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener listener);

    /**
     * Listens to actions performed on the user management button switches screen
     *
     * @param listener the object that implements <code>UserManagementButtonListener</code>
     */
    void addUserManagementButtonListener(UserManagementButtonListener listener);

    /**
     * Listens to actions performed on the get user list button switches screen
     *
     * @param listener the object that implements <code>GetUserListButtonListener</code>
     */
    void addGetUserListButtonListener(GetUserListButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnBackButtonClicked();

    /**
     * Open up <code>OrganizerMainUI</code> frame
     * @return a <code>OrganizerMainUI</code>
     */
    OrganizerMainUI goToOrganizerMainUI();

    /**
     * Retrieves the user's type
     * @return the user's type
     */
    String getUserType();

    /**
     * Retrieves the user's username
     * @return the user's username
     */
    String getUserName();

    /**
     * Retrieves the user's password
     * @return the user's password
     */
    String getPwd();

    /**
     * Displays a success notification
     */
    void registerNewUserSuccessful();

    /**
     * Displays an error notification
     */
    void registerNewUserError();

    /**
     * Open up <code>ListOfUsersUI</code> frame
     * @return a <code>ListOfUsersUI</code>
     */
    ListOfUsersUI goToListOfUsersUI(ArrayList<String> listOfUserInfo);
}

