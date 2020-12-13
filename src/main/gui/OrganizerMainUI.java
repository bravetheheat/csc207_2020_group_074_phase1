package main.gui;

import main.gui_interface.*;
import main.guilisteners.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrganizerMainUI extends JFrame implements IOrganizerMainUI {
    private JPanel panel1;
    private JButton logOutButton;
    private JButton userManagementButton;
    private JButton manageEventRoomButton;
    private JButton registeredEventsButton;
    private JButton messageButton;
    private JButton inboxButton;
    private JButton dataManagementButton;
    private LogoutButtonListener logoutButtonListener;
    private UserManagementButtonListener userManagementButtonListener;
    private ManageEventRoomButtonListener manageEventRoomButtonListener;
    private RegisteredEventsButtonListener registeredEventsButtonListener;
    private MessageButtonListener messageButtonListener;
    private InboxButtonListener inboxButtonListener;
    private DataManagementButtonListener dataManagementButtonListener;

    public OrganizerMainUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.logOutButton.addActionListener(e -> notifyListenerOnLogoutButtonClicked());
        this.userManagementButton.addActionListener(e -> notifyListenerOnUserManagementButtonClicked());
        this.manageEventRoomButton.addActionListener(e -> notifyListenerOnManageEventRoomButtonClicked());
        this.registeredEventsButton.addActionListener(e -> notifyListenerOnRegisteredEventsButtonClicked());
        this.messageButton.addActionListener(e -> notifyListenerOnMessageButtonClicked());
        this.inboxButton.addActionListener(e -> notifyListenerOnInboxButtonClicked());
        this.dataManagementButton.addActionListener(e -> notifyListenerOnDataManagementButtonClicked());
    }

    public void addLogoutButtonListener(LogoutButtonListener listener) {
        this.logoutButtonListener = listener;
    }

    public void addUserManagementButtonListener(UserManagementButtonListener listener) {
        this.userManagementButtonListener = listener;
    }

    public void addManageEventRoomButtonListener(ManageEventRoomButtonListener listener) {
        this.manageEventRoomButtonListener = listener;
    }

    public void addRegisteredEventsButtonListener(RegisteredEventsButtonListener listener) {
        this.registeredEventsButtonListener = listener;
    }

    public void addMessageButtonListener(MessageButtonListener listener) {
        this.messageButtonListener = listener;
    }

    public void addInboxButtonListener(InboxButtonListener listener) {
        this.inboxButtonListener = listener;
    }

    public void addDataManagementButtonListener(DataManagementButtonListener listener) {
        this.dataManagementButtonListener = listener;
    }

    public void notifyListenerOnLogoutButtonClicked() {
        this.logoutButtonListener.onLogoutButtonClicked();
    }

    public void notifyListenerOnUserManagementButtonClicked() {
        this.userManagementButtonListener.onUserManagementButtonClicked();
    }

    public void notifyListenerOnManageEventRoomButtonClicked() {
        this.manageEventRoomButtonListener.onManageEventRoomButtonClicked();
    }

    public void notifyListenerOnRegisteredEventsButtonClicked() {
        this.registeredEventsButtonListener.onRegisteredEventsButtonClicked();
    }

    public void notifyListenerOnMessageButtonClicked() {
        this.messageButtonListener.onMessageButtonClicked();
    }

    public void notifyListenerOnInboxButtonClicked() {
        this.inboxButtonListener.onInboxButtonClicked();
    }

    public void notifyListenerOnDataManagementButtonClicked() {
        this.dataManagementButtonListener.onDataManagementButtonClicked();
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
    public IEventsManagementUI goToEventsManagementUI() {
        EventsManagementUI eventsManagementUI = new EventsManagementUI();
        this.dispose();
        return eventsManagementUI;
    }

    @Override
    public IGatewayUI goToGatewayUI() {
        GatewayUI gatewayUI = new GatewayUI();
        this.dispose();
        return gatewayUI;
    }

    @Override
    public IUserManagementUI goToUserManagementUI() {
        UserManagementUI userManagementUI = new UserManagementUI();
        this.dispose();
        return userManagementUI;
    }

    @Override
    public IOrganizerMessageUI goToOrganizerMessageUI(ArrayList<String> userInfo) {
        OrganizerMessageUI organizerMessageUI = new OrganizerMessageUI(userInfo);
        this.dispose();
        return organizerMessageUI;
    }

    @Override
    public IInboxUI goToInboxUI() {
        InboxUI inboxUI = new InboxUI();
        this.dispose();
        return inboxUI;
    }
}
