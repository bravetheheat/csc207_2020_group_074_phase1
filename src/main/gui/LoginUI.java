package main.gui;

import main.gui_interface.ILoginUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

import javax.swing.*;

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
}
