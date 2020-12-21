package main.guipresenters;


import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;

import java.util.ArrayList;

/**
 * Presenter class for events management
 *
 * @author Steven Yuan
 */
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


    /**
     * Go to the previous frame
     */
    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iOrganizerMainUI = iEventsManagementUI.goToOrganizerMainUI();
        new OrganizerMainUIPresenter(iOrganizerMainUI, programController);
    }

    /**
     * Go to the screen for creating a room
     */
    @Override
    public void onCreateRoomButtonClicked() {
        programController.saveForNext();
        iCreateRoomUI = iEventsManagementUI.goToCreateRoomUI();
        new CreateRoomUIPresenter(iCreateRoomUI, programController);
    }

    /**
     * Go to screen for modifying a room
     */
    @Override
    public void onModifyRoomButtonClicked() {
        programController.saveForNext();
        iModifyRoomUI = iEventsManagementUI.goToModifyRoomUI();
        new ModifyRoomUIPresenter(iModifyRoomUI, programController);
    }

    /**
     * Go to the screen for creating an event
     */
    @Override
    public void onCreateEventButtonClicked() {
        programController.saveForNext();
        iCreateEventUI = iEventsManagementUI.goToCreateEventUI();
        new CreateEventUIPresenter(iCreateEventUI, programController);
    }

    /**
     * Go to the screen for modifying an event
     */
    @Override
    public void onModifyEventButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iEventsManagementUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }

    /**
     * Go to the screen to see a list of rooms
     */
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

    /**
     * Go to the screen to see a list of events
     */
    @Override
    public void onSeeScheduleButtonClicked() {
        programController.saveForNext();
        ArrayList<String> events = organizerController.
                getEventController().getEventsInfoList();
        iSeeScheduleUI = iEventsManagementUI.goToSeeScheduleUI(events);
        new SeeScheduleUIPresenter(iSeeScheduleUI, programController);
    }
}
