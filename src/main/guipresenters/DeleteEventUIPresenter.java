package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IModifyEventUI;
import main.gui_interface.IEventsManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmDeleteButtonListener;

public class DeleteEventUIPresenter
        implements BackButtonListener, ConfirmDeleteButtonListener {

    ProgramController programController;
    IModifyEventUI iModifyEventUI;
    IEventsManagementUI iEventsManagementUI;

    public DeleteEventUIPresenter(IModifyEventUI deleteEventUI,
                                  ProgramController programController) {
        this.programController = programController;
        this.iModifyEventUI = deleteEventUI;
    }

    @Override
    public void onBackButtonClicked() {
    }

    @Override
    public void onConfirmDeleteButtonClicked() {

    }
}
