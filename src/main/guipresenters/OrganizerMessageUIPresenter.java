package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.IAdminMainUI;
import main.gui_interface.IOrganizerMainUI;
import main.gui_interface.IOrganizerMessageUI;
import main.guilisteners.*;
import main.usecases.UsersManager;

import java.util.ArrayList;

/**
 * The presenter for <code>OrganizerMessageUI</code>
 *
 * @author Yi Tao Li
 */
public class OrganizerMessageUIPresenter implements BackButtonListener, SendButtonListener, EveryoneButtonListener,
        AllAttendeesButtonListener, AllSpeakersButtonListener {
    private ProgramController programController;
    private AuthController authController;
    private MessageController messageController;
    private UsersManager usersManager;
    private IOrganizerMessageUI iOrganizerMessageUI;

    public OrganizerMessageUIPresenter(IOrganizerMessageUI iOrganizerMessageUI, ProgramController programController) {
        this.iOrganizerMessageUI = iOrganizerMessageUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.messageController = programController.getMessageController();
        this.usersManager = programController.getUsersManager();
        this.iOrganizerMessageUI.addBackButtonListener(this);
        this.iOrganizerMessageUI.addSendButtonListener(this);
        this.iOrganizerMessageUI.addAllAttendeesButtonListener(this);
        this.iOrganizerMessageUI.addEveryoneButtonListener(this);
        this.iOrganizerMessageUI.addAllSpeakersButtonListener(this);
    }

    @Override
    public void onAllAttendeesButtonClicked() {
        String message = this.iOrganizerMessageUI.getMessage();
        if (message.equals("")) {
            iOrganizerMessageUI.sendMessageError();
        }
        else {
            this.messageController.broadCastToAttendees(this.authController.fetchLoggedInUser(), message);
            programController.saveForNext();
            iOrganizerMessageUI.sendMessageSuccessful();
        }
    }

    @Override
    public void onAllSpeakersButtonClicked() {
        String message = this.iOrganizerMessageUI.getMessage();
        if (message.equals("")) {
            iOrganizerMessageUI.sendMessageError();
        }
        else {
            this.messageController.broadCastToSpeakers(this.authController.fetchLoggedInUser(), message);
            programController.saveForNext();
            iOrganizerMessageUI.sendMessageSuccessful();
        }
    }

    @Override
    public void onBackButtonClicked() {
        String type = this.usersManager.fetchType(this.authController.fetchLoggedInUser());
        if (type.equals("Organizer")) {
            IOrganizerMainUI iOrganizerMainUI = this.iOrganizerMessageUI.goToOrganizerMainUI();
            programController.saveForNext();
            new OrganizerMainUIPresenter(iOrganizerMainUI, this.programController);
        }
        else {
            IAdminMainUI iAdminMainUI = this.iOrganizerMessageUI.goToAdminMainUI();
            programController.saveForNext();
            new AdminMainUIPresenter(iAdminMainUI, this.programController);
        }
    }

    @Override
    public void onEveryoneButtonClicked() {
        String message = this.iOrganizerMessageUI.getMessage();
        if (message.equals("")) {
            iOrganizerMessageUI.sendMessageError();
        }
        else {
            this.messageController.broadCastToAll(this.authController.fetchLoggedInUser(), message);
            programController.saveForNext();
            iOrganizerMessageUI.sendMessageSuccessful();
        }
    }

    @Override
    public void onSendButtonClicked() {
        int[] userIndices = iOrganizerMessageUI.getUsersList().getSelectedIndices();
        String message = iOrganizerMessageUI.getMessage();
        if (userIndices.length != 0 || !message.equals("")) {
            ArrayList<String> userIds = (ArrayList<String>) this.messageController.
                    receiversForAttendeeAndOrganizer(this.programController.
                            getAuthController().fetchLoggedInUser());
            for (int index : userIndices) {
                this.messageController.sendMessage(this.programController.
                                getAuthController().fetchLoggedInUser(),
                        userIds.get(index), message);
            }
            programController.saveForNext();
            iOrganizerMessageUI.sendMessageSuccessful();
        }
        else {
            iOrganizerMessageUI.sendMessageError();
        }
    }
}
