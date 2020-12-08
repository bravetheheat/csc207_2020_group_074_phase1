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
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel loginTitleLabel;
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
            String password = String.valueOf((passwordField.getPassword()));
            if (authController.login(username, password)) {
                String userType = authController.getUserType();
                switch(userType){
                    case "Attendee":
                        System.out.println("go to attendee");
                        programController.saveForNext();
                        new AttendeeMainUI(programController);
                        dispose();
                        break;
                    case "Organizer":
                        System.out.println("go to organizer");
                        programController.saveForNext();
                        new OrganizerMainUI(programController);
                        dispose();
                        break;
                    case "Speaker":
                        System.out.println("go to speaker");
                        programController.saveForNext();
                        new SpeakerMainUI(programController);
                        dispose();
                        break;
                    default:
                        programController.saveForNext();
                        new RegisterMessageErrorUI(programController);
                        dispose();
                }
            }
        });
        // need to find out how to display wrong information message

        backButton.addActionListener(e -> {
            programController.saveForNext();
            new LandingUI(programController);
            dispose();
        });
    }
}
