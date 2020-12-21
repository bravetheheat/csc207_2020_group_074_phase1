package main.gui;

import main.gui_interface.IAdminExportToHTMLUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ExportButtonListener;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The export events schedule to HTML screen for an Admin type account.
 *
 * @author Yi Tao Li
 */
public class AdminExportToHTMLUI extends JFrame implements IAdminExportToHTMLUI {
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private JPanel panel1;
    private JButton exportUsersEventsButton;
    private ExportButtonListener exportButtonListener;
    private JScrollPane listScroller;
    private JList<String> userList;

    public AdminExportToHTMLUI(ArrayList<String> users) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Program X");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.backButton.addActionListener(e -> notifyBackButtonListener());
        this.exportUsersEventsButton.addActionListener(e -> notifyExportButtonListener());
        DefaultListModel<String> usersList = new DefaultListModel<>();
        for (String user:users) {
            usersList.addElement(user);
        }
        this.userList = new JList<>(usersList);
        this.userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.userList.setLayoutOrientation(JList.VERTICAL);
        this.userList.setVisibleRowCount(-1);
        this.listScroller.setViewportView(this.userList);
    }

    @Override
    public void addBackButtonListener(BackButtonListener backButtonListener) {
        this.backButtonListener = backButtonListener;
    }

    @Override
    public void addExportButtonListener(ExportButtonListener exportButtonListener) {
        this.exportButtonListener = exportButtonListener;
    }

    @Override
    public void notifyBackButtonListener() {
        this.backButtonListener.onBackButtonClicked();
    }

    @Override
    public void notifyExportButtonListener() {
        this.exportButtonListener.onExportButtonClicked();
    }

    @Override
    public AdminMainUI goToAdminMainUI() {
        AdminMainUI adminMainUI = new AdminMainUI();
        this.dispose();
        return adminMainUI;
    }

    @Override
    public JList getUsersList() {
        return this.userList;
    }

    @Override
    public void successfullyExported() {
        JOptionPane.showMessageDialog(this,
                "The user's event schedule has been exported as an HTML file to the program files.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void noEventsMessage() {
        JOptionPane.showMessageDialog(this,
                "This user has not signed up for an event.",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
