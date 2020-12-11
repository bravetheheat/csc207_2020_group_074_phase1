package main.gui_interface;

import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

public interface IAttendeeMainUI extends IView {

    void addLogoutButtonListener(LogoutButtonListener listener);

    void addRegisterForEventsButtonListener(RegisterForEventsButtonListener listener);

    void addMessageButtonListener(MessageButtonListener listener);

    void addInboxButtonListener(InboxButtonListener listener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnRegisterForEventsButtonClicked();

    void notifyListenerOnMessageButtonClicked();

    void notifyListenerOnInboxButtonClicked();

    ILandingUI goToLandingUI();

    IEventSignUpUI goToEventSignUpUI();

    IAttendeeMessageUI goToAttendeeMessageUI();

    IInboxUI goToInboxUI();
}
