package main.gui;

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

    public EventSignUpUI() {
        this.setTitle("Event Sign Up");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
