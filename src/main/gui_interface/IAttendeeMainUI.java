package main.gui_interface;

import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The <code>AttendeeMainUIPresenter</code> will depend on this interface which is
 * implemented by <code>AttendeeMainUI</code>
 *
 * @author Yi Tao Li
 */
public interface IAttendeeMainUI extends IView {

    /**
     * Listens to actions performed on the logout button and logs the user out
     *
     * @param listener the object that implements <code>LogoutButtonListener</code>
     */
    void addLogoutButtonListener(LogoutButtonListener listener);

    /**
     * Listens to actions performed on the event registration button and switches screen
     *
     * @param listener the object that implements <code>RegisterForEventsButtonListener</code>
     */
    void addRegisterForEventsButtonListener(RegisterForEventsButtonListener listener);


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
    void notifyListenerOnRegisterForEventsButtonClicked();

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
     * Open up <code>AttendeeMessageUI</code> frame
     * @return a <code>AttendeeMessageUI</code>
     */
    IAttendeeMessageUI goToAttendeeMessageUI(ArrayList<String> users);

    /**
     * Open up <code>InboxUI</code> frame
     * @return a <code>InboxUI</code>
     */
    IInboxUI goToInboxUI(ArrayList<String> messages);
}
