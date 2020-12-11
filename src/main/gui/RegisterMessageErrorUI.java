package main.gui;

import main.gui_interface.IRegisterMessageErrorUI;

import javax.swing.*;

/**
 * A subclass of <code>NotificationUI</code> that displays error message
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageErrorUI extends NotificationUI
        implements IRegisterMessageErrorUI {

    private JLabel jLabel;
    private RegisterUI registerUI;

    public RegisterMessageErrorUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Please try again!");

        setVisible(true);
    }

    @Override
    public RegisterUI goToRegister() {
        registerUI = new RegisterUI();
        this.dispose();
        return registerUI;
    }
}
