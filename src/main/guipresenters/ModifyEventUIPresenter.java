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
        if (iModifyEventUI.getEventIndex() >= 0) {
            try {
                int eventIndex = iDeleteAnEventUI.getEventIndex();
                if (eventIndex < 0) {
                    iModifyEventUI.modifyEventError();
                    return;
                }
                String eventId = eventController.getEventId(eventIndex);
                String dateStr = iModifyEventUI.getEventTime();
                DateTimeFormatter formatter = DateTimeFormatter.
                        ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                String capacityStr = iModifyEventUI.getEventCapacity();
                int capacity = Integer.parseInt(capacityStr);
                int roomNum = iSelectRoomUI.getRoomNum();
                if (eventId.equals("") && dateStr.equals("") && capacityStr.equals("") &&
                        roomNum != -1) {
                    iModifyEventUI.modifyEventError();
                }
                else {
                    if (!eventId.equals("") && !dateStr.equals("")) {
                        organizerController.updateTime(eventId, dateTime);
                    }
                    if (!eventId.equals("") && !capacityStr.equals("")) {
                        organizerController.updateCapacity(eventId, capacity);
                    }
                    if (!eventId.equals("") && roomNum != -1) {
                        organizerController.updateRoom(eventId, roomNum);
                    }
                    iModifyEventUI.modifyEventSuccessful();
                }
            } catch (IllegalArgumentException | DateTimeParseException
                    | NullPointerException e) {
                iModifyEventUI.modifyEventError();
            }

        }
        else {
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
        if (iModifyEventUI.getEventIndex() < 0) {
            iModifyEventUI.modifyEventError();
        }
        else {
            ArrayList<String> listOfAllSpeakers = (ArrayList<String>) organizerController.
                    getAllSpeakers();
            ArrayList<String> listOfEventSpeakers = eventController.getEventSpeakers(eventController.getEventId(iModifyEventUI.getEventIndex()));
            iModifySpeakerUI = iModifyEventUI.goToModifySpeakerUI(
                    listOfAllSpeakers, listOfEventSpeakers);
            new ModifySpeakerUIPresenter(iModifySpeakerUI, programController);
        }
    }

    @Override
    public void onSelectRoomButtonClicked() {
        programController.saveForNext();
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
        new SelectRoomUIPresenter(iSelectRoomUI, programController);
    }
}
