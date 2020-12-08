package main.gui;

import javax.swing.*;

/**
 * A subclass of <code>NotificationUI</code> that displays error message
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageErrorUI extends NotificationUI {
    private JLabel jLabel;

    public RegisterMessageErrorUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Please try again!");

        setVisible(true);
    }
}
