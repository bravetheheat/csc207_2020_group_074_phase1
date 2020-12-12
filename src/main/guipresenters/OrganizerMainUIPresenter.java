package main.guipresenters;

import com.sun.org.apache.xpath.internal.operations.Or;
import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gateways.Gateway;
import main.gui.*;
import main.gui_interface.*;
import main.guilisteners.*;
import main.usecases.UsersManager;

import java.util.ArrayList;

public class OrganizerMainUIPresenter implements LogoutButtonListener, UserManagementButtonListener,
        ManageEventRoomButtonListener, RegisteredEventsButtonListener, MessageButtonListener, InboxButtonListener,
        DataManagementButtonListener {

    private IOrganizerMainUI iOrganizerMainUI;
    private ProgramController programController;
    private AuthController authController;

    public OrganizerMainUIPresenter(IOrganizerMainUI iOrganizerMainUI, ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iOrganizerMainUI = iOrganizerMainUI;
        this.iOrganizerMainUI.addLogoutButtonListener(this);
        this.iOrganizerMainUI.addUserManagementButtonListener(this);
        this.iOrganizerMainUI.addManageEventRoomButtonListener(this);
        this.iOrganizerMainUI.addRegisteredEventsButtonListener(this);
        this.iOrganizerMainUI.addMessageButtonListener(this);
        this.iOrganizerMainUI.addInboxButtonListener(this);
        this.iOrganizerMainUI.addDataManagementButtonListener(this);
    }

    @Override
    public void onLogoutButtonClicked() {
        this.authController.logout();
        ILandingUI ilandingUI = iOrganizerMainUI.goToLandingUI();
        new LandingUIPresenter(ilandingUI, this.programController);
    }

    @Override
    public void onDataManagementButtonClicked() {
        IGatewayUI iGatewayUI = iOrganizerMainUI.goToGatewayUI();
        new GatewayUIPresenter(iGatewayUI, this.programController);
    }

    @Override
    public void onInboxButtonClicked() {
        IInboxUI iInboxUI = iOrganizerMainUI.goToInboxUI();
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onManageEventRoomButtonClicked() {
        IEventsManagementUI iEventsManagementUI = iOrganizerMainUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, this.programController);
    }

    @Override
    public void onMessageButtonClicked() {
        UsersManager usersManager = this.programController.getUsersManager();
        ArrayList<String> userInfo = usersManager.allUsersToString();
        IOrganizerMessageUI iOrganizerMessageUI = iOrganizerMainUI.goToOrganizerMessageUI(userInfo);
        new OrganizerMessageUIPresenter(iOrganizerMessageUI, this.programController);
    }

    @Override
    public void onRegisteredEventsButtonClicked() {
        IEventSignUpUI iEventSignUpUI = iOrganizerMainUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, this.programController);
    }

    @Override
    public void onUserManagementButtonClicked() {
        IUserManagementUI iUserManagementUI = iOrganizerMainUI.goToUserManagementUI();
        new UserManagementUIPresenter(iUserManagementUI, this.programController);
    }
}
