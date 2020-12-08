package main.gui;

import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

import javax.swing.*;

/**
 * The login screen in which users can enter account info and log in.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LoginUI extends JFrame {

//    private ProgramController programController;
    private JPanel loginPanel;
    private JButton backButton;
    private JTextField emailTextField;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel loginTitleLabel;
//    private final AuthController authController;
    private LoginUIListener loginUIListener;
    private BackButtonListener backButtonListener;

    public LoginUI() {

        this.setSize(600, 500);
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        logInButton.addActionListener(e -> {
//            String username = emailTextField.getText();
//            String password = String.valueOf((passwordField.getPassword()));
//            if (authController.login(username, password)) {
//                String userType = authController.getUserType();
//                switch(userType){
//                    case "Attendee":
//                        System.out.println("go to attendee");
//                        programController.saveForNext();
//                        new AttendeeMainUI(programController);
//                        dispose();
//                        break;
//                    case "Organizer":
//                        System.out.println("go to organizer");
//                        programController.saveForNext();
//                        new OrganizerMainUI(programController);
//                        dispose();
//                        break;
//                    case "Speaker":
//                        System.out.println("go to speaker");
//                        programController.saveForNext();
//                        new SpeakerMainUI(programController);
//                        dispose();
//                        break;
//                    default:
//                        programController.saveForNext();
//                        new RegisterMessageErrorUI(programController);
//                        dispose();
//                }
//            }
            notifyListenerOnLoginButtonClicked();
        });

        backButton.addActionListener(e -> {
//            programController.saveForNext();
//            new LandingUI(programController);
//            dispose();
            notifyListenerOnBackButtonClicked();
        });
    }

    public void addLoginUIListener(LoginUIListener listener) {
        loginUIListener = listener;
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerOnLoginButtonClicked() {
        loginUIListener.onLoginButtonClicked();
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public String getUserName() {
        return emailTextField.getText();
    }

    public String getPwd() {
        return String.valueOf((passwordField.getPassword()));
    }
}
