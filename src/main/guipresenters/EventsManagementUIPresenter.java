package main.guipresenters;


import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;

public class EventsManagementUIPresenter {
    ProgramController programController;
    IEventsManagementUI iEventsManagementUI;

    public EventsManagementUIPresenter(IEventsManagementUI eventsManagementUI, ProgramController programController) {
        this.programController = programController;
        this.iEventsManagementUI = eventsManagementUI;
    }
}
