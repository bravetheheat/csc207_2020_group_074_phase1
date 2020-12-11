package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IEventSignUpUI;

public class EventSignUpUIPresenter {
    ProgramController programController;
    IEventSignUpUI iEventSignUpUI;

    public EventSignUpUIPresenter(IEventSignUpUI iEventSignUpUI, ProgramController programController) {
        this.programController = programController;
        this.iEventSignUpUI = iEventSignUpUI;
    }
}
