package main.gui;

import main.gui_interface.*;
import main.guilisteners.*;

import javax.swing.*;
import java.util.ArrayList;

public class AdminMainUI extends JFrame implements IAdminMainUI {
    private JPanel panel1;
    private JButton logOutButton;
    private LogoutButtonListener logoutButtonListener;
    private JButton userManagementButton;
    private UserManagementButtonListener userManagementButtonListener;
    private JButton registeredEventsButton;
    private RegisteredEventsButtonListener registeredEventsButtonListener;
    private JButton messageButton;
    private MessageButtonListener messageButtonListener;
    private JButton inboxButton;
    private JButton exportEvents;
    private ExportEventsButtonListener exportEventsButtonListener;
    private InboxButtonListener inboxButtonListener;
//    private JButton dataManagementButton;
    private DataManagementButtonListener dataManagementButtonListener;

    public AdminMainUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.logOutButton.addActionListener(e -> notifyListenerOnLogoutButtonClicked());
        this.userManagementButton.addActionListener(e -> notifyListenerOnUserManagementButtonClicked());
        this.registeredEventsButton.addActionListener(e -> notifyListenerOnRegisteredEventsButtonClicked());
        this.messageButton.addActionListener(e -> notifyListenerOnMessageButtonClicked());
        this.inboxButton.addActionListener(e -> notifyListenerOnInboxButtonClicked());
        this.exportEvents.addActionListener(e -> notifyListenerOnExportEventsButtonClicked());
//        this.dataManagementButton.addActionListener(e -> notifyListenerOnDataManagementButtonClicked());
    }

    @Override
    public void addLogoutButtonListener(LogoutButtonListener listener) {
        this.logoutButtonListener = listener;
    }

    @Override
    public void addUserManagementButtonListener(UserManagementButtonListener listener) {
        this.userManagementButtonListener = listener;
    }

    @Override
    public void addRegisteredEventsButtonListener(RegisteredEventsButtonListener listener) {
        this.registeredEventsButtonListener = listener;
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
    public void addExportEventsButtonListener(ExportEventsButtonListener listener) {
        this.exportEventsButtonListener = listener;
    }

//    @Override
//    public void addDataManagementButtonListener(DataManagementButtonListener listener) {
//        this.dataManagementButtonListener = listener;
//    }

    @Override
    public void notifyListenerOnLogoutButtonClicked() {
        this.logoutButtonListener.onLogoutButtonClicked();
    }

    @Override
    public void notifyListenerOnUserManagementButtonClicked() {
        this.userManagementButtonListener.onUserManagementButtonClicked();
    }

    @Override
    public void notifyListenerOnRegisteredEventsButtonClicked() {
        this.registeredEventsButtonListener.onRegisteredEventsButtonClicked();
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
    public void notifyListenerOnExportEventsButtonClicked() {
        this.exportEventsButtonListener.onExportEventsButtonClicked();
    }

//    @Override
//    public void notifyListenerOnDataManagementButtonClicked() {
//        this.dataManagementButtonListener.onDataManagementButtonClicked();
//    }

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
    public IGatewayUI goToGatewayUI() {
        GatewayUI gatewayUI = new GatewayUI();
        this.dispose();
        return gatewayUI;
    }

    @Override
    public IAdminUserManagementUI goToAdminUserManagementUI() {
        AdminUserManagementUI adminUserManagementUI = new AdminUserManagementUI();
        this.dispose();
        return adminUserManagementUI;
    }

    @Override
    public IOrganizerMessageUI goToOrganizerMessageUI(ArrayList<String> userInfo) {
        OrganizerMessageUI organizerMessageUI = new OrganizerMessageUI(userInfo);
        this.dispose();
        return organizerMessageUI;
    }

    @Override
    public IInboxUI goToInboxUI(ArrayList<String> messages) {
        InboxUI inboxUI = new InboxUI(messages);
        this.dispose();
        return inboxUI;
    }

    @Override
    public IAdminExportToHTMLUI goToAdminExportToHTMLUI(ArrayList<String> userInfo) {
        AdminExportToHTMLUI adminExportToHTMLUI = new AdminExportToHTMLUI(userInfo);
        this.dispose();
        return adminExportToHTMLUI;
    }
}
