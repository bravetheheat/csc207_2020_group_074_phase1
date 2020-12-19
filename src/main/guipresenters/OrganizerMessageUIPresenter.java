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
        this.messageController.broadCastToAttendees(this.authController.fetchLoggedInUser(), message);
        programController.saveForNext();
        iOrganizerMessageUI.sendMessageSuccessful();
    }

    @Override
    public void onAllSpeakersButtonClicked() {
        String message = this.iOrganizerMessageUI.getMessage();
        this.messageController.broadCastToSpeakers(this.authController.fetchLoggedInUser(), message);
        programController.saveForNext();
        iOrganizerMessageUI.sendMessageSuccessful();
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
        this.messageController.broadCastToAll(this.authController.fetchLoggedInUser(), message);
        programController.saveForNext();
        iOrganizerMessageUI.sendMessageSuccessful();
    }

    @Override
    public void onSendButtonClicked() {
        ArrayList<String> users = new ArrayList<>(this.iOrganizerMessageUI.getUsersList().getSelectedValuesList());
        String message = iOrganizerMessageUI.getMessage();
        if (!users.isEmpty() && message.length() > 0) {
            ArrayList<String> userIDs = new ArrayList<>();
            for (String user:users) {
                String username;
                int i = user.indexOf(",");
                username = user.substring(i + 2);
                userIDs.add(this.usersManager.getIDFromUsername(username));
            }
            this.messageController.broadCast(this.programController.getAuthController().fetchLoggedInUser(), userIDs, message);
            programController.saveForNext();
            iOrganizerMessageUI.sendMessageSuccessful();
        }
        else {
            iOrganizerMessageUI.sendMessageError();
        }
    }
}
