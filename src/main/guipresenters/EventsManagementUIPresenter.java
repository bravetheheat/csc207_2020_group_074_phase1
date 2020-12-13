package main.guipresenters;


import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;

import java.util.ArrayList;

public class EventsManagementUIPresenter implements BackButtonListener,
        CreateRoomButtonListener, ModifyRoomButtonListener, CreateEventButtonListener, ModifyEventButtonListener, SeeRoomsButtonListener, SeeScheduleButtonListener {

    ProgramController programController;
    OrganizerController organizerController;
    IEventsManagementUI iEventsManagementUI;
    ICreateRoomUI iCreateRoomUI;
    IOrganizerMainUI iOrganizerMainUI;
    IModifyRoomUI iModifyRoomUI;
    ICreateEventUI iCreateEventUI;
    IModifyEventUI iModifyEventUI;
    ISeeRoomsUI iSeeRoomsUI;
    ISeeScheduleUI iSeeScheduleUI;

    public EventsManagementUIPresenter(IEventsManagementUI eventsManagementUI, ProgramController programController) {
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        this.iEventsManagementUI = eventsManagementUI;
        iEventsManagementUI.addBackButtonListener(this);
        iEventsManagementUI.addCreateRoomButtonListener(this);
        iEventsManagementUI.addModifyRoomButtonListener(this);
        iEventsManagementUI.addCreateEventButtonListener(this);
        iEventsManagementUI.addModifyEventButtonListener(this);
        iEventsManagementUI.addSeeRoomsButtonListener(this);
        iEventsManagementUI.addSeeScheduleButtonListener(this);
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

    @Override
    public void onSeeRoomsButtonClicked() {
        programController.saveForNext();
        ArrayList<Integer> roomNums = (ArrayList<Integer>) organizerController.getAllRooms();
        ArrayList<String> roomInfoList = new ArrayList<>();
        for (int num : roomNums) {
            String roomInfo = "Room #" + num + " Capacity: "
                    + organizerController.getRoomCapacity(num)
                    + " Tech: " + organizerController.hasTech(num)
                    + " Table: " + organizerController.hasTable(num)
                    + " Stage: " + organizerController.hasStage(num);
            roomInfoList.add(roomInfo);
        }
        iSeeRoomsUI = iEventsManagementUI.goToSeeRoomsUI(roomInfoList);
        new SeeRoomsUIPresenter(iSeeRoomsUI, programController);
    }

    @Override
    public void onSeeScheduleButtonClicked() {
        programController.saveForNext();
        ArrayList<String> events = organizerController.
                getEventController().getEventsInfoList();
        iSeeScheduleUI = iEventsManagementUI.goToSeeScheduleUI(events);
        new SeeScheduleUIPresenter(iSeeScheduleUI, programController);
    }
}
