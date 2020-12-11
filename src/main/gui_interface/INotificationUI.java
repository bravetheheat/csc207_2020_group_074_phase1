package main.gui_interface;

import main.gui.RegisterUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;

public interface INotificationUI {

    void addBackButtonListener(BackButtonListener listener);
    void notifyListenerOnBackButtonClicked();
    void addMessage(JLabel jLabel, String message);
    RegisterUI goToRegister();
}
