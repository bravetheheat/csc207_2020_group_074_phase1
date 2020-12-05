package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class OrganizerHomeScreen extends JFrame {
    private ProgramController programController;
    private JButton logOutButton;
    private JPanel panel1;
    private JButton userManagementButton;
    private JButton manageAndEventOrButton;
    private JButton registeredEventsButton;
    private JButton messagesButton;
    private JButton inboxButton;
    private JButton dataManagementButton;

    public OrganizerHomeScreen (ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

    }
}