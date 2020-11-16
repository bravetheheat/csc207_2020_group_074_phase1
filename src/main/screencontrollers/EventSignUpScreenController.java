package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.entities.Event;
import main.presenters.EventSignUpScreen;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The EventsSignupScreenController handles events sign up and cancellation:
 *
 * @author Zewen Ma
 * @version 3.2
 * @since 2020-11-11
 */
public class EventSignUpScreenController extends ScreenController {
    EventSignUpScreen presenter = new EventSignUpScreen();
    UsersManager usersManager = new UsersManager();
    OrganizerController organizerController;
    EventController eventController;
    AuthController authController;


    public EventSignUpScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        eventController = organizerController.getEventController();
        authController = new AuthController(programController);
    }

    @Override
    public void start() {
        this.presenter.printScreenMessage();
        this.mainOption();
        this.end();
    }

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
            default:
                this.presenter.printErrorMessage();
                this.mainOption();
        }

    }

    public boolean haveEvent() {
        ArrayList<Event> events = new ArrayList<Event>(eventController.getAllEvents());
        return events.size() > 0;
    }

    public boolean userHaveEvent(String userId){
        ArrayList<String> events = new ArrayList<>(eventController.getSignupEvents(userId));
        return events.size() > 0;
    }

    public void signUpOption() {
        String userEmail = authController.fetchLoggedInUser();
        String userId = usersManager.getIDFromUsername(userEmail);
        if (this.haveEvent()) {
            String info = eventController.getEventsInfo();
            this.presenter.promptSignupEvents(info);
            String eventIndex = this.scanner.nextLine();
            int index = Integer.parseInt(eventIndex);
            String eventId = eventController.getEventId(index-1);
            if (eventController.signupEvent(eventId, userId)) {
                eventController.addUser(eventId, userId);
                List<String> users = eventController.getUsers(eventId);
                this.presenter.printEventAttendee(users);
                this.presenter.printSuccessMessage();
            } else {
                this.presenter.printFailMessage();
            }
        } else {
            this.presenter.printNoEventMessage();
        }
    }

    public void cancelOption() {
        String userEmail = authController.fetchLoggedInUser();
        String userId = usersManager.getIDFromUsername(userEmail);
        if (this.userHaveEvent(userId)) {
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
        } else {
            this.presenter.printNoEventMessage();
        }
    }

}

