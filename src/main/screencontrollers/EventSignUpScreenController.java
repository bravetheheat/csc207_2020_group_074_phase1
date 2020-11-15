package main.screencontrollers;

import main.controllers.EventController;
import main.controllers.ProgramController;

import main.entities.Event;
import main.presenters.EventSignUpScreen;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The EventsSignupScreenController handles events sign up and cancellation:
 *
 * @author Zewen Ma
 * @version 3.0
 * @since 2020-11-11
 */
public class EventSignUpScreenController extends ScreenController {
    EventSignUpScreen presenter = new EventSignUpScreen();
    UsersManager usersManager = new UsersManager();
    EventController eventController = new EventController();


    public EventSignUpScreenController(ProgramController programController) {
        super(programController);
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
        switch (choice){
            case "1":
                this.signUpOption();
                break;
            case "2":
                this.cancelOption();
                break;
            case "3":
                programController.setCurrentScreenController(new AttendeeScreenController(programController));
                break;
            default:
                this.presenter.printErrorMessage();
                this.mainOption();
        }

    }

    public boolean haveEvent(){
        ArrayList<Event> events = new ArrayList<Event>(this.eventController.getAllEvents());
        return events.size() > 0;
    }

    public void signUpOption(){
        this.presenter.promptUserEmail();
        String userEmail = this.scanner.nextLine();
        UUID userId = usersManager.getIDFromUsername(userEmail);
        if(this.haveEvent()){
            this.presenter.promptEvents(eventController.getEventsInfo());
            String eventIndex = this.scanner.nextLine();
            int index = Integer.parseInt(eventIndex);
            UUID eventId = eventController.getEventId(index);
            if (eventController.signupEvent(eventId, userId)) {
            this.presenter.printSuccessMessage();
            } else { this.presenter.printFailMessage();}
        }else{this.presenter.printNoEventMessage();}
    }

    public void cancelOption(){
        this.presenter.promptUserEmail();
        String userEmail = this.scanner.nextLine();
        UUID userId = usersManager.getIDFromUsername(userEmail);
        if(this.haveEvent()){
            this.presenter.promptEvents(eventController.getEventsInfo());
            String eventIndex = this.scanner.nextLine();
            int index = Integer.parseInt(eventIndex);
            UUID eventId = eventController.getEventId(index);
            if (eventController.cancelEvent(eventId, userId)) {
                this.presenter.printSuccessMessage();
            } else { this.presenter.printFailMessage();}
        }else{this.presenter.printNoEventMessage();}
    }

}

