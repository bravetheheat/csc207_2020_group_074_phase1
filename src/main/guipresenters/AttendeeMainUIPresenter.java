package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.*;
import main.guilisteners.InboxButtonListener;
import main.guilisteners.LogoutButtonListener;
import main.guilisteners.MessageButtonListener;
import main.guilisteners.RegisterForEventsButtonListener;

public class AttendeeMainUIPresenter implements LogoutButtonListener, RegisterForEventsButtonListener, MessageButtonListener, InboxButtonListener {

    private AttendeeMainUI attendeeMainUI;
    private ProgramController programController;

    public AttendeeMainUIPresenter(ProgramController programController) {
        this.attendeeMainUI = new AttendeeMainUI();
        this.programController = programController;
        programController.startUI();
        this.attendeeMainUI.addLogoutButtonListener(this);
        this.attendeeMainUI.addRegisterForEventsButtonListener(this);
        this.attendeeMainUI.addMessageButtonListener(this);
        this.attendeeMainUI.addInboxButtonListener(this);
    }

    @Override
    public void onInboxButtonClicked() {
        InboxUI inboxUI = new InboxUI();
        new InboxUIPresenter(inboxUI, this.programController);
        this.attendeeMainUI.dispose();
    }

    @Override
    public void onLogoutButtonClicked() {
        LandingUI landingUI = new LandingUI();
        new LandingUIPresenter(landingUI, this.programController);
        this.attendeeMainUI.dispose();
    }

    @Override
    public void onMessageButtonClicked() {
        AttendeeMessageUI attendeeMessageUI = new AttendeeMessageUI();
        new AttendeeMessageUIPresenter(attendeeMessageUI, this.programController);
        this.attendeeMainUI.dispose();
    }

    @Override
    public void onRegisterForEventsButtonClicked() {
        RegisterUI registerUIUI = new RegisterUI();
        new RegisterUIPresenter(registerUIUI, this.programController);
        this.attendeeMainUI.dispose();
    }
}
