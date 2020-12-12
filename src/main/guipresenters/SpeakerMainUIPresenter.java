package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.usecases.UsersManager;

import java.util.ArrayList;

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
        IInboxUI iInboxUI = iSpeakerMainUI.goToInboxUI();
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
