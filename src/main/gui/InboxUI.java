package main.gui;

import main.controllers.ProgramController;
import main.entities.Inbox;

import javax.swing.*;

public class InboxUI extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JList list1;
    private ProgramController programController;

    public InboxUI(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
