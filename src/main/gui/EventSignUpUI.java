package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IEventSignUpUI;

import javax.swing.*;

public class EventSignUpUI extends JFrame implements IEventSignUpUI {
    private JButton backButton;
    private JPanel panel1;
    private JButton signUpForAnButton;
    private JButton cancelAnEventButton;
    private JButton checkEventDetailsButton;
    private JList list1;
    private JButton confirmButton;
    private ProgramController programController;

    public EventSignUpUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
