package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

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
        IInboxUI iInboxUI = iAttendeeMainUI.goToInboxUI();
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
        IAttendeeMessageUI iAttendeeMessageUI = iAttendeeMainUI.goToAttendeeMessageUI();
        new AttendeeMessageUIPresenter(iAttendeeMessageUI, this.programController);
    }

    @Override
    public void onRegisterForEventsButtonClicked() {
        IEventSignUpUI iEventSignUpUI = iAttendeeMainUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, this.programController);
    }
}
