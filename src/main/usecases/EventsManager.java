package main.usecases;

import main.entities.Event;
import main.gateways.Gateway;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The EventsManager holds a list of Events, and modify Event with its corresponding Users.
 *
 * @author Haoze Huang, Zewen Ma
 * @version 2.4
 * @since 2020-10-31
 */

public class EventsManager {

    private Map<String, Event> schedule;
    private RoomManager roomManager;

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
    public boolean scheduleEvent(EventBuilder eventBuilder, String type) {
        EventFactory eventFactory= new EventFactory(eventBuilder);
        Event newEvent = eventFactory.getEvent(type);
        //check event happening between 9A.M to 5P.M
        LocalDateTime time = newEvent.getTime();
        String newRoomId = newEvent.getRoomID();
        int duration = newEvent.getDuration();
        int newCapacity = newEvent.getCapacity();
        Map<String, Integer> inputTime = this.getEndTime(time, duration);
        int inputTimeHour = inputTime.get("hour");
        if ((9 > time.getHour()) || (inputTimeHour > 17)) {
            return false;
        }
        for (String id : schedule.keySet()) {
            Event e = schedule.get(id);
            //time conflict at same room
            if ((this.checkConflictTime(e, time, duration)) && (e.getRoomID().equals(newRoomId))) {
                return false;
            }//speaker conflict at same time
            else if ((this.checkConflictTime(e, time, duration))  && (this.checkConflictSpeaker(e, newEvent))) {
                return false;
            }//check capacity of the new room
            else if (newCapacity > roomManager.getRoomGivenId(newRoomId).getCapacity()){
                return false;
            }
        }
        newEvent.setTime(time);
        newEvent.setRoomID(newRoomId);
        newEvent.setDuration(duration);
        newEvent.setCapacity(newCapacity);
        schedule.put(newEvent.getId(), newEvent);
        return true;

    }
    /**
     * Return true iff there exists a speaker in Event e1 also is in Event e2.
     * Zewen Ma
     * @param e1 Event #1
     * @param e2 Event #2
     * @return true if they contains the same speaker(s).
     */
    public boolean checkConflictSpeaker(Event e1, Event e2){
        for (String speaker: e1.getSpeakers()){
            if (e2.getSpeakers().contains(speaker)){
                return false;
            }
        }
        return true;
    }
    public Map<String, Integer> getEndTime(LocalDateTime time, int duration){
        Map<String, Integer> result = new LinkedHashMap<>();
        int min = time.getMinute() + duration % 60;
        int hour = time.getHour();
        if (min >= 60){
            hour = hour + 1;
            min = min - 60;
        }
        result.put("hour", hour);
        result.put("minute", min);
        return result;
    }

    public boolean checkConflictTime(Event event, LocalDateTime time, int duration){
        LocalDateTime eventTime = event.getTime();
        int eventDuration = event.getDuration();
        Map<String, Integer> eventEndTime = this.getEndTime(eventTime, eventDuration);
        int eventHour = eventEndTime.get("hour"); // end hour of the event
        int eventMin = eventEndTime.get("minute"); // end min of the event
        Map<String, Integer> inputEndTime = this.getEndTime(time, duration);
        int inputHour = inputEndTime.get("hour"); // end hour of the input time
        int inputMin = inputEndTime.get("minute"); // end min of the input time
        if (eventTime.getHour() <= time.getHour()){
            return !((inputHour >= eventHour) && (inputMin >= eventMin));
            // Given: the start hour of scheduled event is less than or equal to the newly event's
            // if the end hour and min of input time is greater than or equal to the
            // end hour and min of the scheduled event, then there is not conflict. Add a "not" to make the
            // method return ture when there is a conflict.
        }
        else{
            return !(eventTime.getHour() >= inputHour && eventTime.getMinute() >= inputMin);
            // Given: the start hour of scheduled event is greater than the newly event's
            // if the end hour and of the input event is less than or equal to these of the
            // scheduled event, then there is not conlict. Add a "not" to make the method return
            // ture when there is a conflict.
        }
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
     * Get the list of eventIds for a User given id
     *
     * @param userId to be get events from
     * @return userEvents
     */
    public ArrayList<String> getUserEvents(String userId) {

        ArrayList<String> userEvents = new ArrayList<>();
        for (String eventId : schedule.keySet()) {
            EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
            List<String> users = eventInfoManager.getUsers();
            for (String user : users) {
                if (user.equals(userId)) {
                    userEvents.add(eventId);
                }
            }
        }
        return userEvents;
    }

    /**
     * Get the list of events for a Speaker given id
     * Modified by Zewen Ma on 2020.11.30
     * @param speakerId to be get events from
     * @return speakerEvents
     */
    public ArrayList<String> getSpeakerEvents(String speakerId) {
        ArrayList<String> speakerEvents = new ArrayList<>();
        for (String eventId : schedule.keySet()) {
            Event event = schedule.get(eventId);
            if (!event.getType().equals("NoSpeakerEvent")){
                ArrayList<String> speakers = event.getSpeakers();
                if (speakers.contains(speakerId)){
                    speakerEvents.add(schedule.get(eventId).getId());
                }
            }
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

    /**
     * Save events to the gateway
     *
     * @param gateway Gateway
     */
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
        for (Event event : events) {
            this.schedule.put(event.getId(), event);
        }
    }
}
