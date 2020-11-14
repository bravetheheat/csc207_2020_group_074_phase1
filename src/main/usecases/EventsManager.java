package main.usecases;

import main.entities.Event;
import java.util.*;

/**
 * The EventsManager holds a list of Events, and modify Event with its corresponding Users.
 *
 * @author Haoze Huang
 * @version 2.1
 * @since 2020-10-31
 */

public class EventsManager {

    private Map<UUID,Event> schedule = new LinkedHashMap<>();

    /**
     * Add an Event to schedule with the given time. Throw an existent exception if
     * the given time is conflict with another Event
     *
     * @param eventBuilder  that to be added
     * @return check for event being added
     */
    public boolean scheduleEvent(EventBuilder eventBuilder) {
        Event newEvent = eventBuilder.toEvent();
        for(UUID id : schedule.keySet()){
            //if time conflict
            Event e = schedule.get(id);
            if ((e.getRoomID() == newEvent.getRoomID()) && (e.getTime() == newEvent.getTime())){
                return false;
            }else if((e.getTime() == newEvent.getTime()) && (e.getSpeakerID() == newEvent.getSpeakerID())){
                return false;
            }
        }
        schedule.put(newEvent.getId(), newEvent);
        return true;
    }


    /**
     * Remove an Event from the EventSchedule, if schedule is empty or Event is not in
     * schedule, do nothing.
     *
     * @param canceledEventId that needs to be removed
     * @return check for successful removal
     */
    public boolean removeEvent(UUID canceledEventId) {
        if(schedule.containsKey(canceledEventId)){
            schedule.remove(canceledEventId);
            return true;
        }
        return false;
    }


    /**
     * Get the list of events for a User given id
     *
     * @param userId to be get events from
     * @return userEvents
     */
    public ArrayList<Event> getUserEvents(UUID userId) {
        ArrayList<Event> userEvents = new ArrayList<>();
        for(UUID i : schedule.keySet()){
            for (UUID id : schedule.get(i).getAttendeesID()){
                if(id == userId) userEvents.add(schedule.get(i));
            }
        }
        return userEvents;
    }

    /**
     * Get the list of events for a Speaker given id
     *
     * @param speakerId to be get events from
     * @return speakerEvents
     */
    public ArrayList<UUID> getSpeakerEvents(UUID speakerId) {
        ArrayList<UUID> speakerEvents = new ArrayList<>();
        for(UUID i : schedule.keySet()){
            if(schedule.get(i).getSpeakerID() == speakerId) speakerEvents.add(schedule.get(i).getId());
        }
        return speakerEvents;
    }


    /**
     * Get list of events
     *
     * @return events
     */
    public ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();
        for (UUID i : schedule.keySet()){
            events.add(schedule.get(i));
        }
        return events;
    }


    /**
     * String representation of EventsManager
     *
     * @return String representation of EventsManager
     */
    public String toString() {
        String s = "Events: \n";
        int num = 0;
        for (UUID i : schedule.keySet()){
            Event e = schedule.get(i);
            String eToString = "Event #" + num + " " + e.toString() + '\n';
            s += eToString;
        }
        return s;
    }

    /**
     * A getter of the schedule stored in EventsManager.
     *
     * @return the schedule of EventsManager
     */
    public Map<UUID, Event> getSchedule() {
        return schedule;
    }
}
