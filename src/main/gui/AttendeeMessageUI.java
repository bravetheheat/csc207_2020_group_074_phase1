package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IAttendeeMessageUI;

import javax.swing.*;

public class AttendeeMessageUI extends JFrame implements IAttendeeMessageUI {
    private JButton backButton;
    private JPanel panel1;
    private JList UsersList;
    private JTextArea textArea1;
    private JButton sendButton;
    private ProgramController programController;

    public AttendeeMessageUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

    }
}
