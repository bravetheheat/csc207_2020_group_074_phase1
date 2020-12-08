package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

/**
 * A frame that display "successfully registered" when a user has registered successfully.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulUI extends RegisterMessageUI {
    private JLabel jLabel;


    public RegisterMessageSuccessfulUI(ProgramController programController) {
        super(programController);

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Successfully Registered!");

        setVisible(true);
    }

}
