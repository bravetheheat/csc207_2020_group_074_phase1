package main.screencontrollers;

import main.controllers.EventController;
import main.controllers.ProgramController;
import main.presenters.EventSignUpScreen;

import java.util.UUID;

public class EventSignUpScreenController extends ScreenController{
    EventSignUpScreen presenter = new EventSignUpScreen();

    public EventSignUpScreenController(ProgramController programController) {
        super(programController);
    }

    @Override
    public void start() {
        this.presenter.printscreenMessage();
    }

    public void mainOption(){
        this.presenter.promptCommand();
        String choice = this.scanner.nextLine();
        this.presenter.promptUserid();
        String inputUserId = this.scanner.nextLine();
        UUID userid = UUID.fromString(inputUserId);
        this.presenter.promptEventid();
        String inputEventId = this.scanner.nextLine();
        UUID eventid = UUID.fromString(inputEventId);

        EventController eventController = new EventController();
        switch(choice){
            case "1":
                eventController.signupEvent(eventid, userid);
                this.presenter.printSuccessMessage();
                break;
            case "2":
                eventController.cancelEvent(eventid, userid);
                this.presenter.printSuccessMessage();
                break;
            case"3":
                // bace to previous page
                break;
            default:
                this.presenter.printErrorMessage();
                this.mainOption();
        }
    }
}
