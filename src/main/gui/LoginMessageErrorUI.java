package main.gui;

import main.gui_interface.ILoginMessageErrorUI;

import javax.swing.*;

/**
 * Display message when login is unsuccessful
 *
 * @see LoginUI
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LoginMessageErrorUI extends NotificationUI
        implements ILoginMessageErrorUI {

    private JLabel jLabel;
    private LoginUI loginUI;

    public LoginMessageErrorUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Incorrect username or password!");

        setVisible(true);
    }

    @Override
    public LoginUI goToLogin() {
        loginUI = new LoginUI();
        this.dispose();
        return loginUI;
    }
}
