package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.BackButtonListener;
import main.guilisteners.SignUpButtonListener;
import main.guilisteners.ViewEventsButtonListener;

import java.util.ArrayList;

/**
 * The presenter class for event registration
 */
@SuppressWarnings("FieldCanBeLocal")

public class EventSignUpUIPresenter implements SignUpButtonListener,
        ViewEventsButtonListener, BackButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private EventController eventController;
    private IEventSignUpUI iEventSignUpUI;
    private IDisplayAllEventsUI iDisplayAllEventsUI;
    private IViewUserEventsUI iViewUserEventsUI;
    private IAttendeeMainUI iAttendeeMainUI;
    private IOrganizerMainUI iOrganizerMainUI;
    private IAdminMainUI iAdminMainUI;


    public EventSignUpUIPresenter(IEventSignUpUI iEventSignUpUI, ProgramController programController) {
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.eventController = programController.getEventController();
        this.iEventSignUpUI = iEventSignUpUI;
        iEventSignUpUI.addSignUpButtonListener(this);
        iEventSignUpUI.addViewEventsButtonListener(this);
        iEventSignUpUI.addBackButtonListener(this);
    }

    /**
     * Go to the screen for sign up events
     */
    @Override
    public void onSignUpButtonClicked() {
        ArrayList<String> listOfEvents = eventController.getEventsInfoList();
        iDisplayAllEventsUI = iEventSignUpUI.goToDisplayAllEventsUI(listOfEvents);
        new DisplayAllEventsUIPresenter(iDisplayAllEventsUI, programController);
    }

    /**
     * Go to the screen to view events
     */
    @Override
    public void onViewEventsButtonClicked() {
        String userId = this.authController.fetchLoggedInUser();
        ArrayList<String> listOfUserEvents = eventController.getUserEventsList(userId);
        iViewUserEventsUI = iEventSignUpUI.goToViewUserEventsUI(listOfUserEvents);
        new ViewUserEventsUIPresenter(iViewUserEventsUI, programController);
    }

    /**
     * Go to the previous screen according to the user type
     */
    @Override
    public void onBackButtonClicked() {
        String userType = this.authController.getUserType();
        switch (userType) {
            case "Attendee":
                programController.saveForNext();
                iAttendeeMainUI = iEventSignUpUI.goToAttendeeMainUI();
                new AttendeeMainUIPresenter(iAttendeeMainUI, programController);
                break;
            case "Organizer":
                programController.saveForNext();
                iOrganizerMainUI = iEventSignUpUI.goToOrganizerMainUI();
                new OrganizerMainUIPresenter(iOrganizerMainUI, programController);
                break;
            case "Admin":
                programController.saveForNext();
                iAdminMainUI = iEventSignUpUI.goToAdminMainUI();
                new AdminMainUIPresenter(iAdminMainUI, programController);
                break;
            default:
                break;
        }
    }
}
