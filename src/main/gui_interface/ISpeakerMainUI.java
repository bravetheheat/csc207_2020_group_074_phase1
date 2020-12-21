package main.gui_interface;

import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;

import java.util.ArrayList;

/**
 * The <code>SpeakerMainUIPresenter</code> will depend on this interface which is
 * implemented by <code>SpeakerMainUI</code>
 *
 * @author Yi Tao Li
 */
public interface ISpeakerMainUI extends  IView {

    /**
     * Listens to actions performed on the logout button and logs the user out
     *
     * @param listener the object that implements <code>LogoutButtonListener</code>
     */
    void addLogoutButtonListener(LogoutButtonListener listener);

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
     * Open up <code>SpeakerMessageUI</code> frame
     * @return a <code>SpeakerMessageUI</code>
     */
    ISpeakerMessageUI goToSpeakerMessageUI(ArrayList<String> users);

    /**
     * Open up <code>InboxUI</code> frame
     * @return a <code>InboxUI</code>
     */
    IInboxUI goToInboxUI(ArrayList<String> messages, ArrayList<String> events);

    /**
     * Retrieves the speaker's list of events
     * @return an ArrayList of events
     */
    ArrayList<String> getEvents();
}
