package main.gui_interface;

import main.gui.EventSignUpUI;
import main.guilisteners.*;

import java.util.ArrayList;

public interface IOrganizerMainUI extends IView {

    void addLogoutButtonListener(LogoutButtonListener listener);

    void addUserManagementButtonListener(UserManagementButtonListener listener);

    void addManageEventRoomButtonListener(ManageEventRoomButtonListener listener);

    void addRegisteredEventsButtonListener(RegisteredEventsButtonListener listener);

    void addMessageButtonListener(MessageButtonListener listener);

    void addInboxButtonListener(InboxButtonListener listener);

    void addDataManagementButtonListener(DataManagementButtonListener listener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnUserManagementButtonClicked();

    void notifyListenerOnManageEventRoomButtonClicked();

    void notifyListenerOnRegisteredEventsButtonClicked();

    void notifyListenerOnMessageButtonClicked();

    void notifyListenerOnInboxButtonClicked();

    void notifyListenerOnDataManagementButtonClicked();

    ILandingUI goToLandingUI();

    IEventSignUpUI goToEventSignUpUI();

    IEventsManagementUI goToEventsManagementUI();

    IGatewayUI goToGatewayUI();

    IUserManagementUI goToUserManagementUI();

    IOrganizerMessageUI goToOrganizerMessageUI(ArrayList<String> userInfo);

    IInboxUI goToInboxUI(ArrayList<String> messages);

}
