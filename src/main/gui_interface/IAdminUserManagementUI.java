package main.gui_interface;

import main.gui.AdminMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteUserButtonListener;

/**
 * The <code>AdminUserManagementUIPresenter</code> will depend on this interface which is
 * implemented by <code>AdminUserManagementUI</code>
 *
 * @author Ruoming Ren
 */
public interface IAdminUserManagementUI extends IUserManagementUI {

    /**
     * Listens to actions performed on the back button and returns to main screen
     *
     * @param listener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener listener);


    /**
     * Listens to actions performed on the back button and deletes the user
     *
     * @param listener the object that implements <code>DeleteUserButtonListener</code>
     */
    void addDeleteUserButtonListener(DeleteUserButtonListener listener);

    /**
     * Retrieves the deleted user
     * @return deleted user
     */
    String getDeletedUser();

    /**
     * Displays a successfully deleted message
     */
    void deleteUserSuccessful();

    /**
     * Displays an error message
     */
    void deleteUserError();

    /**
     * Open up <code>AdminMainUI</code> frame
     * @return a <code>AdminMainUI</code>
     */
    AdminMainUI goToAdminMainUI();
}
