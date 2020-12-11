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

    void createEventList();

    ILandingUI goToLandingUI();

    ISpeakerMessageUI goToSpeakerMessageUI();

    IInboxUI goToInboxUI();
}
