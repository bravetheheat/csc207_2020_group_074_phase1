package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.Map;

public class AttendeeMainUIPresenter implements LogoutButtonListener, RegisterForEventsButtonListener, MessageButtonListener, InboxButtonListener {

    private IAttendeeMainUI iAttendeeMainUI;
    private ProgramController programController;
    private AuthController authController;

    public AttendeeMainUIPresenter(IAttendeeMainUI iAttendeeMainUI, ProgramController programController) {
        this.iAttendeeMainUI = iAttendeeMainUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iAttendeeMainUI.addLogoutButtonListener(this);
        this.iAttendeeMainUI.addRegisterForEventsButtonListener(this);
        this.iAttendeeMainUI.addMessageButtonListener(this);
        this.iAttendeeMainUI.addInboxButtonListener(this);
    }

    @Override
    public void onInboxButtonClicked() {
        InboxController inboxController = new InboxController(this.programController);
        Map<String, String> messageMap = inboxController.getMessagesOfUser(this.authController.fetchLoggedInUser());
        ArrayList<String> messages = new ArrayList<>();
        for (String key:messageMap.keySet()) {
            messages.add(inboxController.getMessageString(key));
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
        UsersManager usersManager = this.programController.getUsersManager();
        ArrayList<String> users = usersManager.allUsersToString();
        IAttendeeMessageUI iAttendeeMessageUI = iAttendeeMainUI.goToAttendeeMessageUI(users);
        new AttendeeMessageUIPresenter(iAttendeeMessageUI, this.programController);
    }

    @Override
    public void onRegisterForEventsButtonClicked() {
        IEventSignUpUI iEventSignUpUI = iAttendeeMainUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, this.programController);
    }
}
