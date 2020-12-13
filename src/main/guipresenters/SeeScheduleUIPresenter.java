package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;
import main.gui_interface.ISeeScheduleUI;
import main.guilisteners.BackButtonListener;

public class SeeScheduleUIPresenter implements BackButtonListener {

    ProgramController programController;
    ISeeScheduleUI iSeeScheduleUI;
    IEventsManagementUI iEventsManagementUI;

    public SeeScheduleUIPresenter(ISeeScheduleUI seeScheduleUI,
                                  ProgramController programController) {
        this.iSeeScheduleUI = seeScheduleUI;
        this.programController = programController;
        iSeeScheduleUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iSeeScheduleUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }
}
