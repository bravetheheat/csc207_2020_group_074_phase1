package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class EventsManagementScreen extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JButton createRoomButton;
    private JButton createEventButton;
    private JButton cancelEventButton;
    private JButton modifyRoomButton;
    private JButton modifyTimeButton;
    private JButton modifySpeakerButton;
    private JButton seeSecheduleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel Name;
    private JButton confirmButton;
    private JList EventList;
    private ProgramController programController;

    public EventsManagementScreen (ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;

    }
}
