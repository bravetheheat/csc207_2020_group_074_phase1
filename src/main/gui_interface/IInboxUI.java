package main.gui_interface;

import main.gui.AdminMainUI;
import main.gui.AttendeeMainUI;
import main.gui.OrganizerMainUI;
import main.gui.SpeakerMainUI;
import main.guilisteners.BackButtonListener;

import java.util.ArrayList;

/**
 * The <code>InboxUIPresenter</code> will depend on this interface which is
 * implemented by <code>InboxUI</code>
 *
 * @author Yi Tao Li
 */
public interface IInboxUI extends IView {

    /**
     * Listens to actions performed on the back button and returns to main screen
     *
     * @param backButtonListener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener backButtonListener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnBackButtonClicked();

    /**
     * Open up <code>AttendeeMainUI</code> frame
     * @return a <code>AttendeeMainUI</code>
     */
    AttendeeMainUI goToAttendeeMainUI();

    /**
     * Open up <code>SpeakerMainUI</code> frame
     * @return a <code>SpeakerMainUI</code>
     */
    SpeakerMainUI goToSpeakerMainUI();

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
}
