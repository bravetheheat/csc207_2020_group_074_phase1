package main.gui;

import main.gui_interface.IInboxUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public class InboxUI extends JFrame implements IInboxUI {
    private ArrayList<String> events;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private JPanel panel1;
    private JScrollPane listScroller;
    private JList messageList;

    public InboxUI(ArrayList<String> messages) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Inbox");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
        DefaultListModel<String> messagesList = new DefaultListModel<>();
        for (String message:messages) {
            messagesList.addElement(message);
        }
        this.messageList = new JList<>(messagesList);
        this.messageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.messageList.setLayoutOrientation(JList.VERTICAL);
        this.messageList.setVisibleRowCount(-1);
        this.listScroller.setViewportView(this.messageList);
    }

    public InboxUI(ArrayList<String> messages, ArrayList<String> events) {
        this.events = events;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
        DefaultListModel<String> messagesList = new DefaultListModel<>();
        for (String message:messages) {
            messagesList.addElement(message);
        }
        this.messageList = new JList<>(messagesList);
        this.messageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.messageList.setLayoutOrientation(JList.VERTICAL);
        this.messageList.setVisibleRowCount(-1);
        this.listScroller.setViewportView(this.messageList);
    }

    @Override
    public void notifyListenerOnBackButtonClicked() {
        this.backButtonListener.onBackButtonClicked();
    }

    @Override
    public void addBackButtonListener(BackButtonListener backButtonListener) {
        this.backButtonListener = backButtonListener;
    }

    @Override
    public AttendeeMainUI goToAttendeeMainUI() {
        AttendeeMainUI attendeeMainUI = new AttendeeMainUI();
        this.dispose();
        return attendeeMainUI;
    }

    @Override
    public OrganizerMainUI goToOrganizerMainUI() {
        OrganizerMainUI organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    @Override
    public SpeakerMainUI goToSpeakerMainUI() {
        SpeakerMainUI speakerMainUI = new SpeakerMainUI(this.events);
        this.dispose();
        return speakerMainUI;
    }

    @Override
    public AdminMainUI goToAdminMainUI() {
        AdminMainUI adminMainUI = new AdminMainUI();
        this.dispose();
        return adminMainUI;
    }
}
