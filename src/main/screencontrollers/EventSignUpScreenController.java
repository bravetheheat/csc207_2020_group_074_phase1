package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.entities.Event;
import main.presenters.EventSignUpScreen;

import java.util.ArrayList;

/**
 * The EventsSignupScreenController handles events sign up and cancellation:
 *
 * @author Zewen Ma
 * @version 4.0
 * @since 2020-11-11
 */
public class EventSignUpScreenController extends ScreenController {
    EventSignUpScreen presenter = new EventSignUpScreen();
    OrganizerController organizerController;
    EventController eventController;
    AuthController authController;

    /**
     * Default constructor.
     * @param programController instance of ProgramController.
     */
    public EventSignUpScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        eventController = organizerController.getEventController();
        authController = programController.getAuthController();
    }

    /**
     * Start the screen and exit to organizer screen base on inputs.
     */
    @Override
    public void start() {
        this.presenter.printScreenMessage();
        this.mainOption();
        this.end();
    }

    /**
     * Deals with the input option.
     */
    public void mainOption() {
        this.presenter.promptCommand();
        String choice = this.scanner.nextLine();
        switch (choice) {
            case "1":
                this.signUpOption();
                break;
            case "2":
                this.cancelOption();
                break;
            case "0":
                this.goToPreviousScreenController();
                break;
            case "3":
                this.getUserEvents();
                break;
            default:
                this.presenter.printErrorMessage();
                this.mainOption();
        }

    }

    /**
     * Return true iff the schedule have at least one event.
     * @return true iff the schedule have at least one event.
     */
    public boolean haveEvent() {
        ArrayList<Event> events = new ArrayList<Event>(eventController.getAllEvents());
        return events.size() > 0;
    }

    /**
     * Return true iff the user signed up for at least one event.
     * @param userId of the user.
     * @return true iff the user signed up for at least one event.
     */
    public boolean userHaveEvent(String userId){
        ArrayList<String> events = new ArrayList<>(eventController.getSignupEvents(userId));
        return events.size() > 0;
    }

    /**
     * Deals the sign up option (Option 1).
     */
    public void signUpOption() {
        String userId = authController.fetchLoggedInUser();
        if (this.haveEvent()) {
            try{
                this.getSignUpInfo(userId);
            }catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e){
                this.presenter.printErrorMessage();
                this.getSignUpInfo(userId);
            }
        } else {
            this.presenter.printNoEventMessage();
        }
    }

    /**
     * Present information of the scheduled events (includes the events a user signed up for) that can be signed up.
     * Present Error message if the user re-sign up for an event that already signed up.
     * @param userId of the User.
     */
    public void getSignUpInfo(String userId){
        try {
            String info = eventController.getEventsInfo();
            this.presenter.promptSignupEvents(info);
            String eventIndex = this.scanner.nextLine();
            int index = Integer.parseInt(eventIndex);
            String eventId = eventController.getEventId(index - 1);
            if (eventController.signupEvent(eventId, userId)) {
                this.presenter.printSuccessMessage();
            } else {
                this.presenter.printFailMessage();
            }
        }catch(NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e){
            presenter.printNoEventMessage();
            getSignUpInfo(userId);
            }
    }

    /**
     * Deals the sign up option (Option 2).
     */
    public void cancelOption() {
        String userId = authController.fetchLoggedInUser();
        if (this.userHaveEvent(userId)) {
            try{
                this.getCancelInfo(userId);
            }catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e){
                this.presenter.printErrorMessage();
                this.getCancelInfo(userId);
            }
        } else {
            this.presenter.printNoEventMessage();
        }
    }

    /**
     * Present information of the signed up events that can be cancelled.
     * Present no events signed up if the user did not signed up for any events.
     * @param userId of the User.
     */
    public void getCancelInfo(String userId){
        try{
            String info = eventController.getUserEvents(userId);
            this.presenter.promptCancelEvents(info);
            String eventIndex = this.scanner.nextLine();
            int index = Integer.parseInt(eventIndex);
            String eventId = eventController.getEventId(index-1);
            if (eventController.cancelEvent(eventId, userId)) {
                this.presenter.printSuccessMessage();
            } else {
                this.presenter.printFailMessage();
            }
        }catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e){
            presenter.printNoEventMessage();
            this.getCancelInfo(userId);
        }
    }

    public void getUserEvents(){
        String userId = authController.fetchLoggedInUser();
        if (this.userHaveEvent(userId)){
            String info = eventController.getUserEvents(userId);
            this.presenter.promptEventSchedule(info);
        }else{
            this.presenter.printNoEventMessage();
        }
    }
}

