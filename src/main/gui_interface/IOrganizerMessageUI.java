package main.gui_interface;

import main.gui.AdminMainUI;
import main.gui.OrganizerMainUI;
import main.guilisteners.*;

import javax.swing.*;

/**
 * The <code>OrganizerMessageUIPresenter</code> will depend on this interface which is
 * implemented by <code>OrganizerMessageUI</code>
 *
 * @author Yi Tao Li
 */
public interface IOrganizerMessageUI extends IView {

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

    /**
     * Listens to actions performed on the everyone button and sends the message to all users
     *
     * @param everyoneButtonListener the object that implements <code>EveryoneButtonListener</code>
     */
    void addEveryoneButtonListener(EveryoneButtonListener everyoneButtonListener);

    /**
     * Listens to actions performed on the all attendees button and sends the message to all attendees
     *
     * @param allAttendeesButtonListener the object that implements <code>AllAttendeesButtonListener</code>
     */
    void addAllAttendeesButtonListener(AllAttendeesButtonListener allAttendeesButtonListener);

    /**
     * Listens to actions performed on the all speakers button and sends the message to all speakers
     *
     * @param allSpeakersButtonListener the object that implements <code>AllSpeakersButtonListener</code>
     */
    void addAllSpeakersButtonListener(AllSpeakersButtonListener allSpeakersButtonListener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyBackButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifySendButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyEveryoneButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyAllAttendeesButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyAllSpeakersButtonListener();

    /**
     * Retrieves the message from the text field
     * @return message being sent
     */
    String getMessage();

    /**
     * Open up <code>OrganizerMainUI</code> frame
     * @return a <code>OrganizerMainUI</code>
     */
    OrganizerMainUI goToOrganizerMainUI();

    /**
     * Open up <code>AdminMainUI</code> frame
     * @return a <code>AdminMainUI</code>
     */
    AdminMainUI goToAdminMainUI();

    /**
     * Retrieves the list of users that the organizer could message
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
