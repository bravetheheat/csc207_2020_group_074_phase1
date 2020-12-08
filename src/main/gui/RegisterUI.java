package main.gui;

import main.controllers.AuthController;
import main.controllers.ProgramController;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")

public class RegisterUI extends JFrame {
    private ProgramController programController;
    private AuthController authController;
    private JButton backButton;
    private JPanel registerPanel;
    private JButton confirmButton;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JLabel userTypeLabel;
    private JTextField userTypeTextField;
    private JLabel messageLabel;

    public RegisterUI(ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();

        this.setSize(600, 500);
        this.setContentPane(registerPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> {
            String userType = userTypeTextField.getText();
            String username = userNameTextField.getText();
            String password = String.valueOf((passwordField.getPassword()));
            if (!userType.equals("Attendee") && !userType.equals("Organizer")) {
                new RegisterMessageErrorUI(programController);
                dispose();
            }
            boolean success = this.authController.registerUser(
                    username, password, userType);
            if (!success) {
                System.out.println("try again!");
                new RegisterMessageErrorUI(programController);
                dispose();
            }
            else {
                System.out.println("successful!");
                new RegisterMessageSuccessfulUI(programController);
                dispose();
            }

        });

        backButton.addActionListener(e -> {
            new LandingUI(programController);
            dispose();
        });

    }
}
