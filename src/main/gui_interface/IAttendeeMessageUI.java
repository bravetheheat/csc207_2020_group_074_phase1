package main.gui_interface;

import main.gui.AttendeeMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;

public interface IAttendeeMessageUI extends IView {

    void addBackButtonListener(BackButtonListener backButtonListener);

    void addSendButtonListener(SendButtonListener sendButtonListener);

    void addListSelectionListener(ListSelectionListener listSelectionListener);

    void notifyBackButtonListener();

    void notifySendButtonListener();

    void notifyListSelectionListener();

    String getMessage();

    AttendeeMainUI goToAttendeeMainUI();

    public JButton getSendButton();

    public JList getUsersList();
}
