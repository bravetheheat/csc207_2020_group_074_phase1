package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class EventsManagementUI extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JButton createRoomButton;
    private JButton createEventButton;
    private JButton cancelEventButton;
    private JButton modifyRoomButton;
    private JButton modifyTimeButton;
    private JButton modifySpeakerButton;
    private JButton seeScheduleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel Name;
    private JButton confirmButton;
    private JList EventList;
    private ProgramController programController;

    public EventsManagementUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

    }
}