package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IInboxUI;

public class InboxUIPresenter {
    ProgramController programController;
    IInboxUI iInboxUI;
    public InboxUIPresenter(IInboxUI iInboxUI, ProgramController programController) {
        this.programController = programController;
        this.iInboxUI = iInboxUI;
    }
}
