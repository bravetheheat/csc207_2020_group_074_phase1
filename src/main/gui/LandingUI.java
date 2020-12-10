package main.gui;

import main.gui_interface.ILandingUI;
import main.gui_interface.ILoginUI;
import main.gui_interface.IRegisterUI;
import main.guilisteners.LoginButtonListener;
import main.guilisteners.RegisterButtonListener;

import javax.swing.*;

/**
 * The main/initial frame of the application.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LandingUI extends JFrame implements ILandingUI {
    private JPanel mainPanel;
    private JButton logInButton;
    private JButton registerButton;
    private JLabel titleLabel;
    private LoginButtonListener loginButtonListener;
    private RegisterButtonListener registerButtonListener;
    private LoginUI loginUI;
    private RegisterUI registerUI;

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

    @Override
    public void addLoginButtonListener(LoginButtonListener listener) {
        loginButtonListener = listener;
    }

    @Override
    public void addRegisterButtonLister(RegisterButtonListener listener) {
        registerButtonListener = listener;
    }

    @Override
    public void notifyListenerOnLoginButtonClicked() {
        loginButtonListener.onLoginButtonClicked();
    }

    @Override
    public void notifyListenerOnRegisterButtonClicked() {
        registerButtonListener.onRegisterButtonClicked();
    }

    @Override
    public ILoginUI goToLoginUI() {
        loginUI = new LoginUI();
        this.dispose();
        return loginUI;
    }

    @Override
    public IRegisterUI goToRegisterUI() {
        registerUI = new RegisterUI();
        this.dispose();
        return registerUI;
    }
}
