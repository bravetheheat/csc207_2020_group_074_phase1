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

/**
 * Presenter for creating an event UI
 */
public class CreateEventUIPresenter implements BackButtonListener, ConfirmCreateEventButtonListener, SelectRoomButtonListener {

    ProgramController programController;
    OrganizerController organizerController;
    ICreateEventUI iCreateEventUI;
    ISelectRoomUI iSelectRoomUI;
    IEventsManagementUI iEventsManagementUI;
    private String title;
    private String type;
    private int duration;
    private int capacity;
    private LocalDateTime date;
    private int roomNum = -1;

    public CreateEventUIPresenter(ICreateEventUI createEventUI,
                                  ProgramController programController) {
        this.iCreateEventUI = createEventUI;
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        iCreateEventUI.addBackButtonListener(this);
        iCreateEventUI.addConfirmButtonListener(this);
        iCreateEventUI.addSelectRoomButtonListener(this);
    }

    /**
     * Go to the previous frame
     */
    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iCreateEventUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    /**
     * Save previous inputs and go to the screen to select a room
     */
    @Override
    public void onSelectRoomButtonClicked() {
        programController.saveForNext();

        this.title = iCreateEventUI.getEventTitle();
        this.type = iCreateEventUI.getEventType();
        String durationStr = iCreateEventUI.getEventDuration();
        String capacityStr = iCreateEventUI.getEventCapacity();
        String dateStr = iCreateEventUI.getEventDate();
        if (title.equals("") || type.equals("") || durationStr.equals("") ||
                capacityStr.equals("") || dateStr.equals("")) {
            iCreateEventUI.createNewEventError();
            return;
        }
        if (!this.type.equals("OneSpeakerEvent") && !this.type.equals("MultiSpeakerEvent")
                && !this.type.equals("NoSpeakerEvent")) {
            iCreateEventUI.createNewEventError();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.duration = Integer.parseInt(durationStr);
            this.capacity = Integer.parseInt(capacityStr);
            this.date = LocalDateTime.parse(dateStr, formatter);
            System.out.println("Duration: " + this.duration + " Capacity: " +
                    this.capacity + " Date: " + date);
        } catch (IllegalArgumentException | NullPointerException | DateTimeParseException e) {
            iCreateEventUI.createNewEventError();
            return;
        }

        ArrayList<String> category = iCreateEventUI.getEventConstraints();
        for (String item : category) {
            if (!item.equalsIgnoreCase("Tech") && (!item.equalsIgnoreCase("Table")) &&
                    (!item.equalsIgnoreCase("Stage")) && (!item.equalsIgnoreCase("None"))) {
                iCreateEventUI.createNewEventError();
                return;
            }
        }

        List<Integer> rooms = organizerController.getEventController().
                getSuggestedRooms(category);
        ArrayList<Integer> listOfRoomNum = organizerController.roomToList(rooms);
        iSelectRoomUI = iCreateEventUI.goToSelectRoomUI(listOfRoomNum);
        iSelectRoomUI.storeValuesFromCreateEventUI(this.title, this.type, this.duration, this.capacity, this.date);
        new SelectRoomUIPresenter(iSelectRoomUI, programController);
    }

    /**
     * Create an event
     */
    @Override
    public void onConfirmCreateEventButtonClicked() {
        this.roomNum = iCreateEventUI.getRoomNum();
        this.title = iCreateEventUI.getEventTitleFromSelectRoomUI();
        this.type = iCreateEventUI.getEventTypeFromSelectRoomUI();
        this.duration = iCreateEventUI.getEventDurationFromSelectRoomUI();
        this.capacity = iCreateEventUI.getEventCapacityFromSelectRoomUI();
        this.date = iCreateEventUI.getEventDateFromSelectRoomUI();
        try {
            if (this.roomNum < 0) {
                iCreateEventUI.createNewEventError();
                System.out.println("error1");
                return;
            }
        } catch (NullPointerException e) {
            iCreateEventUI.createNewEventError();
            System.out.println("error2");
            return;
        }

        try {
            if (this.title.equals("") || this.type.equals("") || this.duration == 0 ||
                    this.capacity == 0 || this.date.toString().equals("")) {
                iCreateEventUI.createNewEventError();
                return;
            }
        } catch (NullPointerException e) {
            iCreateEventUI.createNewEventError();
            System.out.println("error3");
            return;
        }

        if (organizerController.checkCapacityInBound(roomNum, capacity)) {
            if (organizerController.createEvent(title, date,
                    roomNum, duration, capacity, type)) {
                programController.saveForNext();
                iCreateEventUI.createNewEventSuccessful();
                return;
            }
            else {
                System.out.println("error4, title is " + this.title + "\ndate is "
                        + this.date + "\nroom number is " + this.roomNum
                        + "\nduration is " + this.duration + "\ncapacity is "
                        + this.capacity + "\ntype is " + this.type
                        + "\nhas tech: " + organizerController.hasTech(this.roomNum));
                iCreateEventUI.createNewEventError();
                return;
            }
        }
        System.out.println("error5, room number is " + this.roomNum + ", capacity is "
                + this.capacity);
        iCreateEventUI.createNewEventError();
    }

}
