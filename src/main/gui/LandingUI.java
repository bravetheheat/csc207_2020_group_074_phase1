package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")

public class LandingUI extends JFrame {
    private ProgramController programController;
    private JPanel mainPanel;
    private JButton logInButton;
    private JButton registerButton;
    private JLabel titleLabel;


    public LandingUI(ProgramController programController) {
        this.programController = programController;
        programController.startUI();

        this.setTitle("Program X");
        this.setSize(600, 500);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        logInButton.addActionListener(e -> {
            programController.saveForNext();
            new LoginUI(programController);
            dispose();
        });

        registerButton.addActionListener(e -> {
            programController.saveForNext();
            new RegisterUI(programController);
            dispose();
        });

    }

}
