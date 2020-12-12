package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.ISpeakerMainUI;
import main.gui_interface.ISpeakerMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.BroadcastButtonListener;
import main.guilisteners.SendButtonListener;

public class SpeakerMessageUIPresenter implements BackButtonListener, BroadcastButtonListener, SendButtonListener {
    private ProgramController programController;
    private AuthController authController;
    private MessageController messageController;
    private ISpeakerMessageUI iSpeakerMessageUI;

    public SpeakerMessageUIPresenter(ISpeakerMessageUI iSpeakerMessageUI, ProgramController programController) {
        this.iSpeakerMessageUI = iSpeakerMessageUI;
        this.programController = programController;
        this.messageController = programController.getMessageController();
        this.authController = programController.getAuthController();
        this.iSpeakerMessageUI.addBackButtonListener(this);
        this.iSpeakerMessageUI.addBroadcastButtonListener(this);
        this.iSpeakerMessageUI.addSendButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        ISpeakerMainUI iSpeakerMainUI = this.iSpeakerMessageUI.goToSpeakerMainUI();
        new SpeakerMainUIPresenter(iSpeakerMainUI, this.programController);
    }

    @Override
    public void onBroadcastButtonClicked() {
//        this.messageController.broadCastForSpeaker(, this.authController, iSpeakerMessageUI.getMessage());
    }

    @Override
    public void onSendButtonClicked() {

    }
}
