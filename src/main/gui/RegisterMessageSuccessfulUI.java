package main.gui;

import javax.swing.*;

/**
 * A frame that display "successfully registered" when a user has registered successfully.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulUI extends NotificationUI {
    private JLabel jLabel;


    public RegisterMessageSuccessfulUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Successfully Registered!");

        setVisible(true);
    }

}
