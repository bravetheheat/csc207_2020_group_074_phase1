package main.gui_interface;

import main.gui.RegisterUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;

/**
 * The interface for <code>NotificationUI</code>
 *
 * @author Steven Yuan
 */
public interface INotificationUI {

    /**
     * Listens to actions performed on the back button and switch screen
     *
     * @param listener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnBackButtonClicked();

    /**
     * Display a message to the screen
     * @param jLabel a <code>JLabel</code> object
     * @param message notification to be displayed
     */
    void addMessage(JLabel jLabel, String message);

    /**
     * Open up the <code>RegisterUI</code> screen
     * @return a <code>RegisterUI</code>
     */
    RegisterUI goToRegister();
}
