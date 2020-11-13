package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.EventsManagementScreen;
import main.usecases.EventBuilder;
import main.usecases.EventInfoManager;
import main.usecases.EventsManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class EventsManagementScreenController extends ScreenController{

    EventsManager eventsManager = new EventsManager();
    EventsManagementScreen presenter = new EventsManagementScreen();

    public EventsManagementScreenController(ProgramController programController) {
        super(programController);
    }

    @Override
    public void start() {
        presenter.printScreenName();
        manageEvent();
        ScreenController nextScreenController = new OrganizerScreenController(this.programController);;
        this.programController.setCurrentScreenController(nextScreenController);
        end();
    }

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
                manageEvent();;
                break;
            case "3":
                if (modifyEvent()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();;
                break;
            case "4":
                if (modifySpeaker()) presenter.printVerification(); else presenter.printInvalidInput();
                manageEvent();;
                break;
            case "5":
                getEventInfo();
                manageEvent();;
                break;
            case "6":
                String info = eventsManager.toString();
                presenter.printSchedule(info);
                manageEvent();;
                break;
            case "7":
                break;
            default:
                presenter.printInvalidInput();
                manageEvent();
        }
    }

    public boolean createEvent(){
        presenter.promptCreateEvent();
        String title = scanner.nextLine();
        String roomID = scanner.nextLine();
        String speakerID = scanner.nextLine();
        String timeInput = scanner.nextLine();
        EventBuilder eb = new EventBuilder();
        eb.setTitle(title);
        eb.setRoom(UUID.fromString(roomID));
        eb.setSpeaker(UUID.fromString(speakerID));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(timeInput, formatter);
        eb.setTime(time);
        return eventsManager.scheduleEvent(eb);
    }

    public boolean removeEvent(){
        presenter.promptEvent();
        String eventID = scanner.nextLine();
        return eventsManager.removeEvent(UUID.fromString(eventID));
    }

    public boolean modifyEvent(){
        presenter.promptEvent();
        EventInfoManager eventInfoManager = getEventInfoManager();
        presenter.promptModifyEvent();
        String timeInput = scanner.nextLine();
        String roomID = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(timeInput, formatter);
        return eventInfoManager.updateEventInfo(time,UUID.fromString(roomID));
    }

    public boolean modifySpeaker(){
        EventInfoManager eventInfoManager = getEventInfoManager();
        presenter.promptSpeaker();
        String speakerID = scanner.nextLine();
        String option = scanner.nextLine();
        switch (option){
            case "1":
                return eventInfoManager.addSpeaker(UUID.fromString(speakerID));
            case "2":
                return eventInfoManager.removeSpeaker(UUID.fromString(speakerID));
            default:
                return false;
        }
    }

    public void getEventInfo(){
        presenter.promptEvent();
        EventInfoManager eventInfoManager = getEventInfoManager();
        String info = eventInfoManager.getEvent();
        presenter.printSchedule(info);
    }

    public EventInfoManager getEventInfoManager(){
        String eventId = scanner.nextLine();
        return new EventInfoManager(UUID.fromString(eventId),
                eventsManager.getSchedule());
    }

}
