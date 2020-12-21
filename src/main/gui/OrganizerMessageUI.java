package main.gui;

import main.gui_interface.IOrganizerMessageUI;
import main.guilisteners.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The messaging screen of the application for an Organizer or Admin type account.
 *
 * @author Yi Tao Li
 */
public class OrganizerMessageUI extends JFrame implements IOrganizerMessageUI  {
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton selectedUsersButton;
    private SendButtonListener sendButtonListener;
    private JButton everyoneButton;
    private EveryoneButtonListener everyoneButtonListener;
    private JButton allAttendeesButton;
    private AllAttendeesButtonListener allAttendeesButtonListener;
    private JButton allSpeakersButton;
    private AllSpeakersButtonListener allSpeakersButtonListener;
    private JScrollPane listScroller;
    private JList userList;
    private JTextField textField1;

    public OrganizerMessageUI(ArrayList<String> userInfo) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyBackButtonListener());
        this.selectedUsersButton.addActionListener(e -> notifySendButtonListener());
        this.everyoneButton.addActionListener(e -> notifyEveryoneButtonListener());
        this.allAttendeesButton.addActionListener(e -> notifyAllAttendeesButtonListener());
        this.allSpeakersButton.addActionListener(e -> notifyAllSpeakersButtonListener());
        DefaultListModel<String> usersList = new DefaultListModel<>();
        for (String user:userInfo) {
            usersList.addElement(user);
        }
        this.userList = new JList<>(usersList);
        this.userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.userList.setLayoutOrientation(JList.VERTICAL);
        this.userList.setVisibleRowCount(-1);
        this.listScroller.setViewportView(this.userList);
    }

    @Override
    public void addBackButtonListener(BackButtonListener backButtonListener) {
        this.backButtonListener = backButtonListener;
    }

    @Override
    public void addSendButtonListener(SendButtonListener sendButtonListener) {
        this.sendButtonListener = sendButtonListener;
    }

    @Override
    public void addEveryoneButtonListener(EveryoneButtonListener everyoneButtonListener) {
        this.everyoneButtonListener = everyoneButtonListener;
    }

    @Override
    public void addAllAttendeesButtonListener(AllAttendeesButtonListener allAttendeesButtonListener) {
        this.allAttendeesButtonListener = allAttendeesButtonListener;
    }

    @Override
    public void addAllSpeakersButtonListener(AllSpeakersButtonListener allSpeakersButtonListener) {
        this.allSpeakersButtonListener = allSpeakersButtonListener;
    }

    @Override
    public void notifyBackButtonListener() {
        this.backButtonListener.onBackButtonClicked();
    }

    @Override
    public void notifySendButtonListener() {
        this.sendButtonListener.onSendButtonClicked();
    }

    @Override
    public void notifyEveryoneButtonListener() {
        this.everyoneButtonListener.onEveryoneButtonClicked();
    }

    @Override
    public void notifyAllAttendeesButtonListener() {
        this.allAttendeesButtonListener.onAllAttendeesButtonClicked();
    }

    @Override
    public void notifyAllSpeakersButtonListener() {
        this.allSpeakersButtonListener.onAllSpeakersButtonClicked();
    }

    @Override
    public String getMessage() {
        return this.textField1.getText();
    }

    @Override
    public OrganizerMainUI goToOrganizerMainUI() {
        OrganizerMainUI organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    @Override
    public AdminMainUI goToAdminMainUI() {
        AdminMainUI adminMainUI = new AdminMainUI();
        this.dispose();
        return adminMainUI;
    }

    @Override
    public JList getUsersList() {
        return this.userList;
    }

    public void sendMessageSuccessful() {
        JOptionPane.showMessageDialog(this,
                "Message sent!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void sendMessageError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
