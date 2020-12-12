package main.gui_interface;

import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ISpeakerMainUI extends  IView {

    void addLogoutButtonListener(LogoutButtonListener listener);

    void addMessageButtonListener(MessageButtonListener listener);

    void addInboxButtonListener(InboxButtonListener listener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnMessageButtonClicked();

    void notifyListenerOnInboxButtonClicked();

    ILandingUI goToLandingUI();

    ISpeakerMessageUI goToSpeakerMessageUI(ArrayList<String> users);

    IInboxUI goToInboxUI();
}
