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
 * @version 1.0
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
            if((e.getTime() == newTime) && (e.getRoomID()== newRoomId)) {
                return false;
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
}
