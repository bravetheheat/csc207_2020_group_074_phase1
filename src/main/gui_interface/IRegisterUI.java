package main.gui_interface;

import main.gui.RegisterMessageErrorUI;
import main.gui.RegisterMessageSuccessfulUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterUIListener;

/**
 * Interface for <code>RegisterUI</code>
 *
 * @author Steven Yuan
 */
public interface IRegisterUI extends IView {

    /**
     * Listens to actions performed on the register button and switch screen
     *
     * @param listener the object that implements <code>RegisterUIListener</code>
     */
    void addRegisterUIListener(RegisterUIListener listener);

    /**
     * Listens to actions performed on the back button and switch screen
     *
     * @param listener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnConfirmButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnBackButtonClicked();

    /**
     * Return the type of the current user
     * @return user type
     */
    String getUserType();

    /**
     * Return the user name inputted by the user
     * @return user name
     */
    String getUserName();

    /**
     * Return the password inputted by the user
     * @return password
     */
    String getPwd();

    /**
     * Go back to the main screen
     * @return a <code>LandingUI</code>
     */
    ILandingUI goToLandingUI();

    /**
     * Display "successful" notification
     * @return a <code>RegisterMessageSuccessfulUI</code>
     */
    RegisterMessageSuccessfulUI goToSuccessfulUI();

    /**
     * Display "Please try again" notification
     * @return a <code>RegisterMessageErrorUI</code>
     */
    RegisterMessageErrorUI goToErrorUI();
}
