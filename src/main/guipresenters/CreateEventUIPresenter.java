package main.guipresenters;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.ICreateEventUI;
import main.gui_interface.IEventsManagementUI;
import main.gui_interface.ISelectRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateEventButtonListener;
import main.guilisteners.SelectRoomButtonListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CreateEventUIPresenter implements BackButtonListener, ConfirmCreateEventButtonListener, SelectRoomButtonListener {

    ProgramController programController;
    OrganizerController organizerController;
    ICreateEventUI iCreateEventUI;
    ISelectRoomUI iSelectRoomUI;
    IEventsManagementUI iEventsManagementUI;
    private String title;
    private String type;
    private String durationStr;
    private String capacityStr;
    private String dateStr;

    public CreateEventUIPresenter(ICreateEventUI createEventUI,
                                  ProgramController programController) {
        this.iCreateEventUI = createEventUI;
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        iCreateEventUI.addBackButtonListener(this);
        iCreateEventUI.addConfirmButtonListener(this);
        iCreateEventUI.addSelectRoomButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iCreateEventUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    @Override
    public void onSelectRoomButtonClicked() {
        programController.saveForNext();

        title = iCreateEventUI.getEventTitle();
        type = iCreateEventUI.getEventType();
        durationStr = iCreateEventUI.getEventDuration();
        capacityStr = iCreateEventUI.getEventCapacity();
        dateStr = iCreateEventUI.getEventDate();
        if (title.equals("") || type.equals("") || durationStr.equals("") ||
                capacityStr.equals("") || dateStr.equals("")) {
            iCreateEventUI.createNewEventError();
            return;
        }

        ArrayList<String> category = iCreateEventUI.getEventConstraints();
        List<Integer> rooms = organizerController.getEventController().
                getSuggestedRooms(category);
        ArrayList<Integer> listOfRoomNum = organizerController.roomToList(rooms);
        iSelectRoomUI = iCreateEventUI.goToSelectRoomUI(listOfRoomNum);
        new SelectRoomUIPresenter(iSelectRoomUI, programController);
    }

    @Override
    public void onConfirmCreateEventButtonClicked() {
        int roomNum = iSelectRoomUI.getRoomNum();
        int duration;
        int capacity;
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            duration = Integer.parseInt(durationStr);
            capacity = Integer.parseInt(capacityStr);
            date = LocalDateTime.parse(dateStr, formatter);
        } catch (IllegalArgumentException | NullPointerException | DateTimeParseException e) {
            iCreateEventUI.createNewEventError();
            return;
        }
        if (title.equals("") || type.equals("") || durationStr.equals("") ||
                capacityStr.equals("") || dateStr.equals("")) {
            iCreateEventUI.createNewEventError();
            return;
        }
        if (organizerController.checkCapacityInBound(roomNum, capacity)) {
            if (organizerController.createEvent(title, date,
                    roomNum, duration, capacity, type)) {
                iCreateEventUI.createNewEventSuccessful();
                return;
            }
        }
        iCreateEventUI.createNewEventError();
    }

}
