package main.guipresenters;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifyEventUIPresenter implements BackButtonListener, GetEventsButtonListener,
        ModifySpeakerButtonListener, ConfirmModifyEventButtonListener, SelectRoomButtonListener {

    private ProgramController programController;
    private EventController eventController;
    private OrganizerController organizerController;
    private IModifyEventUI iModifyEventUI;
    private IEventsManagementUI iEventsManagementUI;
    private IModifySpeakerUI iModifySpeakerUI;
    private IDeleteAnEventUI iDeleteAnEventUI;
    private ISelectRoomUI iSelectRoomUI;
    private int eventIndex = -1;

    public ModifyEventUIPresenter(IModifyEventUI modifyEventUI,
                                  ProgramController programController) {
        iModifyEventUI = modifyEventUI;
        this.programController = programController;
        this.eventController = programController.getEventController();
        this.organizerController = new OrganizerController(programController);
        iModifyEventUI.addBackButtonListener(this);
        iModifyEventUI.addGetEventsButtonListener(this);
        iModifyEventUI.addModifySpeakerButtonListener(this);
        iModifyEventUI.addConfirmModifyEventButtonListener(this);
        iModifyEventUI.addSelectRoomButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iModifyEventUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    @Override
    public void onConfirmModifyEventButtonClicked() {
        this.eventIndex = iModifyEventUI.getEventIndex();
        if (eventIndex < 0) {
            iModifyEventUI.modifyEventError();
            return;
        }
        String eventId = eventController.getEventId(eventIndex);
        if (eventId == null) {
            System.out.println("event id is null");
            eventId = "";
        }
        String dateStr = iModifyEventUI.getEventTime();
        if (dateStr == null) {
            System.out.println("date is null");
            dateStr = "";
        }
        String capacityStr = iModifyEventUI.getEventCapacity();
        if (capacityStr == null) {
            System.out.println("capacity is null");
            capacityStr = "";
        }
        int roomNum = iModifyEventUI.getRoomNum();
        if (!eventId.equals("")) {
            try {
                if (dateStr.equals("") && capacityStr.equals("") && roomNum == -1) {
                    System.out.println("no inputs:\n" + "Event id: " + eventId
                        + "\nDate: " + dateStr
                        + "\nCapacity: " + capacityStr
                        + "Room number: " + roomNum);
                    iModifyEventUI.modifyEventError();
                }
                else {
                    if (!dateStr.equals("")) {
                        DateTimeFormatter formatter = DateTimeFormatter.
                                ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                        if (organizerController.updateTime(eventId, dateTime)){
                            iModifyEventUI.modifyEventSuccessful();
                        }else{
                            iModifyEventUI.modifyEventError();
                        }
                    }
                    if (!capacityStr.equals("")) {
                        int capacity = Integer.parseInt(capacityStr);
                        if (organizerController.updateCapacity(eventId, capacity)){
                            iModifyEventUI.modifyEventSuccessful();
                        }else{
                            iModifyEventUI.modifyEventError();
                        }
                    }
                    if (roomNum != -1) {
                        if (organizerController.updateRoom(eventId, roomNum)){
                            iModifyEventUI.modifyEventSuccessful();
                        }else{
                            iModifyEventUI.modifyEventError();
                        }
                    }

                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("exceptions");
                iModifyEventUI.modifyEventError();
            }

        }
        else {
            System.out.println("event index is -1");
            iModifyEventUI.modifyEventError();
        }
    }

    @Override
    public void onGetEventsButtonClicked() {
        programController.saveForNext();
        iDeleteAnEventUI = iModifyEventUI.goToDeleteAnEventUI(
                eventController.getEventsInfoList());
        new DeleteAnEventUIPresenter(iDeleteAnEventUI, programController);
    }

    @Override
    public void onModifySpeakerButtonClicked() {
        programController.saveForNext();
        eventIndex = iModifyEventUI.getEventIndex();
        if (eventIndex < 0) {
            iModifyEventUI.modifyEventError();
        }
        else {
            ArrayList<String> listOfAllSpeakers = (ArrayList<String>) organizerController.getAllSpeakerUsernames();
            ArrayList<String> listOfEventSpeakers = eventController.getEventSpeakers(eventController.getEventId(eventIndex));
            iModifySpeakerUI = iModifyEventUI.goToModifySpeakerUI(
                    listOfAllSpeakers, listOfEventSpeakers);
            iModifySpeakerUI.storeEventIndexFromModifyEvent(this.eventIndex);
            new ModifySpeakerUIPresenter(iModifySpeakerUI, programController);
        }
    }

    @Override
    public void onSelectRoomButtonClicked() {
        programController.saveForNext();
        this.eventIndex = iModifyEventUI.getEventIndex();
        if (this.eventIndex == -1) {
            System.out.println("event index is -1");
            iModifyEventUI.modifyEventError();
            return;
        }
        String constraints = iModifyEventUI.getRoomConstraints();
        ArrayList<String> category = new ArrayList<>(Arrays.asList(constraints.split("[\\s]*[,][\\s]*")));
        for (String item : category) {
            if (!item.equalsIgnoreCase("Tech") && (!item.equalsIgnoreCase("Table")) &&
                    (!item.equalsIgnoreCase("Stage")) && (!item.equalsIgnoreCase("None"))) {
                iModifyEventUI.modifyEventError();
                return;
            }
        }
        List<Integer> rooms = organizerController.getEventController().
                getSuggestedRooms(category);
        iSelectRoomUI = iModifyEventUI.goToSelectRoomUI((ArrayList<Integer>) rooms, category);
        iSelectRoomUI.storeEventIndexFromModifyEvent(this.eventIndex);
        new SelectRoomUIPresenter(iSelectRoomUI, programController);
    }
}
