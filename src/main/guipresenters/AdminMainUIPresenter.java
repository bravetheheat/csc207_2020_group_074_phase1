package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.Map;

public class AdminMainUIPresenter implements LogoutButtonListener, UserManagementButtonListener, MessageButtonListener,
        InboxButtonListener, RegisteredEventsButtonListener {

    private IAdminMainUI iAdminMainUI;
    private ProgramController programController;
    private AuthController authController;

    public AdminMainUIPresenter(IAdminMainUI iAdminMainUI, ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iAdminMainUI = iAdminMainUI;
        this.iAdminMainUI.addLogoutButtonListener(this);
        this.iAdminMainUI.addUserManagementButtonListener(this);
        this.iAdminMainUI.addRegisteredEventsButtonListener(this);
        this.iAdminMainUI.addMessageButtonListener(this);
        this.iAdminMainUI.addInboxButtonListener(this);
//        this.iAdminMainUI.addDataManagementButtonListener(this);
    }

    @Override
    public void onLogoutButtonClicked() {
        programController.saveForNext();
        this.authController.logout();
        ILandingUI ilandingUI = iAdminMainUI.goToLandingUI();
        new LandingUIPresenter(ilandingUI, this.programController);
    }

//    @Override
//    public void onDataManagementButtonClicked() {
//        IGatewayUI iGatewayUI = iAdminMainUI.goToGatewayUI();
//        new GatewayUIPresenter(iGatewayUI, this.programController);
//    }

    @Override
    public void onInboxButtonClicked() {
        InboxController inboxController = new InboxController(this.programController);
        Map<String, String> messageMap = inboxController.getMessagesOfUser(this.authController.fetchLoggedInUser());
        ArrayList<String> messages = new ArrayList<>();
        for (String key:messageMap.keySet()) {
            messages.add(inboxController.getMessageString(key));
        }
        IInboxUI iInboxUI = iAdminMainUI.goToInboxUI(messages);
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onMessageButtonClicked() {
        UsersManager usersManager = this.programController.getUsersManager();
        ArrayList<String> userInfo = usersManager.allUsersToString();
        IOrganizerMessageUI iOrganizerMessageUI = iAdminMainUI.goToOrganizerMessageUI(userInfo);
        new OrganizerMessageUIPresenter(iOrganizerMessageUI, this.programController);
    }

    @Override
    public void onRegisteredEventsButtonClicked() {
        IEventSignUpUI iEventSignUpUI = iAdminMainUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, this.programController);
    }

    @Override
    public void onUserManagementButtonClicked() {
        IAdminUserManagementUI iAdminMainUIUserManagementUI = iAdminMainUI.goToAdminUserManagementUI();
        new AdminUserManagementUIPresenter(iAdminMainUIUserManagementUI, this.programController);
    }
}
