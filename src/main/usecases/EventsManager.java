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
 * @version 3.0
 * @since 2020-10-31
 */

public class EventsManager {

    private Map<String, Event> schedule;

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
            if (this.checkConflictDate(e, time)){
                //time conflict at same room
                if ((this.checkConflictTime(e, time, duration)) && (e.getRoomID().equals(newRoomId))) {
                    return false;
                }//speaker conflict at same time
                else if ((this.checkConflictTime(e, time, duration))  && (this.checkConflictSpeaker(e, newEvent))) {
                    return false;
                }
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
     * Return true iff the input newTime has the same date as the pre-scheduled event's time.
     * @param scheduledEvent a scheduled Event
     * @param newTime of the new-added Event
     * @return ture iff the newTime has the same date as the scheduledEvent's time.
     */
    public boolean checkConflictDate(Event scheduledEvent, LocalDateTime newTime){
        int scheduledEventYear = scheduledEvent.getTime().getYear();
        int scheduledEventMonth = scheduledEvent.getTime().getMonthValue();
        int scheduledEventDate = scheduledEvent.getTime().getDayOfMonth();
        int newEventYear = newTime.getYear();
        int newEventMonth = newTime.getMonthValue();
        int newEventDate = newTime.getDayOfMonth();
        return (scheduledEventYear == newEventYear) && (scheduledEventMonth == newEventMonth) && (scheduledEventDate == newEventDate);
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
        int hour = time.getHour() + duration / 60;
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
        int eventDuration = event.getDuration(); // duration of the event
        int eventStartHour = event.getTime().getHour(); // StartHour of the event
        int eventStartMin = event.getTime().getMinute(); // StartMin of the event
        int newTimeHour = time.getHour(); // StartHour of the new time
        int newTimeMin = time.getMinute(); // StartMin of the new time
        Map<String, Integer> eventEndTime = this.getEndTime(eventTime, eventDuration); // get the end time of the event
        int eventHour = eventEndTime.get("hour"); // end hour of the event
        int eventMin = eventEndTime.get("minute"); // end min of the event
        Map<String, Integer> inputEndTime = this.getEndTime(time, duration);
        int inputHour = inputEndTime.get("hour"); // end hour of the input time
        int inputMin = inputEndTime.get("minute"); // end min of the input time
        if (eventStartHour == newTimeHour){ // if both start time are the same hour, compare end time
            if (eventHour <= newTimeHour){ // if event end hour <= new time start hour (cannot < but just in case)
                return !(eventMin <= newTimeMin); // No conflict if eventMin <= newTimeMin
            }
            return true;
        }
        else if (eventStartHour < newTimeHour){
            if (eventHour == newTimeHour){
                return !(eventMin <= newTimeMin); // No conflict if eventMin <= newTimeMin
            }
            else if (eventHour < newTimeHour){
                return false;
            }
            return true;
        }
        else{
            if (inputHour == eventStartHour){
                return !(inputMin <= eventStartMin);
            }
            else if (inputHour < eventStartHour) {
                return false;
            }
            return true;
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
