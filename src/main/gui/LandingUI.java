package main.gui;

import main.controllers.ProgramController;
import main.guilisteners.LoginButtonListener;
import main.guilisteners.RegisterButtonListener;

import javax.swing.*;

/**
 * The main/initial frame of the application.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LandingUI extends JFrame {
    private ProgramController programController;
    private JPanel mainPanel;
    private JButton logInButton;
    private JButton registerButton;
    private JLabel titleLabel;
    private LoginButtonListener loginButtonListener;
    private RegisterButtonListener registerButtonListener;

    public LandingUI() {
        this.setTitle("Program X");
        this.setSize(600, 500);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        logInButton.addActionListener(e -> notifyListenerOnLoginButtonClicked());

        registerButton.addActionListener(e -> notifyListenerOnRegisterButtonClicked());

    }

    public void addLoginButtonListener(LoginButtonListener listener) {
        loginButtonListener = listener;
    }

    public void addRegisterButtonLister(RegisterButtonListener listener) {
        registerButtonListener = listener;
    }

    public void notifyListenerOnLoginButtonClicked() {
        loginButtonListener.onLoginButtonClicked();
    }

    public void notifyListenerOnRegisterButtonClicked() {
        registerButtonListener.onRegisterButtonClicked();
    }

}
