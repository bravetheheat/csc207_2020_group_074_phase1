package main.gui;

import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpeakerMainUI extends JFrame implements ISpeakerMainUI {
    private JPanel panel1;
    private JButton logOutButton;
    private LogoutButtonListener logoutButtonListener;
    private JButton messageButton;
    private MessageButtonListener messageButtonListener;
    private JButton inboxButton;
    private InboxButtonListener inboxButtonListener;
    private JList TalksList;
    private JScrollPane listScroller;

    public SpeakerMainUI(ArrayList<String> events) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.logOutButton.addActionListener(e -> notifyListenerOnLogoutButtonClicked());
        this.messageButton.addActionListener(e -> notifyListenerOnMessageButtonClicked());
        this.inboxButton.addActionListener(e -> notifyListenerOnInboxButtonClicked());
        this.TalksList = new JList(events.toArray());
        this.TalksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.TalksList.setLayoutOrientation(JList.VERTICAL);
        this.TalksList.setVisibleRowCount(-1);
        this.listScroller = new JScrollPane(this.TalksList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    public void addLogoutButtonListener(LogoutButtonListener listener) {
        this.logoutButtonListener = listener;
    }

    public void addMessageButtonListener(MessageButtonListener listener) {
        this.messageButtonListener = listener;
    }

    public void addInboxButtonListener(InboxButtonListener listener) {
        this.inboxButtonListener = listener;
    }

    public void notifyListenerOnLogoutButtonClicked() {
        this.logoutButtonListener.onLogoutButtonClicked();
    }

    public void notifyListenerOnMessageButtonClicked() {
        this.messageButtonListener.onMessageButtonClicked();
    }

    public void notifyListenerOnInboxButtonClicked() {
        this.inboxButtonListener.onInboxButtonClicked();
    }

    public ILandingUI goToLandingUI() {
        LandingUI landingUI = new LandingUI();
        this.dispose();
        return landingUI;
    }

    public ISpeakerMessageUI goToSpeakerMessageUI() {
        SpeakerMessageUI speakerMessageUI = new SpeakerMessageUI();
        this.dispose();
        return speakerMessageUI;
    }

    public IInboxUI goToInboxUI() {
        InboxUI inboxUI = new InboxUI();
        this.dispose();
        return inboxUI;
    }

}
