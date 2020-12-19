package main.gui;

import main.gui_interface.IAdminUserManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteUserButtonListener;
import main.guilisteners.GetUserListButtonListener;
import main.guilisteners.UserManagementButtonListener;

import javax.swing.*;

public class AdminUserManagementUI extends UserManagementUI implements IAdminUserManagementUI {

    private JPanel panel1;
    private JTextField userTypeField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JTextField userNameRemovedField;
    private JButton deleteButton;
    private JButton getUserListButton;
    private JButton backButton;
    private DeleteUserButtonListener deleteUserButtonListener;
    private BackButtonListener backButtonListener;
    private UserManagementButtonListener userManagementButtonListener;
    private GetUserListButtonListener getUserListButtonListener;

    public AdminUserManagementUI() {
        this.setTitle("Admin User Management");
        this.setSize(600, 500);
        this.setContentPane(panel1);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        getUserListButton.addActionListener(e -> notifyListenerOnGetUserListButtonClicked());
        deleteButton.addActionListener(e -> notifyListenerOnDeleteUserButtonClicked());
    }

    public String getUserType() {
        return userTypeField.getText();
    }

    public String getUserName() {
        return usernameField.getText();
    }

    public String getPwd() {
        return String.valueOf(passwordField.getPassword());
    }

    public String getDeletedUser() {
        return userNameRemovedField.getText();
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void addUserManagementButtonListener(UserManagementButtonListener listener) {
        userManagementButtonListener = listener;
    }

    @Override
    public void addGetUserListButtonListener(GetUserListButtonListener listener) {
        getUserListButtonListener = listener;
    }

    public void addDeleteUserButtonListener(DeleteUserButtonListener listener) {
        deleteUserButtonListener = listener;
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

    public void notifyListenerOnDeleteUserButtonClicked() {
        deleteUserButtonListener.onDeleteUserButtonClicked();
    }

    public void deleteUserSuccessful() {
        JOptionPane.showMessageDialog(this,
                "User has successfully been deleted!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteUserError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public AdminMainUI goToAdminMainUI() {
        AdminMainUI adminMainUI = new AdminMainUI();
        this.dispose();
        return adminMainUI;
    }


}