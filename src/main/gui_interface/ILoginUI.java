package main.gui_interface;

import main.gui.*;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

import java.util.ArrayList;

/**
 * The interface for <code>LoginUI</code>
 *
 * @author Steven Yuan
 */
public interface ILoginUI extends IView {

    /**
     * Listens to actions performed on the login button and switch screen
     *
     * @param listener the object that implements <code>LoginUIListener</code>
     */
    void addLoginUIListener(LoginUIListener listener);

    /**
     * Listens to actions performed on the back button and switch screen
     *
     * @param listener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnLoginButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnBackButtonClicked();

    /**
     * Get the username from the current user
     * @return username
     */
    String getUserName();

    /**
     * Get the password from the current user
     * @return password
     */
    String getPwd();

    /**
     * Go back to the main screen
     * @return <code>LandingUI</code>
     */
    LandingUI goToLandingUI();

    AttendeeMainUI goToAttendeeMainUI();

    OrganizerMainUI goToOrganizerMainUI();

    SpeakerMainUI goToSpeakerMainUI(ArrayList<String> events);

    AdminMainUI goToAdminMainUI();

    LoginMessageErrorUI goToLoginMessageErrorUI();
}
