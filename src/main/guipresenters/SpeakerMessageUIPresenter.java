package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.ISpeakerMainUI;
import main.gui_interface.ISpeakerMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.BroadcastButtonListener;
import main.guilisteners.SendButtonListener;
import main.usecases.UsersManager;

import java.util.ArrayList;

public class SpeakerMessageUIPresenter implements BackButtonListener, BroadcastButtonListener, SendButtonListener {
    private ProgramController programController;
    private AuthController authController;
    private MessageController messageController;
    private EventController eventController;
    private ISpeakerMessageUI iSpeakerMessageUI;

    public SpeakerMessageUIPresenter(ISpeakerMessageUI iSpeakerMessageUI, ProgramController programController) {
        this.iSpeakerMessageUI = iSpeakerMessageUI;
        this.programController = programController;
        this.messageController = programController.getMessageController();
        this.authController = programController.getAuthController();
        this.eventController = programController.getEventController();
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
        int i = iSpeakerMessageUI.getEventsList().getSelectedIndex();
        if (i != -1) {
            String message = iSpeakerMessageUI.getMessage();
            String eventID = this.eventController.getSpeakerEvents(this.authController.fetchLoggedInUser()).get(i);
            this.messageController.broadCastForSpeaker(eventID, this.authController.fetchLoggedInUser(), message);
            //TODO add message sent frame
        }
    }

    @Override
    public void onSendButtonClicked() {
        int i = iSpeakerMessageUI.getUsersList().getSelectedIndex();
        if (i != -1) {
            String message = iSpeakerMessageUI.getMessage();
            String userID = this.messageController.replyOptionsForSpeaker(this.authController.fetchLoggedInUser()).get(i);
            this.messageController.sendMessage(message, this.authController.fetchLoggedInUser(), userID);
            //TODO add message sent frame
        }
    }
}
