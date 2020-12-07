package main.gui;

import main.controllers.AuthController;
import main.controllers.ProgramController;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")

public class LoginUI extends JFrame {

    private ProgramController programController;
    private JPanel loginPanel;
    private JButton backButton;
    private JTextField emailTextField;
    private JPasswordField PasswordField;
    private JButton logInButton;
    private final AuthController authController;

    public LoginUI(ProgramController programController) {
        this.programController = programController;

        this.setSize(600, 500);
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.authController = programController.getAuthController();

        logInButton.addActionListener(e -> {
            String username = emailTextField.getText();
            String password = String.valueOf(PasswordField.getPassword());

        });
        // need to find out how to display wrong information message

        backButton.addActionListener(e -> {
            new LandingUI(programController);
            dispose();
        });
    }
}
