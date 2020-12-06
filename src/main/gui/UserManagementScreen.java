package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class UserManagementScreen extends JFrame {
    private ProgramController programController;
//    private JButton backButton;
//    private JPanel panel1;
//    private JButton confirmButton;
//    private JTextField textField1;
//    private JTextField textField2;
//    private JList UserList;

    public UserManagementScreen(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
