package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;
import java.awt.*;

/**
 * A frame that display messages to the user.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageUI extends JFrame {
    private ProgramController programController;
    private JButton backButton;

    public RegisterMessageUI(ProgramController programController) {
        this.programController = programController;

        this.setLayout(null);

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // back button
        backButton = new JButton("Back");
        Dimension dimBackButton = backButton.getPreferredSize();
        backButton.setBounds(0, 0, dimBackButton.width, dimBackButton.height);
//        backButton.setLocation(0,0);
        add(backButton);

        backButton.addActionListener(e -> {
            programController.saveForNext();
            new RegisterUI(programController);
            dispose();
        });
    }

    public void addMessage(JLabel jLabel, String message) {
        jLabel.setText(message);
        Dimension dim = jLabel.getPreferredSize();
        int xPos = (this.getWidth() - dim.width) / 2;
        int yPos = (this.getHeight() - dim.height) / 2;
        jLabel.setBounds(xPos, yPos, dim.width, dim.height);
        this.add(jLabel);
    }
}
