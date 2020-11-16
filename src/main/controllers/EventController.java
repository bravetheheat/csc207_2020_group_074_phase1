package main.controllers;

import main.entities.Event;
import main.entities.Room;
import main.usecases.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * The EventController handles event management: create and cancel an event; add or remove a user from
 * the attendee list; organize the speaker; as well as a getter for the current schedule of a User
 *
 * @author Zewen Ma
 * @version 4.1
 * @since 2020-11-08
 */
public class EventController {
    private final EventsManager eventsManager;
    private final UsersManager usersManager;
    private final RoomManager roomManager;


    /**
     * Default constructor for an EventsController
     */
    public EventController(ProgramController programController){
        this.eventsManager = programController.getEventsManager();
        this.usersManager = programController.getUsersManager();
        this.roomManager = programController.getRoomManager();
    }

    /**
     * Creating an event to the schedule(stored in EventsManager)
     * @param eventbuilder that creates an event
     * @return true iff the event is successfully created
     */
    public boolean createEvent(EventBuilder eventbuilder){
        return this.eventsManager.scheduleEvent(eventbuilder);
    }

    /**
     * Removing an event from the schedule(stored in EventsManager)
     * @param eventId of an Event
     * @return true iff the event is successfully removed
     */
    public boolean removeEvent(String eventId){
        return this.eventsmanager.removeEvent(eventId);
    }

    /**
     * Getting a list of events for a User given userid.
     * @param userId user id of a specific User
     * @return a list of events of this User given userid.
     */
    public ArrayList<Event> getUserEvents(String userId){
        return this.eventsmanager.getUserEvents(userId);
    }

    /**
     * Getting a list of events for a Speaker given userid.
     * @param userId user id of a specific Speaker
     * @return a list of events of this User given userid.
     */
    public ArrayList<String> getSpeakerEvents(String userId){
        return this.eventsmanager.getSpeakerEvents(userId);
    }

    /**
     * A getter of all events scheduled.
     * @return a list of Events scheduled
     */
    public ArrayList<Event> getAllEvents(){
        return this.eventsManager.getEvents();
    }

    /**
     * Add a user to a specific Event
     * @param eventId of Events stored in eventsmanager
     * @param userId needs to be added to the event
     * @return ture iff the user is successfully added
     */
    public boolean addUser(String eventId, String userId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
        return eventInfoManager.addUser(userId);
    }

    /**
     * Remove a user from a specific Event
     * @param eventId of Events stored in eventsmanager
     * @param userId needs to be removed from the event
     * @return ture iff the user is successfully removed
     */
    public boolean removeUser(String eventId, String userId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
        return eventInfoManager.removeUser(userId);
    }

    /**
     * A sign up method for an attendee User
     * @param eventId of an Event that an attendee wants to attend
     * @param userId of the attendee User
     * @return true iff the attendee User has successfully signed up the spot
     */
    public boolean signupEvent(String eventId, String userId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.addUser(userId);
    }

    /**
     * A cancellation method for an attendee User
     * @param eventId of an Event that an attendee wants to cancel a spot
     * @param userId of the attendee User
     * @return true iff the attendee User has successfully cancelled the spot
     */
    public boolean cancelEvent(String eventId, String userId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.removeUser(userId);
    }

    /**
     * An Attendee is able to view all the information of the Events scheduled as a string
     * @return a string representation of all the events scheduled
     */
    public String getEventsInfo(){
        String s = "Events: \n";
        int num = 1;
        for (UUID i: this.eventsManager.getSchedule().keySet()){
            String eToString = "Event #" + num + " "+getSingleEventInfo(i);
            num += 1;
            s += eToString;
        }
        return s;
    }

    /**
     * An Attendee is able to view the information of a single Event as a string given eventId
     * @param eventId of an Event
     * @return a string representation of the event
     */
    public String getSingleEventInfo(UUID eventId){
        String speakerName = "";
        int roomNum = -1;
        Event event = this.getSingleEvent(eventId);
        for(UUID user: usersManager.getAllUsers()){
            if(this.usersManager.fetchRole(user).equals("Speaker") && user == event.getSpeakerID()){
                speakerName = usersManager.fetchUser(user).getUsername();
            }
        }
        for(Room room: roomManager.getAllRoomsObject()){
            if (room.getId() == event.getRoomID()){
                roomNum = room.getRoomNum();
            }
        }
        return "Title: " + event.getTitle() + "\n"
                + "Time: " + event.getTime() + "\n"
                + "Speaker: " + speakerName + "\n"
                + "Room: " + roomNum;
    }

    /**
     * Return the Event given its eventId
     * @param eventId of an Event
     * @return an Event
     */
    public Event getSingleEvent(String eventId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.getEvent();
    }

    /**
     * Return true iff a new speaker is successfully added to the event
     * @param eventId of the Event
     * @param speakerId of the Speaker
     * @return ture iff the speaker is successfully added to the event
     */
    public boolean addSpeaker(String eventId, String speakerId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.addSpeaker(speakerId);
    }

    /**
     * Return true iff the speaker is successfully removed from the event
     * @param eventId of the Event
     * @param speakerId of the Speaker
     * @return true iff the speaker is successfully removed from the event
     */
    public boolean removeSpeaker(String eventId, String speakerId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.removeSpeaker(speakerId);
    }

    /**
     * Return true iff the new information of the Event is updated
     * @param eventId of the event
     * @param newTime of the event
     * @param newRoomId of the event
     * @return true iff the new information of the Event is updated
     */
    public boolean updateEventInfo(String eventId, LocalDateTime newTime, String newRoomId){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.updateEventInfo(newTime, newRoomId);
    }

    /**
     * Return the event id given index
     * @param index of the event
     * @return the event id
     */
    public String getEventId(int index){
        Map<String, Event> schedule = this.eventsmanager.getSchedule();
        ArrayList<String> eventIds = new ArrayList<>(schedule.keySet());
        return eventIds.get(index);
    }
}
