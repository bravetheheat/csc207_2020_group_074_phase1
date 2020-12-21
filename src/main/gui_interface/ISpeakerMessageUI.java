package main.gui_interface;

import main.gui.SpeakerMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.BroadcastButtonListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;

public interface ISpeakerMessageUI extends IView {

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
     * Listens to actions performed on the send button and sends the message to all users of a specific event
     *
     * @param broadcastButtonListener the object that implements <code>BroadcastButtonListener</code>
     */
    void addBroadcastButtonListener(BroadcastButtonListener broadcastButtonListener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyBroadcastButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyBackButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifySendButtonListener();

    /**
     * Retrieves the message from the text field
     * @return message being sent
     */
    String getMessage();

    /**
     * Open up <code>SpeakerMainUI</code> frame
     * @return a <code>SpeakerMainUI</code>
     */
    SpeakerMainUI goToSpeakerMainUI();

    /**
     * Retrieves the list of users that the speaker could message
     * @return list of users that could be messaged
     */
    JList getUsersList();

    /**
     * Retrieves the list of the speaker's events
     * @return list of the speaker's events
     */
    JList getEventsList();

    /**
     * Display a "message sent" notification
     */
    void sendMessageSuccessful();

    /**
     * Display an error notification
     */
    void sendMessageError();

}
