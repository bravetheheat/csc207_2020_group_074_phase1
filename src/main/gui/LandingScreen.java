package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingScreen extends JFrame {
    private ProgramController programController;
//    private JTextField welcomeText;
//    private JButton exitButton;
//    private JButton logInButton;
//    private JButton registerButton;

    public LandingScreen (ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // programController.setNextScreen(null);
//            }
//        });
//        logInButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // nextScreen = new LoginScreen(programController);
//            }
//        });
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // nextScreen = new RegisterScreen(programController);
//            }
//        });
    }
}
