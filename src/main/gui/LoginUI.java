package main.gui;

import main.gui_interface.ILoginUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The login screen in which users can enter account info and log in.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LoginUI extends JFrame implements ILoginUI {

    private JPanel loginPanel;
    private JButton backButton;
    private JTextField emailTextField;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel loginTitleLabel;
    private LoginUIListener loginUIListener;
    private BackButtonListener backButtonListener;
    private LandingUI landingUI;
    private AttendeeMainUI attendeeMainUI;
    private OrganizerMainUI organizerMainUI;
    private SpeakerMainUI speakerMainUI;
    private AdminMainUI adminMainUI;
    private LoginMessageErrorUI loginMessageErrorUI;

    public LoginUI() {

        this.setSize(600, 500);
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        logInButton.addActionListener(e -> notifyListenerOnLoginButtonClicked());

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
    }

    @Override
    public void addLoginUIListener(LoginUIListener listener) {
        loginUIListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void notifyListenerOnLoginButtonClicked() {
        loginUIListener.onLoginButtonClicked();
    }

    @Override
    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    @Override
    public String getUserName() {
        return emailTextField.getText();
    }

    @Override
    public String getPwd() {
        return String.valueOf((passwordField.getPassword()));
    }

    @Override
    public LandingUI goToLandingUI() {
        landingUI = new LandingUI();
        this.dispose();
        return landingUI;
    }

    @Override
    public AttendeeMainUI goToAttendeeMainUI() {
        attendeeMainUI = new AttendeeMainUI();
        this.dispose();
        return attendeeMainUI;
    }

    @Override
    public OrganizerMainUI goToOrganizerMainUI() {
        organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    @Override
    public SpeakerMainUI goToSpeakerMainUI(ArrayList<String> events) {
        speakerMainUI = new SpeakerMainUI(events);
        this.dispose();
        return speakerMainUI;
    }

    @Override
    public AdminMainUI goToAdminMainUI() {
        adminMainUI = new AdminMainUI();
        this.dispose();
        return adminMainUI;
    }

    @Override
    public LoginMessageErrorUI goToLoginMessageErrorUI() {
        loginMessageErrorUI = new LoginMessageErrorUI();
        this.dispose();
        return loginMessageErrorUI;
    }
}
