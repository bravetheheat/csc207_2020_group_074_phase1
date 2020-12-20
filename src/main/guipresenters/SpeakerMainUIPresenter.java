package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.Map;

public class SpeakerMainUIPresenter implements LogoutButtonListener, MessageButtonListener, InboxButtonListener {
    ProgramController programController;
    AuthController authController;
    ISpeakerMainUI iSpeakerMainUI;

    public SpeakerMainUIPresenter(ISpeakerMainUI iSpeakerMainUI, ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iSpeakerMainUI = iSpeakerMainUI;
        this.iSpeakerMainUI.addInboxButtonListener(this);
        this.iSpeakerMainUI.addLogoutButtonListener(this);
        this.iSpeakerMainUI.addMessageButtonListener(this);
    }

    @Override
    public void onInboxButtonClicked() {
        InboxController inboxController = new InboxController(this.programController);
        Map<String, String> messageMap = inboxController.getMessagesOfUser(this.authController.fetchLoggedInUser());
        ArrayList<String> messages = new ArrayList<>();
        for (Map.Entry<String, String> entry : messageMap.entrySet()) {
            messages.add(entry.getValue());
        }
        IInboxUI iInboxUI = iSpeakerMainUI.goToInboxUI(messages, this.iSpeakerMainUI.getEvents());
        new InboxUIPresenter(iInboxUI, this.programController);
    }

    @Override
    public void onLogoutButtonClicked() {
        this.authController.logout();
        ILandingUI iLandingUI = iSpeakerMainUI.goToLandingUI();
        new LandingUIPresenter(iLandingUI, this.programController);
    }

    @Override
    public void onMessageButtonClicked() {
        MessageController messageController = programController.getMessageController();
        UsersManager usersManager = programController.getUsersManager();
        ArrayList<String> potentialReceivers = messageController.replyOptionsForSpeaker(this.authController.fetchLoggedInUser());
        ArrayList<String> userInfo = usersManager.selectUsersToString(potentialReceivers);
        ISpeakerMessageUI iSpeakerMessageUI = iSpeakerMainUI.goToSpeakerMessageUI(userInfo);
        new SpeakerMessageUIPresenter(iSpeakerMessageUI, this.programController);
    }

}
