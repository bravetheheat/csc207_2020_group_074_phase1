package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IOrganizerMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;

public class OrganizerMessageUI extends JFrame implements IOrganizerMessageUI  {
    private ProgramController programController;
    private JButton backButton;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton selectedUsersButton;
    private JButton everyoneButton;
    private JButton allAttendeesButton;
    private JButton allSpeakersButton;
    private JScrollPane listScroller;
    private JList userList;

    public OrganizerMessageUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programController = programController;
    }

    @Override
    public void addBackButtonListener(BackButtonListener backButtonListener) {

    }

    @Override
    public void addSendButtonListener(SendButtonListener sendButtonListener) {

    }

    @Override
    public void addListSelectionListener(ListSelectionListener listSelectionListener) {

    }

    @Override
    public void notifyBackButtonListener() {

    }

    @Override
    public void notifySendButtonListener() {

    }

    @Override
    public void notifyListSelectionListener() {

    }

    @Override
    public void goToSentUI() {

    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public OrganizerMainUI goToOrganizerMainUI() {
        return null;
    }

    @Override
    public JList getUsersList() {
        return null;
    }
}
