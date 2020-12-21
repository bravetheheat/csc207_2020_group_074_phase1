package main.gui_interface;

import main.gui.AttendeeMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;

/**
 * The <code>AttendeeMessageUIPresenter</code> will depend on this interface which is
 * implemented by <code>AttendeeMessageUI</code>
 *
 * @author Yi Tao Li
 */
public interface IAttendeeMessageUI extends IView {

    /**
     * Listens to actions performed on the back button and returns to main screen
     *
     * @param backButtonListener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener backButtonListener);

    /**
     * Listens to actions performed on the send button and sends the message
     *
     * @param sendButtonListener the object that implements <code>SendButtonListener</code>
     */
    void addSendButtonListener(SendButtonListener sendButtonListener);

    @Deprecated
    void addListSelectionListener(ListSelectionListener listSelectionListener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyBackButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifySendButtonListener();

    @Deprecated
    void notifyListSelectionListener();

    /**
     * Retrieves the message from the text field
     * @return message being sent
     */
    String getMessage();

    /**
     * Open up <code></code> frame
     * @return a <code></code>
     */
    AttendeeMainUI goToAttendeeMainUI();

    /**
     * Retrieves the list of users that the attendee could message
     * @return list of users that could be messaged
     */
    JList getUsersList();

    /**
     * Display a "message sent" notification
     */
    void sendMessageSuccessful();

    /**
     * Display an error notification
     */
    void sendMessageError();
}
