package main.gui;

import main.gui_interface.IAttendeeMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;

/**
 * The message screen for an Attendee type account.
 *
 * @author Yi Tao Li
 */
public class AttendeeMessageUI extends JFrame implements IAttendeeMessageUI {
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private JPanel panel1;
    private JList<String> userList;
    private ListSelectionListener listSelectionListener;
    private JTextArea messageText;
    private JButton sendButton;
    private JScrollPane listScroller;
    private SendButtonListener sendButtonListener;

    public AttendeeMessageUI(ArrayList<String> users) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyBackButtonListener());
        this.sendButton.addActionListener(e -> notifySendButtonListener());
        DefaultListModel<String> usersList = new DefaultListModel<>();
        for (String user:users) {
            usersList.addElement(user);
        }
        this.userList = new JList<>(usersList);
        this.userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.userList.setLayoutOrientation(JList.VERTICAL);
        this.userList.setVisibleRowCount(-1);
        this.listScroller.setViewportView(this.userList);
        this.userList.addListSelectionListener(e -> notifyListSelectionListener());
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
    public void addListSelectionListener(ListSelectionListener listSelectionListener) {
        this.listSelectionListener = listSelectionListener;
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
    public void notifyListSelectionListener() {
        ListSelectionEvent e = new ListSelectionEvent(this.userList, this.userList.getMinSelectionIndex(),
                this.userList.getMaxSelectionIndex(), false);
        this.listSelectionListener.valueChanged(e);
    }

    @Override
    public String getMessage() {
        return messageText.getText();
    }

    @Override
    public AttendeeMainUI goToAttendeeMainUI() {
        AttendeeMainUI attendeeMainUI = new AttendeeMainUI();
        this.dispose();
        return attendeeMainUI;
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
