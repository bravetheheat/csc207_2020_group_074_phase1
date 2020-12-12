package main.gui_interface;

import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;

public interface ISpeakerMainUI extends  IView {

    void addLogoutButtonListener(LogoutButtonListener listener);

    void addMessageButtonListener(MessageButtonListener listener);

    void addInboxButtonListener(InboxButtonListener listener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnMessageButtonClicked();

    void notifyListenerOnInboxButtonClicked();

    ILandingUI goToLandingUI();

    ISpeakerMessageUI goToSpeakerMessageUI();

    IInboxUI goToInboxUI();
}
