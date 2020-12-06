package main.gui;

import main.controllers.AuthController;
import main.controllers.ProgramController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private ProgramController programController;
    private JPanel panel1;
    private JButton backButton;
    private JTextField emailTextField;
    private JPasswordField PasswordField;
    private JButton logInButton;
    private final AuthController authController;

    public LoginUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
        this.authController = programController.getAuthController();

//        logInButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = emailTextField.getText();
//                String password = String.valueOf(PasswordField.getPassword());
//
//            }
//        });
//        // need to find out how to display wrong information message
//
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // nextScreen = new LandingScreen(programController);
//            }
//        });
    }
}
