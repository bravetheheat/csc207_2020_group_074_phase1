package main.gui;

import main.gui_interface.IUserManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.GetUserListButtonListener;
import main.guilisteners.UserManagementButtonListener;

import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")

public class UserManagementUI extends JFrame implements IUserManagementUI {

    private JButton backButton;
    private JPanel panel1;
    private JButton confirmButton;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel userTypeLabel;
    private JPasswordField passwordField;
    private JTextField userTypeField;
    private JButton getUserListButton;
    private ArrayList<String> usernameList;
    private BackButtonListener backButtonListener;
    private GetUserListButtonListener getUserListButtonListener;
    private UserManagementButtonListener userManagementButtonListener;
    private OrganizerMainUI organizerMainUI;
    private ListOfUsersUI listOfUsersUI;

    public UserManagementUI() {
        this.setTitle("User Management");
        this.setSize(600, 500);
        this.setContentPane(panel1);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        getUserListButton.addActionListener(e -> notifyListenerOnGetUserListButtonClicked());
    }

    public void addBackButtonListener(BackButtonListener listener) {
        this.backButtonListener = listener;
    }

    public void addUserManagementButtonListener(UserManagementButtonListener listener) {
        this.userManagementButtonListener = listener;
    }

    public void addGetUserListButtonListener(GetUserListButtonListener listener) {
        this.getUserListButtonListener = listener;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void notifyListenerOnConfirmButtonClicked() {
        userManagementButtonListener.onUserManagementButtonClicked();
    }

    public void notifyListenerOnGetUserListButtonClicked() {
        getUserListButtonListener.onGetUserListButtonClicked();
    }

    public String getUserType() {
        return userTypeField.getText();
    }

    public String getUserName() {
        return usernameField.getText();
    }

    public String getPwd() {
        return String.valueOf((passwordField.getPassword()));
    }

    public void registerNewUserSuccessful() {
        JOptionPane.showMessageDialog(this,
                "User has successfully been created!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void registerNewUserError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }


    public OrganizerMainUI goToOrganizerMainUI() {
        organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    public ListOfUsersUI goToListOfUsersUI(ArrayList<String> listOfUserInfo) {
        listOfUsersUI = new ListOfUsersUI(listOfUserInfo);
        this.dispose();
        return listOfUsersUI;
    }
}
