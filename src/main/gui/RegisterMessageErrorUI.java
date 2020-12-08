package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageErrorUI extends RegisterMessageUI {
    private JLabel jLabel;

    public RegisterMessageErrorUI(ProgramController programController) {
        super(programController);

        // message labels
        jLabel = new JLabel();
        addMessage(jLabel, "Please try again!");

        setVisible(true);
    }
}
