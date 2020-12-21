package main.gui_interface;

import main.gui.EventSignUpUI;
import main.guilisteners.*;

import java.util.ArrayList;

/**
 * The <code>OrganizerMainUIPresenter</code> will depend on this interface which is
 * implemented by <code>OrganizerMainUI</code>
 *
 * @author Yi Tao Li
 */
public interface IOrganizerMainUI extends IView {

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
     * Listens to actions performed on the event and room management button and switches screen
     *
     * @param listener the object that implements <code>ManageEventRoomButtonListener</code>
     */
    void addManageEventRoomButtonListener(ManageEventRoomButtonListener listener);

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
    void notifyListenerOnManageEventRoomButtonClicked();

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
     * Open up <code>EventsManagementUI</code> frame
     * @return a <code>EventsManagementUI</code>
     */
    IEventsManagementUI goToEventsManagementUI();

    /**
     * Open up <code>UserManagementUI</code> frame
     * @return a <code>UserManagementUI</code>
     */
    IUserManagementUI goToUserManagementUI();

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

}
