package main.screencontrollers;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.presenters.EventsManagementScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * The EventsManagementScreenController handles events information and management:
 * create and cancel an event; organize the speaker and room; as well as get events info
 *
 * @author Haoze Huang
 * @version 3.1
 * @since 2020-11-11
 */
public class EventsManagementScreenController extends ScreenController{

    OrganizerController organizerController;
    EventController eventController;
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
        eventController = new EventController();
    }

    /**
     * Start the screen and exit to organizer screen base on input
     */
    @Override
    public void start() {
        presenter.printScreenName();
        manageEvent();
        ScreenController nextScreenController = new OrganizerScreenController(this.programController);
        this.programController.setCurrentScreenController(nextScreenController);
        end();
    }

    /**
     * Present info and manage events base on input command, and reprinting the options if there is
     * invalid input or more things to modify
     */
    public void manageEvent(){
        presenter.promptCommand();
        String command = scanner.nextLine();
        switch (command){
            case "1":
                if (createEvent()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "2":
                if (removeEvent()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "3":
                if (modifyRoom()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "4":
                if (modifyTime()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "5":
                if (modifySpeaker()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();
                break;
            case "6":
                String info = eventController.getEventsInfo();
                presenter.printSchedule(info);
                manageEvent();
                break;
            case "7":
                break;
            default:
                presenter.printInvalidInput();
                manageEvent();
        }
    }

    /**
     * Create an event base on organizer input title, room id, and time.
     *
     * @return verify if the event is successfully created
     */
    public boolean createEvent(){
        presenter.promptCreateEvent();
        String title = scanner.nextLine();
        LocalDateTime time = getTime();
        UUID speakerID = getSpeakerID();
        String roomNum = scanner.nextLine();
        return organizerController.createEvent(title, time, Integer.parseInt(roomNum), speakerID);
    }

    /**
     * Cancel an event base on organizer input eventID.
     *
     * @return verify if the event is successfully removed
     */
    public boolean removeEvent(){
        UUID eventID = getEventID();
        return organizerController.removeEvent(eventID);
    }

    /**
     * Modify time of the event
     *
     * @return verify if the event time is successfully modified
     */
    public boolean modifyTime(){
        UUID eventID = getEventID();
        LocalDateTime time = getTime();
        return organizerController.updateTime(eventID, time);
    }


    /**
     * Change speaker of the event
     *
     * @return verify if the speaker is successfully modified
     */
    public boolean modifySpeaker(){
        UUID eventID = getEventID();
        UUID speakerID = getSpeakerID();
        return organizerController.updateSpeaker(eventID, speakerID);
    }

    /**
     * Change room of the event
     *
     * @return verify if the room is successfully modified
     */
    public boolean modifyRoom(){
        UUID eventID = getEventID();
        UUID roomID = getRoomID();
        return organizerController.updateRoom(eventID, roomID);
    }

    /**
     * Helper function to get event id base on input index
     *
     * @return UUID of eventId
     */
    public UUID getEventID(){
        String info = eventController.getEventsInfo();
        presenter.printSchedule(info);
        presenter.promptEvent();
        String eventIndex = scanner.nextLine();
        int i = Integer.parseInt(eventIndex);
        return eventController.getEventId(i);
    }

    /**
     * Helper function to get speaker id base on input index
     *
     * @return UUID of speakerId
     */
    public UUID getSpeakerID(){
        List<UUID> speakers = organizerController.getAllSpeakers();
        presenter.promptSpeaker(organizerController.speakerToString());
        String speakerIndex = scanner.nextLine();
        return speakers.get(Integer.parseInt(speakerIndex)-1);
    }

    /**
     * Helper function to get room id base on input index
     *
     * @return UUID of roomId
     */
    public UUID getRoomID(){
        List<UUID> rooms = organizerController.getAllRooms();
        presenter.promptRoom(organizerController.roomToString());
        String roomIndex = scanner.nextLine();
        return rooms.get(Integer.parseInt(roomIndex)-1);
    }

    /**
     * Helper function to get time base on input format
     *
     * @return time in LocalDateTime
     */
    public LocalDateTime getTime(){
        presenter.promptTime();
        String timeInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(timeInput, formatter);
    }


}
