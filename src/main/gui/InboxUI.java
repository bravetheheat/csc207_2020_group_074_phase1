package main.gui;

import main.controllers.ProgramController;
import main.entities.Inbox;
import main.gui_interface.IInboxUI;

import javax.swing.*;

public class InboxUI extends JFrame implements IInboxUI {
    private JButton backButton;
    private JPanel panel1;
    private JList list1;

    public InboxUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
