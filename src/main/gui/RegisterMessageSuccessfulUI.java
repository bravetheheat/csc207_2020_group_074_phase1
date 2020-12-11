package main.gui;

import main.gui_interface.IRegisterMessageSuccessfulUI;

import javax.swing.*;

/**
 * A frame that display "successfully registered" when a user has registered successfully.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulUI extends NotificationUI
        implements IRegisterMessageSuccessfulUI {

    private JLabel jLabel;
    private RegisterUI registerUI;

    public RegisterMessageSuccessfulUI() {
        super();

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Successfully Registered!");

        setVisible(true);
    }

    @Override
    public RegisterUI goToRegister() {
        registerUI = new RegisterUI();
        this.dispose();
        return registerUI;
    }

}
