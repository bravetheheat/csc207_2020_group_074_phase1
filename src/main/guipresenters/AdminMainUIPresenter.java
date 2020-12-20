package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.Map;

public class AdminMainUIPresenter implements LogoutButtonListener, UserManagementButtonListener, MessageButtonListener,
        InboxButtonListener, RegisteredEventsButtonListener, ExportEventsButtonListener {

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
        this.iAdminMainUI.addExportEventsButtonListener(this);
    }

    @Override
    public void onLogoutButtonClicked() {
        programController.saveForNext();
        this.authController.logout();
        ILandingUI ilandingUI = iAdminMainUI.goToLandingUI();
        new LandingUIPresenter(ilandingUI, this.programController);
    }

    @Override
    public void onInboxButtonClicked() {
        InboxController inboxController = new InboxController(this.programController);
        Map<String, String> messageMap = inboxController.getMessagesOfUser(this.authController.fetchLoggedInUser());
        ArrayList<String> messages = new ArrayList<>();
        for (Map.Entry<String, String> entry : messageMap.entrySet()) {
            messages.add(entry.getValue());
        }
        IInboxUI iInboxUI = iAdminMainUI.goToInboxUI(messages);
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onMessageButtonClicked() {
        MessageController messageController = this.programController.getMessageController();
        ArrayList<String> userIds = (ArrayList<String>) messageController.
                receiversForAttendeeAndOrganizer(this.programController.
                        getAuthController().fetchLoggedInUser());
        ArrayList<String> users = new ArrayList<>();
        for (String id : userIds) {
            users.add(this.programController.getUsersManager().userToString(id));
        }
        IOrganizerMessageUI iOrganizerMessageUI = iAdminMainUI.goToOrganizerMessageUI(users);
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

    @Override
    public void onExportEventsButtonClicked() {
        UsersManager usersManager = this.programController.getUsersManager();
        ArrayList<String> userInfo = usersManager.allUsersToString();
        IAdminExportToHTMLUI iAdminExportToHTMLUI = iAdminMainUI.goToAdminExportToHTMLUI(userInfo);
        new AdminExportToHTMLUIPresenter(iAdminExportToHTMLUI, this.programController);
    }
}
