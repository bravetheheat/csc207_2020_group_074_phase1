package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")

public class LandingUI extends JFrame {
    private ProgramController programController;
    private JPanel mainPanel;
    private JTextField welcomeText;
    private JButton exitButton;
    private JButton logInButton;
    private JButton registerButton;


    public LandingUI(ProgramController programController) {
        this.programController = programController;

        this.setTitle("Program X");
        this.setSize(600, 500);
//        this.add(mainPanel);
//        this.add(welcomeText);
//        this.add(exitButton);
//        this.add(logInButton);
//        this.add(registerButton);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        exitButton.addActionListener(e -> {
            // programController.setNextScreen(null);
        });

        logInButton.addActionListener(e -> {
            new LoginUI(programController);
            dispose();
        });

        registerButton.addActionListener(e -> {
            new RegisterUI(programController);
            dispose();
        });

    }

}
