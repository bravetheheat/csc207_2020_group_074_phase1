package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;
import main.gui_interface.ISeeRoomsUI;
import main.guilisteners.BackButtonListener;

public class SeeRoomsUIPresenter implements BackButtonListener {

    ProgramController programController;
    ISeeRoomsUI iSeeRoomsUI;
    IEventsManagementUI iEventsManagementUI;

    public SeeRoomsUIPresenter(ISeeRoomsUI seeRoomsUI, ProgramController programController) {
        this.iSeeRoomsUI = seeRoomsUI;
        this.programController = programController;
        iSeeRoomsUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iSeeRoomsUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }
}
