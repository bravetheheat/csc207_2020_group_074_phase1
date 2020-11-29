package main.usecases;

import main.entities.Event;
import main.entities.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The EventInfoManager modifies info for a particular Event given event id.
 *
 * @author Haoze Huang
 * @version 2.3
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
        if (eventType.equals("SingleSpeakerEvent")){
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
        if (eventType.equals("SingleSpeakerEvent") || eventType.equals("MultiSpeakerEvent")){
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
        boolean notFull = false;
        String roomId = event.getRoomID();
        int eventSize = event.getAttendeesID().size();
        for (Room r : roomManager.getAllRoomsObject()){
            //get the room object base on event's room id
            if (r.getId().equals(roomId)){
                //check if the room is full
                notFull = r.getCapacity() >= eventSize;
            }
        }
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
     * Update time and room of a particular Event iff no conflict
     *
     * @param newTime   of event
     * @param newRoomId of event
     * @return check for successful update
     */
    public boolean updateEventInfo(LocalDateTime newTime, String newRoomId) {
        //check event happening between 9A.M to 5P.M
        if ((9 > newTime.getHour()) || (newTime.getHour() > 17)) {
            return false;
        }
        for (String id : schedule.keySet()) {
            Event e = schedule.get(id);
            //time conflict at same room
            if ((e.getTime() == newTime) && (e.getRoomID().equals(newRoomId))) {
                return false;
            }//speaker conflict at same time
            else if ((e.getTime() == newTime) && (e.getSpeakerID().equals(event.getSpeakerID()))) {
                return false;
            }//check capacity of the new room
            else if (e.getCapacity() > roomManager.getRoomGivenId(newRoomId).getCapacity()){
                return false;
            }
        }
        event.setTime(newTime);
        event.setRoomID(newRoomId);
        return true;
    }


    public boolean checkConflictSpeaker(Event e1, Event e2){
        for (String speaker: e1.getSpeakers()){
            if (e2.getSpeakers().contains(speaker)){
                return false;
            }
        }
        return true;
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
     * Get event info
     *
     * @return eventInfo
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Get one speaker event information as a string representation
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
        String speakerName = "";
        switch (event.getType()){
            case "NoSpeakerEvent":
                speakerName = "It is no speaker event";
            case "OneSpeakerEvent":
                for (String user : usersManager.getAllUsers()) {
                    if (this.usersManager.fetchRole(user).equals("Speaker") && user.equals(event.getSpeakers().get(0))) {
                        speakerName = usersManager.fetchUser(user).getUsername();
                    }
                }
                break;
            case "MultiSpeakerEvent":
                for (String user : usersManager.getAllUsers()){
                    if(this.usersManager.fetchRole(user).equals("Speaker") && event.getSpeakers().contains(user)){
                        speakerName = speakerName + "; " + usersManager.fetchUser(user).getUsername();
                    }
                }
        }
        return speakerName;
    }



}
