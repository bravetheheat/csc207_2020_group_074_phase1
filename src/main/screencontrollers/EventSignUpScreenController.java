package main.screencontrollers;

import main.controllers.EventController;
import main.controllers.ProgramController;

import main.presenters.EventSignUpScreen;
import main.usecases.UsersManager;

import java.util.UUID;

/**
 * The EventsSignupScreenController handles events sign up and cancellation:
 *
 * @author Zewen Ma
 * @version 2.1
 * @since 2020-11-11
 */
public class EventSignUpScreenController extends ScreenController{
    EventSignUpScreen presenter = new EventSignUpScreen();
    UsersManager usersManager = new UsersManager();
    EventController eventController = new EventController();

    public EventSignUpScreenController(ProgramController programController) {
        super(programController);
    }

    @Override
    public void start() {
        this.presenter.printscreenMessage();
        this.mainOption();
    }

    public void mainOption(){
        this.presenter.promptCommand();
        String choice = this.scanner.nextLine();
        this.presenter.promptUserEmail();
        String userEmail = this.scanner.nextLine();
        UUID userid = usersManager.getIDFromUsername(userEmail);
        this.presenter.promptEvents(eventController.getEventsInfo());
        String eventIndex = this.scanner.nextLine();
        int index = Integer.parseInt(eventIndex);
        UUID eventId = eventController.getEventId(index);

        switch(choice){
            case "1":
                if(eventController.signupEvent(eventId, userid)){
                    this.presenter.printSuccessMessage();
                }else{
                    this.presenter.printFailMessage();
                }

                break;
            case "2":
                if(eventController.cancelEvent(eventId, userid)){
                    this.presenter.printSuccessMessage();
                }else{
                    this.presenter.printFailMessage();
                }
                break;
            case"3":
                // back to previous screen.
                break;
            default:
                this.presenter.printErrorMessage();
                this.mainOption();
        }
    }
}

