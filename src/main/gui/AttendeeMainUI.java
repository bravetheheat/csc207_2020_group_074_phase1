package main.gui;

import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public class AttendeeMainUI extends JFrame implements IAttendeeMainUI {
    private JPanel panel1;
    private JButton logOutButton;
    private LogoutButtonListener logoutButtonListener;
    private JButton registerForEventsButton;
    private RegisterForEventsButtonListener registerForEventsButtonListener;
    private JButton messageButton;
    private MessageButtonListener messageButtonListener;
    private JButton inboxButton;
    private InboxButtonListener inboxButtonListener;

    public AttendeeMainUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.logOutButton.addActionListener(e -> notifyListenerOnLogoutButtonClicked());
        this.registerForEventsButton.addActionListener(e -> notifyListenerOnRegisterForEventsButtonClicked());
        this.messageButton.addActionListener(e -> notifyListenerOnMessageButtonClicked());
        this.inboxButton.addActionListener(e -> notifyListenerOnInboxButtonClicked());
    }

    public void addLogoutButtonListener(LogoutButtonListener listener) {
        this.logoutButtonListener = listener;
    }

    public void addRegisterForEventsButtonListener(RegisterForEventsButtonListener listener) {
        this.registerForEventsButtonListener = listener;
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

    public void notifyListenerOnRegisterForEventsButtonClicked() {
        this.registerForEventsButtonListener.onRegisterForEventsButtonClicked();
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

    public IEventSignUpUI goToEventSignUpUI() {
        EventSignUpUI eventSignUpUI = new EventSignUpUI();
        this.dispose();
        return eventSignUpUI;
    }

    public IAttendeeMessageUI goToAttendeeMessageUI(ArrayList<String> users) {
        AttendeeMessageUI attendeeMessageUI = new AttendeeMessageUI(users);
        this.dispose();
        return attendeeMessageUI;
    }

    public IInboxUI goToInboxUI() {
        InboxUI inboxUI = new InboxUI();
        this.dispose();
        return inboxUI;
    }
}
