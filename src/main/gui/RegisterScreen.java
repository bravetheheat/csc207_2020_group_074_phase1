package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class RegisterScreen extends JFrame {
    private ProgramController programController;
    private JButton backButton;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton confirmButton;

    public RegisterScreen (ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}