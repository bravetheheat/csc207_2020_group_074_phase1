package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class SpeakerMessageUI extends JFrame {
    private ProgramController programController;
    private JPanel panel1;
    private JList UserList;
    private JButton broadcastButton;
    private JTextField textField1;
    private JButton selectedUsersButton;
    private JButton backButton;
    private JButton inboxButton;

    public SpeakerMessageUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
