package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class AttendeeMessageUI extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JList UsersList;
    private JTextArea textArea1;
    private JButton sendButton;
    private ProgramController programController;

    public AttendeeMessageUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

    }
}
