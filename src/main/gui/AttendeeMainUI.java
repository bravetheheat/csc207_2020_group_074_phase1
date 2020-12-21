package main.gui;

import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The home screen of the application for an Attendee type account.
 *
 * @author Yi Tao Li
 */
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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    @Override
    public void addLogoutButtonListener(LogoutButtonListener listener) {
        this.logoutButtonListener = listener;
    }

    @Override
    public void addRegisterForEventsButtonListener(RegisterForEventsButtonListener listener) {
        this.registerForEventsButtonListener = listener;
    }

    @Override
    public void addMessageButtonListener(MessageButtonListener listener) {
        this.messageButtonListener = listener;
    }

    @Override
    public void addInboxButtonListener(InboxButtonListener listener) {
        this.inboxButtonListener = listener;
    }

    @Override
    public void notifyListenerOnLogoutButtonClicked() {
        this.logoutButtonListener.onLogoutButtonClicked();
    }

    @Override
    public void notifyListenerOnRegisterForEventsButtonClicked() {
        this.registerForEventsButtonListener.onRegisterForEventsButtonClicked();
    }

    @Override
    public void notifyListenerOnMessageButtonClicked() {
        this.messageButtonListener.onMessageButtonClicked();
    }

    @Override
    public void notifyListenerOnInboxButtonClicked() {
        this.inboxButtonListener.onInboxButtonClicked();
    }

    @Override
    public ILandingUI goToLandingUI() {
        LandingUI landingUI = new LandingUI();
        this.dispose();
        return landingUI;
    }

    @Override
    public IEventSignUpUI goToEventSignUpUI() {
        EventSignUpUI eventSignUpUI = new EventSignUpUI();
        this.dispose();
        return eventSignUpUI;
    }

    @Override
    public IAttendeeMessageUI goToAttendeeMessageUI(ArrayList<String> users) {
        AttendeeMessageUI attendeeMessageUI = new AttendeeMessageUI(users);
        this.dispose();
        return attendeeMessageUI;
    }

    @Override
    public IInboxUI goToInboxUI(ArrayList<String> messages) {
        InboxUI inboxUI = new InboxUI(messages);
        this.dispose();
        return inboxUI;
    }
}
