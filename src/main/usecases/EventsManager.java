package main.usecases;

import main.entities.Event;
import main.gateways.Gateway;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The EventsManager holds a list of Events, and modify Event with its corresponding Users.
 *
 * @author Haoze Huang
 * @version 2.1
 * @since 2020-10-31
 */

public class EventsManager {

    private Map<String, Event> schedule ;

    public EventsManager() {
        this.schedule = new LinkedHashMap<>();
    }

    /**
     * Add an Event to schedule with the given time. Throw an existent exception if
     * the given time is conflict with another Event
     *
     * @param eventBuilder that to be added
     * @return check for event being added
     */
    public boolean scheduleEvent(EventBuilder eventBuilder) {
        Event newEvent = eventBuilder.toEvent();
        for (String id : schedule.keySet()) {
            //if time conflict
            Event e = schedule.get(id);
            if ((e.getRoomID() == newEvent.getRoomID()) && (e.getTime() == newEvent.getTime())) {
                return false;
            } else if ((e.getTime() == newEvent.getTime()) && (e.getSpeakerID() == newEvent.getSpeakerID())) {
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
    public boolean removeEvent(String canceledEventId) {
        if (schedule.containsKey(canceledEventId)) {
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
    public ArrayList<Event> getUserEvents(String userId) {
        ArrayList<Event> userEvents = new ArrayList<>();
        for (String i : schedule.keySet()) {
            for (String id : schedule.get(i).getAttendeesID()) {
                if (id.equals(userId)) userEvents.add(schedule.get(i));
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
    public ArrayList<String> getSpeakerEvents(String speakerId) {
        ArrayList<String> speakerEvents = new ArrayList<>();
        for (String i : schedule.keySet()) {
            if (schedule.get(i).getSpeakerID().equals(speakerId)) speakerEvents.add(schedule.get(i).getId());
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
        for (String i : schedule.keySet()) {
            events.add(schedule.get(i));
        }
        return events;
    }


    /**
     * A getter of the schedule stored in EventsManager.
     *
     * @return the schedule of EventsManager
     */
    public Map<String, Event> getSchedule() {
        return schedule;
    }

    public void saveEventsToGateway(Gateway gateway) {
        List<Event> events = new ArrayList<>();
        events.addAll(this.schedule.values());
        gateway.saveEvents(events);
    }

    /**
     * A loader to load events from gateway
     *
     * @param gateway Gateway
     */
    public void loadEventsFromGateway(Gateway gateway) {
        this.schedule = new LinkedHashMap<>();
        List<Event> events = gateway.loadEvents();
        for (Event event: events) {
            this.schedule.put(event.getId(), event);
        }
    }
}
