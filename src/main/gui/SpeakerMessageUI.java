package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.ISpeakerMessageUI;

import javax.swing.*;

public class SpeakerMessageUI extends JFrame implements ISpeakerMessageUI {
    private ProgramController programController;
    private JPanel panel1;
    private JList UserList;
    private JButton broadcastButton;
    private JTextField textField1;
    private JButton selectedUsersButton;
    private JButton backButton;
    private JButton inboxButton;

    public SpeakerMessageUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }

}
