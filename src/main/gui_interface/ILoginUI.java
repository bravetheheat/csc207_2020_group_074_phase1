package main.gui_interface;

import main.gui.LandingUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

/**
 * The interface for <code>LoginUI</code>
 *
 * @author Steven Yuan
 */
public interface ILoginUI extends IView {

    /**
     * Add the class that implements <code>LoginUIListener</code>
     * @param listener listens to actions performed on the login button and switch screen
     */
    void addLoginUIListener(LoginUIListener listener);

    /**
     * Add the class that implements <code>BackButtonListener</code>
     * @param listener listens to actions performed on the back button and switch screen
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
}
