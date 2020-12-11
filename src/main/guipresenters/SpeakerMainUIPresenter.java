package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;

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
        ISpeakerMessageUI iSpeakerMessageUI = iSpeakerMainUI.goToSpeakerMessageUI();
        new SpeakerMessageUIPresenter(iSpeakerMessageUI, this.programController);
    }

}
