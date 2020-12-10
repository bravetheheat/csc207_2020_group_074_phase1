package main.gui;

import main.gui_interface.ILandingUI;
import main.gui_interface.IRegisterUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterUIListener;

import javax.swing.*;

/**
 * The register screen in which users can enter info to sign up.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterUI extends JFrame implements IRegisterUI {
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
    private RegisterMessageSuccessfulUI registerMessageSuccessfulUI;
    private RegisterMessageErrorUI registerMessageErrorUI;
    private LandingUI landingUI;

    public RegisterUI() {
        this.setSize(600, 500);
        this.setContentPane(registerPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
    }


    @Override
    public void addRegisterUIListener(RegisterUIListener listener) {
        registerUIListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void notifyListenerOnConfirmButtonClicked() {
        registerUIListener.onConfirmButtonClicked();
    }

    @Override
    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    @Override
    public String getUserType() {
        return userTypeTextField.getText();
    }

    @Override
    public String getUserName() {
        return userNameTextField.getText();
    }

    @Override
    public String getPwd() {
        return String.valueOf((passwordField.getPassword()));
    }

    @Override
    public ILandingUI goToLandingUI() {
        landingUI = new LandingUI();
        this.dispose();
        return landingUI;
    }

    @Override
    public RegisterMessageSuccessfulUI goToSuccessfulUI() {
        registerMessageSuccessfulUI = new RegisterMessageSuccessfulUI();
        this.dispose();
        return registerMessageSuccessfulUI;
    }

    @Override
    public RegisterMessageErrorUI goToErrorUI() {
        registerMessageErrorUI = new RegisterMessageErrorUI();
        this.dispose();
        return registerMessageErrorUI;
    }

}
