package main.gui;

import main.gui_interface.ISpeakerMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.BroadcastButtonListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The messaging screen of the application for a Speaker type account.
 *
 * @author Yi Tao Li
 */
public class SpeakerMessageUI extends JFrame implements ISpeakerMessageUI {
    ArrayList<String> events;
    private JPanel panel1;
    private JList userList;
    private JList eventList;
    private JButton broadcastButton;
    private BroadcastButtonListener broadcastButtonListener;
    private JTextField messageText;
    private JButton sendButton;
    private SendButtonListener sendButtonListener;
    private JButton backButton;
    private JScrollPane userListScroller;
    private JScrollPane eventListScroller;
    private BackButtonListener backButtonListener;

    public SpeakerMessageUI(ArrayList<String> users, ArrayList<String> events) {
        this.events = events;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyBackButtonListener());
        this.sendButton.addActionListener(e -> notifySendButtonListener());
        this.broadcastButton.addActionListener(e -> notifyBroadcastButtonListener());

        DefaultListModel<String> usersList = new DefaultListModel<>();
        for (String user:users) {
            usersList.addElement(user);
        }
        this.userList = new JList<>(usersList);
        this.userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.userList.setLayoutOrientation(JList.VERTICAL);
        this.userList.setVisibleRowCount(-1);
        this.userListScroller.setViewportView(this.userList);

        DefaultListModel<String> eventsList = new DefaultListModel<>();
        for (String event:events) {
            eventsList.addElement(event);
        }
        this.eventList = new JList<>(eventsList);
        this.eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.eventList.setLayoutOrientation(JList.VERTICAL);
        this.eventList.setVisibleRowCount(-1);
        this.eventListScroller.setViewportView(this.eventList);
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
    public void addBroadcastButtonListener(BroadcastButtonListener broadcastButtonListener) {
        this.broadcastButtonListener = broadcastButtonListener;
    }

    @Override
    public void notifyBroadcastButtonListener() {
        this.broadcastButtonListener.onBroadcastButtonClicked();
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
    public String getMessage() {
        return messageText.getText();
    }

    @Override
    public SpeakerMainUI goToSpeakerMainUI() {
        SpeakerMainUI speakerMainUI = new SpeakerMainUI(this.events);
        this.dispose();
        return speakerMainUI;
    }

    @Override
    public JList getUsersList() {
        return this.userList;
    }

    @Override
    public JList getEventsList() {
        return this.eventList;
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
