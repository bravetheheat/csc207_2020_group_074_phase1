package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IAttendeeMessageUI;

public class AttendeeMessageUIPresenter {
    IAttendeeMessageUI iAttendeeMessageUI;
    ProgramController programController;
    public AttendeeMessageUIPresenter(IAttendeeMessageUI iAttendeeMessageUI, ProgramController programController) {
        this.iAttendeeMessageUI = iAttendeeMessageUI;
        this.programController = programController;
    }
}
