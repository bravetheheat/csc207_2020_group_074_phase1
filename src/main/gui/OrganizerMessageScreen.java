package main.gui;

import main.controllers.ProgramController;

import javax.swing.*;

public class OrganizerMessageScreen extends JFrame {
    private ProgramController programController;
//    private JButton backButton;
//    private JPanel panel1;
//    private JList UserList;
//    private JTextArea textArea1;
//    private JButton selectedUsersButton;
//    private JButton everyoneButton;
//    private JButton allAttendeesButton;
//    private JButton allSpeakersButton;

    public OrganizerMessageScreen(ProgramController programController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }
}
