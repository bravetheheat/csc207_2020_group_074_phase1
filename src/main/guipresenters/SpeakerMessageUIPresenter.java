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

/**
 * The presenter for <code>SpeakerMessageUI</code>
 *
 * @author Yi Tao Li
 */
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
        programController.saveForNext();
        ISpeakerMainUI iSpeakerMainUI = this.iSpeakerMessageUI.goToSpeakerMainUI();
        new SpeakerMainUIPresenter(iSpeakerMainUI, this.programController);
    }

    @Override
    public void onBroadcastButtonClicked() {
        int i = iSpeakerMessageUI.getEventsList().getSelectedIndex();
        String message = iSpeakerMessageUI.getMessage();
        if (i != -1 && message.length() > 0) {
            String eventID = this.eventController.getSpeakerEvents(this.authController.fetchLoggedInUser()).get(i);
            this.messageController.broadCastForSpeaker(eventID, this.authController.fetchLoggedInUser(), message);
            programController.saveForNext();
            iSpeakerMessageUI.sendMessageSuccessful();
        }
        else {
            iSpeakerMessageUI.sendMessageError();
        }
    }

    @Override
    public void onSendButtonClicked() {
        int i = iSpeakerMessageUI.getUsersList().getSelectedIndex();
        String message = iSpeakerMessageUI.getMessage();
        if (i != -1 && message.length() > 0) {
            String userID = this.messageController.replyOptionsForSpeaker(this.authController.fetchLoggedInUser()).get(i);
            this.messageController.sendMessage(this.authController.fetchLoggedInUser(),userID, message);
            programController.saveForNext();
            iSpeakerMessageUI.sendMessageSuccessful();
        }
        else {
            iSpeakerMessageUI.sendMessageError();
        }
    }
}
