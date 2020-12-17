package main.controllers;

import main.entities.Event;
import main.entities.Room;
import main.usecases.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The EventController handles event management: create and cancel an event; add or remove a user from
 * the attendee list; organize the speaker; as well as a getter for the current schedule of a User.
 *
 * @author Zewen Ma
 * @version 5.0
 * @since 2020-11-08
 */
public class EventController {
    private final EventsManager eventsManager;
    private final UsersManager usersManager;
    private final RoomManager roomManager;


    /**
     * Default constructor for an EventsController.
     */
    public EventController(ProgramController programController){
        this.eventsManager = programController.getEventsManager();
        this.usersManager = programController.getUsersManager();
        this.roomManager = programController.getRoomManager();
    }

    /**
     * Create an event to the schedule(stored in EventsManager).
     * @param eventBuilder that creates an event.
     * @return true iff the event is successfully created.
     */
    public boolean createEvent(EventBuilder eventBuilder, String type){
        return this.eventsManager.scheduleEvent(eventBuilder, type);
    }

    /**
     * Remove an event from the schedule(stored in EventsManager).
     * @param eventId of an Event.
     * @return true iff the event is successfully removed.
     */
    public boolean removeEvent(String eventId){
        return this.eventsManager.removeEvent(eventId);
    }

    /**
     * Get a string representation of the events of a user given userId.
     * @param userId user id of a specific User.
     * @return a string of events of this User.
     */
    public String getUserEvents(String userId){
        ArrayList<String> ids = this.eventsManager.getUserEvents(userId);
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        String s = "Events: \n";
        int num = 1;
        for (String eventId: ids){
            EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
            String eventInfo = eventInfoManager.toString();
            String eToString = "Event #" + num + " "+eventInfo + "\n";
            num += 1;
            s += eToString;
        }
        return s;
    }

    public ArrayList<String> getUserEventIds(String userId){
        ArrayList<String> listOfUserEvents = new ArrayList<>();
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        for (String eventId: schedule.keySet()){
            EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
            List<String> attendees = eventInfoManager.getUsers();
            if (attendees.contains(userId)){
                listOfUserEvents.add(eventId);
            }
        }
        return listOfUserEvents;
    }

    public ArrayList<String> getUserEventsList(String userId) {
        ArrayList<String> listOfUserEvents = new ArrayList<>();
        ArrayList<String> ids = this.eventsManager.getUserEvents(userId);
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        for (String eventId: ids) {
            EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
            String eventInfo = eventInfoManager.toString();
            listOfUserEvents.add(eventInfo);
        }
        return listOfUserEvents;
    }

    public int getRoomNum(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
        String roomId = eventInfoManager.getRoomId(eventId);
        return roomManager.getRoomNumGivenId(roomId);
    }

    /**
     * Get a list of eventIds of a User given userId.
     * @param userId of the user.
     * @return a list of eventIds that this user attend.
     */
    public ArrayList<String> getSignupEvents(String userId){
        return this.eventsManager.getUserEvents(userId);
    }

    /**
     * Get a list of events for a Speaker given userid.
     * @param userId user id of a specific Speaker
     * @return a list of events of this User given userid.
     */
    public ArrayList<String> getSpeakerEvents(String userId){
        return this.eventsManager.getSpeakerEvents(userId);
    }

    /**
     * Get a list of all events scheduled.
     * @return a list of Events scheduled.
     */
    public ArrayList<Event> getAllEvents(){
        return this.eventsManager.getEvents();
    }

    /**
     * Add a user to a specific Event.
     * @param eventId of Events stored in eventsManager.
     * @param userId needs to be added to the event.
     * @return ture iff the user is successfully added.
     */
    public boolean addUser(String eventId, String userId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventInfoManager.addUser(userId);
    }

    /**
     * Remove a user from a specific Event.
     * @param eventId of Events stored in eventsManager.
     * @param userId needs to be removed from the event.
     * @return ture iff the user is successfully removed.
     */
    public boolean removeUser(String eventId, String userId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventInfoManager.removeUser(userId);
    }

    /**
     * A sign up method for an attendee User.
     * Return true iff the attendee User has successfully signed up.
     * @param eventId of an Event that an attendee wants to attend.
     * @param userId of the attendee User.
     * @return true iff the attendee User has successfully signed up.
     */
    public boolean signupEvent(String eventId, String userId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.addUser(userId);
    }

    /**
     * A cancellation method for an attendee User.
     * Return true iff the attendee User has successfully cancelled the spot.
     * @param eventId of an Event that an attendee wants to cancel a spot.
     * @param userId of the attendee User.
     * @return true iff the attendee User has successfully cancelled the spot.
     */
    public boolean cancelEvent(String eventId, String userId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.removeUser(userId);
    }

    /**
     * Get a string of all the information of the Events scheduled.
     * @return a string representation of all the events scheduled.
     */
    public String getEventsInfo(){
        String s = "Events: \n";
        int num = 1;
        for (String i: this.eventsManager.getSchedule().keySet()){
            String eToString = "Event #" + num + " "+getSingleEventInfo(i) + "\n";
            num += 1;
            s += eToString;
        }
        return s;
    }

    public ArrayList<String> getEventsInfoList() {
        ArrayList<String> listOfEvents = new ArrayList<>();
        for (String i: this.eventsManager.getSchedule().keySet()) {
            listOfEvents.add(getSingleEventInfo(i));
        }
        return listOfEvents;
    }

    /**
     * Get a string representation of an event.
     * @param eventId of an Event.
     * @return a string representation of the event.
     */
    public String getSingleEventInfo(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.toString();
    }

    /**
     * Get event basic information as a string
     *
     * @return string representation of the event about its info
     */
    public String getSingleEventBasicInfo(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.infoToString();
    }

    /**
     * Get events basic information as a string
     *
     * @return string representation of the events about its info
     */
    public String getEventsBasicInfo(){
        String eventsInfo = "";
        int num = 1;
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        for (String id : schedule.keySet()){
            eventsInfo = eventsInfo + "Event #" + num + "\n";
            eventsInfo = eventsInfo + getSingleEventBasicInfo(id) + "\n\n";
        }
        return eventsInfo;
    }

    /**
     * Return the Event given its eventId.
     * @param eventId of an Event.
     * @return an Event.
     */
    public Event getSingleEvent(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.getEvent();
    }

    /**
     * Return true iff a new speaker is successfully added to the event.
     * @param eventId of the Event.
     * @param speakerId of the Speaker.
     * @return ture iff the speaker is successfully added to the event.
     */
    public boolean addSpeaker(String eventId, String speakerId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.addSpeaker(speakerId);
    }

    /**
     * Return true iff the speaker is successfully removed from the event.
     * @param eventId of the Event.
     * @param speakerId of the Speaker.
     * @return true iff the speaker is successfully removed from the event.
     */
    public boolean removeSpeaker(String eventId, String speakerId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.removeSpeaker(speakerId);
    }

    /**
     * Return true iff the new information of the Event is updated.
     * @param eventId of the event.
     * @param newTime of the event.
     * @param newRoomId of the event.
     * @param duration of the event
     * @return true iff the new information of the Event is updated.
     */
    public boolean updateEventInfo(String eventId, LocalDateTime newTime,
                                   String newRoomId, int duration, int newCapacity){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.updateEventInfo(newTime, newRoomId, duration, newCapacity);
    }

    /**
     * Return the event id given index.
     * @param index of the event.
     * @return the event id.
     */
    public String getEventId(int index){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        ArrayList<String> eventIds = new ArrayList<>(schedule.keySet());
        return eventIds.get(index);
    }

    /**
     * Return a list of Attendees in the Event given its eventId.
     * @param eventId of the event.
     * @return a list of Attendees in this event.
     */
    public List<String> getUsers(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.getUsers();
    }

    /**
     * Get suggested rooms for an event based on the requirements
     *
     * @param category arraylist potentially included ["Tech", "Table", "Stage"]
     * @return verification of the suggested rooms are added into event
     */
    public ArrayList<Integer> getSuggestedRooms(ArrayList<String> category){
        for (int i = 0; i < category.size(); i++){
            category.set(i,category.get(i).toLowerCase());
        }
        ArrayList<Integer> suggestedRooms = new ArrayList<>();
        if (category.contains("none")|| category.size() == 0) {
            for (Room room : roomManager.getAllRoomsObject()){
                suggestedRooms.add(room.getRoomNum());
            }
            return suggestedRooms;
        }
        if(category.contains("tech")) {
            for (Room room : roomManager.getAllRoomsObject()) {
                if (room.getHasTech()) {
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        if(category.contains("table")){
            for (Room room : roomManager.getAllRoomsObject()){
                if(room.getIsTable() ){
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        if(category.contains("stage")) {
            for (Room room : roomManager.getAllRoomsObject()) {
                if (room.getHasStage()) {
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        if (category.contains("tech") && category.contains("table")) {
            for (Room room : roomManager.getAllRoomsObject()) {
                if (room.getHasTech() && room.getIsTable()) {
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        if (category.contains("table") && category.contains("stage")) {
            for (Room room : roomManager.getAllRoomsObject()) {
                if (room.getIsTable() && room.getHasStage()) {
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        if(category.contains("tech") && category.contains("stage")) {
            for (Room room : roomManager.getAllRoomsObject()) {
                if (room.getHasTech() && room.getHasStage()) {
                    suggestedRooms.add(room.getRoomNum());
                }
            }
            return suggestedRooms;
        }
        for (Room room : roomManager.getAllRoomsObject()) {
            if (room.getHasTech() && room.getHasStage() && room.getIsTable()) {
                suggestedRooms.add(room.getRoomNum());
            }
        }
        return suggestedRooms;
    }

    public ArrayList<String> getEventSpeakers(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.getEventSpeakers(eventId);
    }

    public String getEventSpeakersToString(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.speakersOfEvent();
    }

    public String getEventType(String eventId){
        Map<String, Event> schedule = this.eventsManager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule, roomManager, usersManager);
        return eventinfomanager.getType();
    }

}
