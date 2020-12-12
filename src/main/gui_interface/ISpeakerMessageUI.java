package main.gui_interface;

import main.guilisteners.LogoutButtonListener;
import main.guilisteners.SendButtonListener;

public interface ISpeakerMessageUI extends IView {

    void addLogoutButtonListener(LogoutButtonListener logoutButtonListener);

    void addSendButtonListener(SendButtonListener sendButtonListener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnSendButtonClicked();

}
