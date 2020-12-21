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

/**
 * The presenter for <code>OrganizerMainUI</code>
 *
 * @author Yi Tao Li
 */
public class OrganizerMainUIPresenter implements LogoutButtonListener, UserManagementButtonListener,
        ManageEventRoomButtonListener, RegisteredEventsButtonListener, MessageButtonListener, InboxButtonListener {

    private IOrganizerMainUI iOrganizerMainUI;
    private ProgramController programController;
    private AuthController authController;

    public OrganizerMainUIPresenter(IOrganizerMainUI iOrganizerMainUI, ProgramController programController) {
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        this.iOrganizerMainUI = iOrganizerMainUI;
        this.iOrganizerMainUI.addLogoutButtonListener(this);
        this.iOrganizerMainUI.addUserManagementButtonListener(this);
        this.iOrganizerMainUI.addManageEventRoomButtonListener(this);
        this.iOrganizerMainUI.addRegisteredEventsButtonListener(this);
        this.iOrganizerMainUI.addMessageButtonListener(this);
        this.iOrganizerMainUI.addInboxButtonListener(this);
    }

    @Override
    public void onLogoutButtonClicked() {
        this.authController.logout();
        ILandingUI ilandingUI = iOrganizerMainUI.goToLandingUI();
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
        IInboxUI iInboxUI = iOrganizerMainUI.goToInboxUI(messages);
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onManageEventRoomButtonClicked() {
        IEventsManagementUI iEventsManagementUI = iOrganizerMainUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, this.programController);
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
        IOrganizerMessageUI iOrganizerMessageUI = iOrganizerMainUI.goToOrganizerMessageUI(users);
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
