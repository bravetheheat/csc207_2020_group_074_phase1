package main.screencontrollers;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.presenters.EventsManagementScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The EventsManagementScreenController handles events information and management:
 * create and cancel an event; organize the speaker and room; as well as get events info
 *
 * @author Haoze Huang
 * @version 3.4
 * @since 2020-11-11
 */
public class EventsManagementScreenController extends ScreenController {

    OrganizerController organizerController;
    EventsManagementScreen presenter;

    /**
     * Constructor for EventsManagementScreenController
     *
     * @param programController instance of ProgramController
     */
    public EventsManagementScreenController(ProgramController programController) {
        super(programController);
        organizerController = new OrganizerController(programController);
        presenter = new EventsManagementScreen();
    }

    /**
     * Start the screen and exit to organizer screen base on input
     */
    @Override
    public void start() {
        presenter.printScreenName();
        manageEvent();
    }


    /**
     * Present info and manage events base on input command, and reprinting the options if there is
     * invalid input or more things to modify
     */
    public void manageEvent() {
        presenter.promptCommand();
        String command = scanner.nextLine();
        switch (command) {
            case "0":
                programController.goToPreviousScreenController();
                end();
                return;
            case "1":
                if (createRoom()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "2":
                if (createEvent()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "3":
                if (removeEvent()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "4":
                if (modifyRoom()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "5":
                if (modifyTime()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "6":
                if (modifySpeaker()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "7":
                if (modifyEventCapacity()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "8":
                String info = organizerController.getEventController().getEventsInfo();
                presenter.printSchedule(info);
                manageEvent();
                break;
            default:
                presenter.printInvalidInput();
                manageEvent();
        }
    }

    /**
     * Create an event base on organizer input room number, capacity if fixed at 2 for phase 1
     * Only proceed until user input valid input
     *
     * @return verify if the room is successfully created
     */
    public boolean createRoom() {
        try{
            presenter.promptCreateRoom();
            String roomNum = scanner.nextLine();
            presenter.promptRoomCapacity();
            String capacity = scanner.nextLine();
            return organizerController.createRoom(Integer.parseInt(roomNum), Integer.parseInt(capacity));
        }catch (IllegalArgumentException e){
            presenter.printInvalidInput();
            return createRoom();
        }

    }


    /**
     * Create an event base on organizer input title, room id, and time.
     *
     * @return verify if the event is successfully created
     */
    public boolean createEvent() {
        presenter.promptCreateEvent();
        String title = scanner.nextLine();
        String type = getType();
        int duration = getDuration();
        int capacity = getEventCapacity();
        LocalDateTime time = getTime();
        String speakerID = getSpeakerID(); //Modify later
        int roomNum = getRoomNum();
        return organizerController.createEvent(title, time, roomNum, duration, capacity, type);
    }

    /**
     * Cancel an event base on organizer input eventID.
     *
     * @return verify if the event is successfully removed
     */
    public boolean removeEvent() {
        String eventID = getEventID();
        return organizerController.removeEvent(eventID);
    }

    /**
     * Modify time of the event
     *
     * @return verify if the event time is successfully modified
     */
    public boolean modifyTime() {
        String eventID = getEventID();
        LocalDateTime time = getTime();
        return organizerController.updateTime(eventID, time);
    }

    /**
     * Modify the capacity of the event
     *
     * @return verify if the event capacity is successfully modified
     */
    private boolean modifyEventCapacity() {
        return organizerController.updateCapacity(this.getEventID(), this.getEventCapacity());
    }

    /**
     * Change speaker of the event
     *
     * @return verify if the speaker is successfully modified
     */
    public boolean modifySpeaker() {
        String eventID = getEventID();
        String speakerID = getSpeakerID();
        return organizerController.updateSpeaker(eventID, speakerID);
    }

    /**
     * Change room of the event
     *
     * @return verify if the room is successfully modified
     */
    public boolean modifyRoom() {
        String eventID = getEventID();
        int roomID = getRoomNum();
        return organizerController.updateRoom(eventID, roomID);
    }

    /**
     * Helper function to get event id base on input index
     * Only proceed until user input valid input
     *
     * @return String of eventId
     */
    public String getEventID() {
        String info = organizerController.getEventController().getEventsInfo();
        presenter.printSchedule(info);
        presenter.promptEvent();
        handleEmptyList(organizerController.getEventController().getAllEvents());
        try {
            String eventIndex = scanner.nextLine();
            int i = Integer.parseInt(eventIndex);
            return organizerController.getEventController().getEventId(i-1);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            presenter.printInvalidInput();
            return getEventID();
        }

    }

    /**
     * Helper function to get speaker id base on input index
     * Only proceed until user input valid input
     *
     * @return String of speakerId
     */
    public String getSpeakerID() {
        List<String> speakers = organizerController.getAllSpeakers();
        handleEmptyList(speakers);
        try {
            presenter.promptSpeaker(organizerController.speakerToString());
            String speakerIndex = scanner.nextLine();
            return speakers.get(Integer.parseInt(speakerIndex) - 1);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            presenter.printInvalidInput();
            return getSpeakerID();
        }

    }

    /**
     * Helper function to get room num base on input num
     * Only proceed until user input valid input
     *
     * @return roomNum
     */
    public int getRoomNum() {
        presenter.promptRequirement();
        String cateString = scanner.nextLine();
        ArrayList<String> category = new ArrayList<>(Arrays.asList(cateString.split(",")));
        List<Integer> rooms = organizerController.getEventController().getSuggestedRooms(category);
        handleEmptyList(rooms);
        try {
            presenter.promptRoom(rooms.toString());// change to suggested room to string
            String roomIndex = scanner.nextLine();
            return rooms.get(Integer.parseInt(roomIndex) - 1);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            presenter.printInvalidInput();
            return getRoomNum();
        }
    }

    /**
     * Helper function to get time base on input format
     * Only proceed until user input valid input
     *
     * @return time in LocalDateTime
     */
    public LocalDateTime getTime() {
        presenter.promptTime();
        try {
            String timeInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(timeInput, formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            presenter.printInvalidInput();
            return getTime();
        }
    }

    /**
     * Helper function to get the capacity on input format
     * Only proceed until user input valid input
     *
     * @return capacity of the event
     */
    public int getEventCapacity() {
        try {
            presenter.promptCapacity();
            String capacity = scanner.nextLine();
            return Integer.parseInt(capacity);
        } catch (IllegalArgumentException e) {
            presenter.printInvalidInput();
            return getEventCapacity();
        }
    }

    /**
     * Helper function to get the duration of the event based on input format
     * Only proceed until user input valid input
     *
     * @return duration of the event
     */
    public int getDuration() {
        try {
            presenter.promptDuration();
            String duration = scanner.nextLine();
            return Integer.parseInt(duration);
        } catch (IllegalArgumentException e) {
            presenter.printInvalidInput();
            return getDuration();
        }
    }

    /**
     * Helper function to get the type of event base on input format
     * Only proceed until user input valid input
     *
     * @return type of event in String
     */
    public String getType() {
        try {
            presenter.promptType();
            String type = scanner.nextLine();
            if (type.equals("One")) {
                return "OneSpeakerEvent";
            }else if (type.equals("Multi")){
                return "MultiSpeakerEvent";
            }else{
                return "NoSpeakerEvent";
            }
        } catch (IllegalArgumentException e) {
            presenter.printInvalidInput();
            return getType();
        }
    }


    /**
     * Handles situation where no corresponding entity to input
     *
     * @param list of the entity
     */
    public void handleEmptyList(List list) {
        if (list.size() == 0) {
            presenter.printErrorMessage();
            manageEvent();
        }
    }


}
