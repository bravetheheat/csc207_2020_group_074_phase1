package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;

public class EventsManagementUIPresenter {
    ProgramController programController;
    IEventsManagementUI iEventsManagementUI;

    public EventsManagementUIPresenter(IEventsManagementUI iEventsManagementUI, ProgramController programController) {
        this.programController = programController;
        this.iEventsManagementUI = iEventsManagementUI;
    }
}
