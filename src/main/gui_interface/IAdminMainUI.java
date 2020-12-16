package main.gui_interface;

import main.guilisteners.*;

import java.util.ArrayList;

public interface IAdminMainUI extends IView {

    void addLogoutButtonListener(LogoutButtonListener listener);

    void addUserManagementButtonListener(UserManagementButtonListener listener);

    void addRegisteredEventsButtonListener(RegisteredEventsButtonListener listener);

    void addMessageButtonListener(MessageButtonListener listener);

    void addInboxButtonListener(InboxButtonListener listener);

    void addDataManagementButtonListener(DataManagementButtonListener listener);

    void notifyListenerOnLogoutButtonClicked();

    void notifyListenerOnUserManagementButtonClicked();

    void notifyListenerOnRegisteredEventsButtonClicked();

    void notifyListenerOnMessageButtonClicked();

    void notifyListenerOnInboxButtonClicked();

    void notifyListenerOnDataManagementButtonClicked();

    ILandingUI goToLandingUI();

    IEventSignUpUI goToEventSignUpUI();

    IGatewayUI goToGatewayUI();

    IAdminUserManagementUI goToAdminUserManagementUI();

    IOrganizerMessageUI goToOrganizerMessageUI(ArrayList<String> userInfo);

    IInboxUI goToInboxUI();
}
