package main.usecases;

import main.entities.Event;
import main.entities.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * The EventInfoManager modifies info for a particular Event given event id.
 *
 * @author Haoze Huang， Zewen Ma, Yile Xie
 * @version 4.0
 * @since 2020-10-31
 */

public class EventInfoManager {

    private final Event event;
    private final Map<String, Event> schedule;
    private RoomManager roomManager;
    private UsersManager usersManager;

    /**
     * Class constructor
     *
     * @param eventId  that going to be modified
     * @param schedule of events
     */
    public EventInfoManager(String eventId, Map<String, Event> schedule) {
        this.schedule = schedule;
        this.event = schedule.get(eventId);
    }

    /**
     * @param eventId  that going to be modified
     * @param schedule of events
     * @param rm       to get room info
     * @param um       to get speaker info
     */
    public EventInfoManager(String eventId, Map<String, Event> schedule, RoomManager rm, UsersManager um) {
        this.schedule = schedule;
        this.event = schedule.get(eventId);
        this.roomManager = rm;
        this.usersManager = um;
    }

    /**
     * Add speaker to the event depends on the event type
     *
     * @param newSpeakerId that going to be added
     * @return verification of success addition
     */
    public boolean addSpeaker(String newSpeakerId) {
        String eventType = event.getType();
        // For Single Speaker Event
        if (eventType.equals("OneSpeakerEvent")){
            if (event.getSpeakers().size() == 0) {
                event.addSpeaker(newSpeakerId);
                return true;
            }
            return false;
        }
        // For Multiple Speaker Event
        else if (eventType.equals("MultiSpeakerEvent")){
            if (!event.getSpeakers().contains(newSpeakerId)){
                event.addSpeaker(newSpeakerId);
                return true;
            }
            return false;
        }
        // For No Speaker Event
        return false;
    }

    /**
     * Remove speaker to the event depends on the event type
     *
     * @param removeSpeakerId that going to be removed
     * @return verification of success removal
     */
    public boolean removeSpeaker(String removeSpeakerId) {
        String eventType = event.getType();
        // For Single Speaker Event and Multi Speaker Event
        if (eventType.equals("OneSpeakerEvent") || eventType.equals("MultiSpeakerEvent")){
            if (event.getSpeakers() != null && event.getSpeakers().contains(removeSpeakerId)) {
                event.removeSpeaker(removeSpeakerId);
                return true;
            }
            return false;
        }
        // For No Speaker Event
        return false;
    }

    /**
     * Add an User from a particular event, if the user is already
     * in the event, do nothing.
     *
     * @param newUserId that needs to be added
     * @return check if user is added
     */
    public boolean addUser(String newUserId) {
        int eventSize = event.getAttendeesID().size();
        boolean notFull = event.getCapacity() > eventSize;
        if ((!event.getAttendeesID().contains(newUserId)) && notFull) {
            event.addAttendees(newUserId);
            return true;
        }
        return false;
    }


    /**
     * Remove an User from a particular event iff user is in the event.
     *
     * @param removedUserId that needs to be removed
     * @return check for successful removal
     */
    public boolean removeUser(String removedUserId) {
        if (event.getAttendeesID().contains(removedUserId)) {
            event.removeAttendees(removedUserId);
            return true;
        }
        return false;
    }


    /**
     * Update time, capacity, and room of a particular Event iff no conflict
     *
     * @param newTime   of event
     * @param newRoomId of event
     * @param duration of event
     * @param newCapacity of event
     * @return check for successful update
     */
    public boolean updateEventInfo(LocalDateTime newTime, String newRoomId, int duration, int newCapacity) {
        //check event starting and ending between 9A.M to 5P.M
        EventScheduler scheduler = new EventScheduler();
        if (scheduler.isConflict(schedule, event, newTime, newRoomId, duration)){
            return false;
        }
        event.setTime(newTime);
        event.setRoomID(newRoomId);
        event.setDuration(duration);
        event.setCapacity(newCapacity);
        return true;
    }


    /**
     * Get event basic infomation as a string
     *
     * @return string representation of the event about its info
     */
    public String infoToString(){
        int roomNum = -1;

        for (Room room : roomManager.getAllRoomsObject()) {
            if (room.getId().equals(event.getRoomID())) {
                roomNum = room.getRoomNum();
            }
        }

        return "Title: " + event.getTitle() + "\n"
                + "Time: " + event.getTime() + "\n"
                + "Room: Room #" + roomNum + "\n"
                + "Duration: " + event.getDuration() + " mins \n"
                + "Capacity: " + event.getCapacity() + " people \n"
                + "Type: " + event.getType() + "\n";
    }

    /**
     * Get event details as a string representation
     *
     * @return the information of the event as a string representation.
     */
    public String toString() {
        String speakerName = speakersOfEvent();
        int roomNum = -1;

        for (Room room : roomManager.getAllRoomsObject()) {
            if (room.getId().equals(event.getRoomID())) {
                roomNum = room.getRoomNum();
            }
        }

        return "Title: " + event.getTitle() + "\n"
                + "Time: " + event.getTime() + "\n"
                + "Speaker: " + speakerName + "\n"
                + "Room: Room #" + roomNum + "\n"
                + "Duration: " + event.getDuration() + " mins \n"
                + "Capacity: " + event.getCapacity() + " people \n"
                + "Type: " + event.getType() + "\n";
    }

    public String speakersOfEvent(){
        if (event.getType().equals("NoSpeakerEvent")){
            return "No speaker";
        }
        if (event.getSpeakers().size() == 0){
            return "No speaker is added yet";
        }
        String speakerName = " ";
        switch (event.getType()){
            case "OneSpeakerEvent":
                for (String user : usersManager.getAllUsers()) {
                    if (this.usersManager.fetchType(user).equals("Speaker") && user.equals(event.getSpeakers().get(0))) {
                        speakerName = usersManager.fetchUser(user).getUsername();
                    }
                }
                break;
            case "MultiSpeakerEvent":
                int i = 0;
                for (String user : usersManager.getAllUsers()){
                    if(this.usersManager.fetchType(user).equals("Speaker") && event.getSpeakers().contains(user)){
                        speakerName = speakerName + "\n " + i + ". " + usersManager.fetchUser(user).getUsername();
                        i ++;
                    }
                }
                break;
        }
        return speakerName;
    }

    public ArrayList<String> listOfSpeakersOfEvent(){
        ArrayList<String> speakerNames = new ArrayList<>();
        if (event.getSpeakers().size() == 0){
            return speakerNames;
        }
        switch (event.getType()){
            case "OneSpeakerEvent":
                for (String user : usersManager.getAllUsers()) {
                    if (this.usersManager.fetchType(user).equals("Speaker") && user.equals(event.getSpeakers().get(0))) {
                        speakerNames.add(usersManager.fetchUser(user).getUsername());
                    }
                }
                break;
            case "MultiSpeakerEvent":
                for (String user : usersManager.getAllUsers()){
                    if(this.usersManager.fetchType(user).equals("Speaker") && event.getSpeakers().contains(user)){
                        speakerNames.add(usersManager.fetchUser(user).getUsername());
                    }
                }
                break;
        }
        return speakerNames;
    }

    /**
     * Get Users for a particular Event
     *
     * @return usersId
     */
    public List<String> getUsers() {
        return event.getAttendeesID();
    }

    /**
     * Get the capacity of a particular Event
     *
     * @return capacity of the event
     */
    public int getCapacity(){
        return event.getCapacity();
    }

    /**
     * Get event info
     *
     * @return eventInfo
     */
    public Event getEvent() {
        return event;
    }

    public ArrayList<String> getEventSpeakers(String eventId){
        return schedule.get(eventId).getSpeakers();
    }

    /**
     * Return the room id of given event
     * @param eventId of the Event
     * @return a string representation of the room id of given eventId
     */
    public String getRoomId(String eventId){
        return schedule.get(eventId).getRoomID();
    }

    /**
     * Return the duration of given event
     * @param eventId of the Event
     * @return an integer representation of the duration of given eventId
     */
    public int getDuration(String eventId){
        return schedule.get(eventId).getDuration();
    }

    /**
     * Return the start time of given event
     * @param eventId of the event
     * @return a LocalDateTime representation of the start time of given eventId
     */
    public LocalDateTime getTime(String eventId){
        return schedule.get(eventId).getTime();
    }

    public String getType(){
        return event.getType();
    }
}
