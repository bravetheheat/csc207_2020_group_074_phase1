package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * The presenter for <code>AttendeeMainUI</code>
 *
 * @author Yi Tao Li
 */
public class AttendeeMainUIPresenter implements LogoutButtonListener, RegisterForEventsButtonListener, MessageButtonListener, InboxButtonListener {

    private IAttendeeMainUI iAttendeeMainUI;
    private ProgramController programController;
    private AuthController authController;

    public AttendeeMainUIPresenter(IAttendeeMainUI iAttendeeMainUI, ProgramController programController) {
        this.iAttendeeMainUI = iAttendeeMainUI;
        this.programController = programController;
        this.authController = this.programController.getAuthController();
        this.iAttendeeMainUI.addLogoutButtonListener(this);
        this.iAttendeeMainUI.addRegisterForEventsButtonListener(this);
        this.iAttendeeMainUI.addMessageButtonListener(this);
        this.iAttendeeMainUI.addInboxButtonListener(this);
    }

    @Override
    public void onInboxButtonClicked() {
        InboxController inboxController = new InboxController(this.programController);
        Map<String, String> messageMap = inboxController.getMessagesOfUser(this.authController.fetchLoggedInUser());
        System.out.println(this.authController.fetchLoggedInUser() + " " + messageMap.values().size());
        ArrayList<String> messages = new ArrayList<>();
        for (Map.Entry<String, String> entry : messageMap.entrySet()) {
            messages.add(entry.getValue());
        }
        IInboxUI iInboxUI = iAttendeeMainUI.goToInboxUI(messages);
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onLogoutButtonClicked() {
        this.authController.logout();
        ILandingUI ilandingUI = iAttendeeMainUI.goToLandingUI();
        new LandingUIPresenter(ilandingUI, this.programController);
    }

    @Override
    public void onMessageButtonClicked() {
        MessageController messageController =
                this.programController.getMessageController();
        ArrayList<String> userIds = (ArrayList<String>) messageController.
                receiversForAttendeeAndOrganizer(this.programController.
                        getAuthController().fetchLoggedInUser());
        ArrayList<String> users = new ArrayList<>();
        for (String id : userIds) {
            users.add(this.programController.getUsersManager().userToString(id));
        }
        IAttendeeMessageUI iAttendeeMessageUI = iAttendeeMainUI.goToAttendeeMessageUI(users);
        new AttendeeMessageUIPresenter(iAttendeeMessageUI, this.programController);
    }

    @Override
    public void onRegisterForEventsButtonClicked() {
        IEventSignUpUI iEventSignUpUI = iAttendeeMainUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, this.programController);
    }
}
