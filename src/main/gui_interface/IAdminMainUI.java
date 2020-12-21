package main.gui_interface;

import main.guilisteners.*;

import java.util.ArrayList;

/**
 * The <code>AdminMainUIPresenter</code> will depend on this interface which is
 * implemented by <code>AdminMainUI</code>
 *
 * @author Yi Tao Li
 */
public interface IAdminMainUI extends IView {

    /**
     * Listens to actions performed on the logout button and logs the user out
     *
     * @param listener the object that implements <code>LogoutButtonListener</code>
     */
    void addLogoutButtonListener(LogoutButtonListener listener);

    /**
     * Listens to actions performed on the user management button and switches screen
     *
     * @param listener the object that implements <code>UserManagementButtonListener</code>
     */
    void addUserManagementButtonListener(UserManagementButtonListener listener);

    /**
     * Listens to actions performed on the event registration button and switches screen
     *
     * @param listener the object that implements <code>RegisteredEventsButtonListener</code>
     */
    void addRegisteredEventsButtonListener(RegisteredEventsButtonListener listener);

    /**
     * Listens to actions performed on the message button and switches screen
     *
     * @param listener the object that implements <code>MessageButtonListener</code>
     */
    void addMessageButtonListener(MessageButtonListener listener);

    /**
     * Listens to actions performed on the inbox button and switches screen
     *
     * @param listener the object that implements <code>InboxButtonListener</code>
     */
    void addInboxButtonListener(InboxButtonListener listener);

    /**
     * Listens to actions performed on the export events button and switches screen
     *
     * @param listener the object that implements <code>ExportEventsButtonListener</code>
     */
    void addExportEventsButtonListener(ExportEventsButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnLogoutButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnUserManagementButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnRegisteredEventsButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnMessageButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnInboxButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnExportEventsButtonClicked();

    /**
     * Open up <code>LandingUI</code> frame
     * @return a <code>LandingUI</code>
     */
    ILandingUI goToLandingUI();

    /**
     * Open up <code>EventSignUpUI</code> frame
     * @return a <code>EventSignUpUI</code>
     */
    IEventSignUpUI goToEventSignUpUI();

    /**
     * Open up <code>AdminUserManagementUI</code> frame
     * @return a <code>AdminUserManagementUI</code>
     */
    IAdminUserManagementUI goToAdminUserManagementUI();

    /**
     * Open up <code>OrganizerMessageUI</code> frame
     * @return a <code>OrganizerMessageUI</code>
     */
    IOrganizerMessageUI goToOrganizerMessageUI(ArrayList<String> userInfo);

    /**
     * Open up <code>InboxUI</code> frame
     * @return a <code>InboxUI</code>
     */
    IInboxUI goToInboxUI(ArrayList<String> messages);

    /**
     * Open up <code>AdminExportToHTMLUI</code> frame
     * @return a <code>AdminExportToHTMLUI</code>
     */
    IAdminExportToHTMLUI goToAdminExportToHTMLUI(ArrayList<String> userInfo);
}
