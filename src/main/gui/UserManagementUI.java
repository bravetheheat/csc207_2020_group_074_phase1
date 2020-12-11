package main.gui;

import main.gui_interface.IUserManagementUI;

import javax.swing.*;

public class UserManagementUI extends JFrame implements IUserManagementUI {

    private JButton backButton;
    private JPanel panel1;
    private JButton confirmButton;
    private JTextField textField1;
    private JTextField textField2;
    private JList UserList;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public UserManagementUI() {
        this.setTitle("User Management");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        confirmButton.addActionListener(e -> {

        });
    }
}
