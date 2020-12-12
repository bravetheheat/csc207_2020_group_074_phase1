package main.gui_interface;

import main.controllers.ProgramController;
import main.gui.AttendeeMainUI;
import main.gui.MessageSentUI;
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

    void goToSentUI();

    String getMessage();

    AttendeeMainUI goToAttendeeMainUI();

    JList getUsersList();
}
