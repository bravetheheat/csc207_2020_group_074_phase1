package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class AttendeeMainUI extends JFrame {
    private ProgramController programController;
    private JButton logOutButton;
    private JPanel panel1;
    private JButton registerForEventsButton;
    private JButton messageButton;
    private JButton inboxButton;

    public AttendeeMainUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;


    }
}