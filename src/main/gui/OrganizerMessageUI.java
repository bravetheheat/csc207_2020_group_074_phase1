package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IOrganizerMessageUI;

import javax.swing.*;

public class OrganizerMessageUI extends JFrame implements IOrganizerMessageUI  {
    private ProgramController programController;
    private JButton backButton;
    private JPanel panel1;
    private JList UserList;
    private JTextArea textArea1;
    private JButton selectedUsersButton;
    private JButton everyoneButton;
    private JButton allAttendeesButton;
    private JButton allSpeakersButton;

    public OrganizerMessageUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
