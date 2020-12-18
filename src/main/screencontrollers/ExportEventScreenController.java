package main.screencontrollers;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.usecases.EventsManager;
import main.entities.Event;
import main.presenters.ExportEventScreen;
import main.usecases.UsersManager;
import main.export.HTMLExport;
import java.util.*;

public class ExportEventScreenController extends ScreenController{
    ExportEventScreen presenter;
    OrganizerController organizerController;
    UsersManager usersManager;
    EventsManager eventsManager;

    /**
     * Constructor for EventsManagementScreenController
     *
     * @param programController instance of ProgramController
     */
    public ExportEventScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        presenter = new ExportEventScreen();
        usersManager = programController.getUsersManager();
        eventsManager = programController.getEventsManager();
    }

    /**
     * Start the screen and exit to organizer screen base on inputs.
     */
    @Override
    public void start() {
        this.presenter.printScreenName();
        this.optionsPrompt();
        this.end();
    }

    public void optionsPrompt(){
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        switch(choice) {
            case"0":
                this.goToPreviousScreenController();
                return;
            case"1":
                if(this.exportEvent()){this.presenter.success();}
                    else{this.presenter.failure();}
                return;
            default:
                this.presenter.invalidInput();
                this.optionsPrompt();
        }
    }

    public boolean exportEvent(){
        presenter.exportEventOption();
        String username = scanner.nextLine();
        String userId = this.usersManager.getIDFromUsername(username);
        if (userId.equals("")){
            return false;
        }
        List<Event> eventList = this.eventsManager.getUserEventsObj(userId);
        HTMLExport export = new HTMLExport();
        export.exportEvents(eventList);
        return true;
    }

}
