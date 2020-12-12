package main.guipresenters;


import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;

public class EventsManagementUIPresenter implements BackButtonListener,
        CreateRoomButtonListener, ModifyRoomButtonListener, CreateEventButtonListener, ModifyEventButtonListener {

    ProgramController programController;
    IEventsManagementUI iEventsManagementUI;
    ICreateRoomUI iCreateRoomUI;
    IOrganizerMainUI iOrganizerMainUI;
    IModifyRoomUI iModifyRoomUI;
    ICreateEventUI iCreateEventUI;
    IModifyEventUI iModifyEventUI;

    public EventsManagementUIPresenter(IEventsManagementUI eventsManagementUI, ProgramController programController) {
        this.programController = programController;
        this.iEventsManagementUI = eventsManagementUI;
        iEventsManagementUI.addBackButtonListener(this);
        iEventsManagementUI.addCreateRoomButtonListener(this);
        iEventsManagementUI.addModifyRoomButtonListener(this);
        iEventsManagementUI.addCreateEventButtonListener(this);
        iEventsManagementUI.addModifyEventButtonListener(this);
    }


    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iOrganizerMainUI = iEventsManagementUI.goToOrganizerMainUI();
        new OrganizerMainUIPresenter(iOrganizerMainUI, programController);
    }

    @Override
    public void onCreateRoomButtonClicked() {
        programController.saveForNext();
        iCreateRoomUI = iEventsManagementUI.goToCreateRoomUI();
        new CreateRoomUIPresenter(iCreateRoomUI, programController);
    }

    @Override
    public void onModifyRoomButtonClicked() {
        programController.saveForNext();
        iModifyRoomUI = iEventsManagementUI.goToModifyRoomUI();
        new ModifyRoomUIPresenter(iModifyRoomUI, programController);
    }

    @Override
    public void onCreateEventButtonClicked() {
        programController.saveForNext();
        iCreateEventUI = iEventsManagementUI.goToCreateEventUI();
        new CreateEventUIPresenter(iCreateEventUI, programController);
    }

    @Override
    public void onModifyEventButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iEventsManagementUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }
}
