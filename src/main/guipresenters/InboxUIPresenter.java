package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.BackButtonListener;
import main.usecases.UsersManager;

public class InboxUIPresenter implements BackButtonListener {
    ProgramController programController;
    AuthController authController;
    IInboxUI iInboxUI;
    UsersManager usersManager;
    public InboxUIPresenter(IInboxUI iInboxUI, ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.usersManager = programController.getUsersManager();
        this.iInboxUI = iInboxUI;
        this.iInboxUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        switch (this.usersManager.fetchType(this.authController.fetchLoggedInUser())) {
            case "Attendee":
                IAttendeeMainUI iAttendeeMainUI = this.iInboxUI.goToAttendeeMainUI();
                new AttendeeMainUIPresenter(iAttendeeMainUI, this.programController);
                break;
            case "Speaker":
                ISpeakerMainUI iSpeakerMainUI = this.iInboxUI.goToSpeakerMainUI();
                new SpeakerMainUIPresenter(iSpeakerMainUI, this.programController);
                break;
            case "Organizer":
                IOrganizerMainUI iOrganizerMainUI = this.iInboxUI.goToOrganizerMainUI();
                new OrganizerMainUIPresenter(iOrganizerMainUI, this.programController);
                break;
            case "Admin":
                IAdminMainUI iAdminMainUI = this.iInboxUI.goToAdminMainUI();
                new AdminMainUIPresenter(iAdminMainUI, this.programController);
                break;
        }
    }
}
