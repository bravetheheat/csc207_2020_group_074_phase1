package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class GatewayUI extends JFrame {
    private ProgramController programController;
    private JButton button1;
    private JPanel panel1;
    private JButton saveAllButton;
    private JButton saveUsersButton;
    private JButton saveRoomsButton;
    private JButton saveEventsButton;
    private JButton saveMessagesButton;
    private JButton saveInboxesButton;

    public GatewayUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
