package main.screencontrollers;

import main.controllers.*;
import main.usecases.EventsManager;
import main.entities.Event;
import main.presenters.ExportEventScreen;
import main.usecases.UsersManager;
import main.export.HTMLExport;
import java.util.*;

/**
 * The ExportEventScreenController is a controller class that tell ExportEventScreen what to display
 *
 * @author Leyi Wang
 * @version 1.0
 */

public class ExportEventScreenController extends ScreenController{
    ExportEventScreen presenter;
    OrganizerController organizerController;
    UsersManager usersManager;
    EventsManager eventsManager;
    EventController eventController;

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
        eventController = programController.getEventController();
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

    /**
     * Export event schedule based off the inputs of username
     */
    public boolean exportEvent(){
        presenter.exportEventOption();
        String username = scanner.nextLine();
        String userId = this.usersManager.getIDFromUsername(username);
        if (userId.equals("")){
            return false;
        }

        ArrayList<String> ids = this.eventsManager.getUserEvents(userId);
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        List<Event> eventList = new ArrayList<Event>();
        for(String id : ids){
            eventList.add(schedule.get(id));
        }
        if (eventList.isEmpty()){
            return false;
        }
        HTMLExport export = new HTMLExport();
        export.exportEvents(eventList);
        return true;
    }

}
