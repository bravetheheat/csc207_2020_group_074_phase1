package main.gui;

import main.gui_interface.INotificationUI;

import javax.swing.*;

/**
 * A frame that display "successfully registered" when a user has registered successfully.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulUI extends NotificationUI implements INotificationUI {
    private JLabel jLabel;


    public RegisterMessageSuccessfulUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Successfully Registered!");

        setVisible(true);
    }

}
