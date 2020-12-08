package main.gui;

import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterUIListener;

import javax.swing.*;

/**
 * The register screen in which users can enter info to sign up.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterUI extends JFrame {
    private JButton backButton;
    private JPanel registerPanel;
    private JButton confirmButton;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JLabel userTypeLabel;
    private JTextField userTypeTextField;
    private JLabel messageLabel;
    private RegisterUIListener registerUIListener;
    private BackButtonListener backButtonListener;

    public RegisterUI() {
        this.setSize(600, 500);
        this.setContentPane(registerPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
    }


    public void addRegisterUIListener(RegisterUIListener listener) {
        registerUIListener = listener;
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerOnConfirmButtonClicked() {
        registerUIListener.onConfirmButtonClicked();
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public String getUserType() {
        return userTypeTextField.getText();
    }

    public String getUserName() {
        return userNameTextField.getText();
    }

    public String getPwd() {
        return String.valueOf((passwordField.getPassword()));
    }
}
