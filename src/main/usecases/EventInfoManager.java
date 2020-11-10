package main.usecases;

import main.entities.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * The EventInfoManager modifies info for a particular Event given event id.
 *
 * @author Haoze Huang
 * @version 2.2
 * @since 2020-10-31
 */

public class EventInfoManager {

    private final Event event;
    private final Map<UUID, Event> schedule;

    /**
     * Class constructor
     *  @param eventId that going to be modified
     * @param schedule of events
     */
    public EventInfoManager(UUID eventId, Map<UUID, Event> schedule) {
        this.schedule = schedule;
        this.event = schedule.get(eventId);
    }

    /**
     * Add speaker to the event depends on the event type
     *
     * @param newSpeakerId that going to be added
     * @return verification of success addition
     */
    public boolean addSpeaker(UUID newSpeakerId){
        //for one speaker event
        if (event.getSpeakerID() == null){
            event.setSpeakerID(newSpeakerId);
            return true;
        }
        return false;
        //for no speaker event (in phase 2)
        //for multiple speaker event (in phase 2)
    }

    /**
     * Remove speaker to the event depends on the event type
     *
     * @param removeSpeakerId that going to be removed
     * @return verification of success removal
     */
    public boolean removeSpeaker(UUID removeSpeakerId){
        //for one speaker event, do not have to worry about time conflict
        if (event.getSpeakerID() != null && event.getSpeakerID() == removeSpeakerId){
            event.setSpeakerID(null);
            return true;
        }
        return  false;
        //for no speaker event (in phase 2)
        //for multiple speaker event (in phase 2)
    }

    /**
     * Add an User from a particular event, if the user is already
     * in the event, do nothing.
     *
     * @param newUserId that needs to be added
     * @return check if user is added
     */
    public boolean addUser(UUID newUserId) {
        if (!event.getAttendeesID().contains(newUserId)) {
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
    public boolean removeUser(UUID removedUserId) {
        if (event.getAttendeesID().contains(removedUserId)){
            event.removeAttendees(removedUserId);
            return true;
        }
        return false;
    }


    /**
     * Update time and room of a particular Event iff no conflict
     *
     * @param newTime of event
     * @param newRoomId of event
     * @return check for successful update
     */
    public boolean updateEventInfo(LocalDateTime newTime, UUID newRoomId) {
        for(UUID id : schedule.keySet()){
            Event e = schedule.get(id);
            //time conflict at same room
            if((e.getTime() == newTime) && (e.getRoomID()== newRoomId)) {
                throw new IllegalArgumentException("Time conflict for room " + e.getRoomID() +
                        " with Event #" + e.getTitle());
            }//speaker conflict at same time
            else if((e.getTime() == newTime) && (e.getSpeakerID()== event.getSpeakerID())) {
                throw new IllegalArgumentException("Time conflict for speaker " + e.getSpeakerID() +
                        " with Event #" + e.getTitle());
            }
        }
        event.setTime(newTime);
        event.setRoomID(newRoomId);
        return true;
    }


    /**
     * Get Users for a particular Event
     *
     * @return usersId
     */
    public List<UUID> getUsers() {
        return event.getAttendeesID();
    }


    /**
     * Get event info to string
     *
     * @return eventInfo
     */
    public String getEvent() {
        return event.toString();
    }

}
