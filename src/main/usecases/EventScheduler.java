package main.usecases;

import main.entities.Event;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventScheduler {

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
     * Return true iff the input time and duration of an Event is conflict with the scheduled event.
     * Zewen Ma
     * @param event that already scheduled
     * @param time of the newly input event
     * @param duration of the newly input event
     * @return true when there is a conflict
     */
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
     * Return A map whose key are "hour" and "min", representing the hour and min of the end time of the event.
     * Zewen Ma
     * @param time of the event
     * @param duration of the event
     * @return a map representation of the end time of the event
     */
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
                return true;
            }
        }
        return false;
    }

    public boolean isConflict(Map<String, Event> schedule, Event event, LocalDateTime t, String r, int d){
        Map<String, Integer> inputTime = this.getEndTime(t, d);
        int inputTimeHour = inputTime.get("hour");
        if ((9 > t.getHour()) || (inputTimeHour > 17)) {
            return true;
        }
        for (String id : schedule.keySet()) {
            Event e = schedule.get(id);
            if (e.getId().equals(event.getId())){
                continue;
            }
            if (this.checkConflictDate(e, t)){
                //time conflict at same room
                if ((this.checkConflictTime(e, t, d)) && (e.getRoomID().equals(r))) {
                    return true;
                }//speaker conflict at same time
                else if ((this.checkConflictTime(e, t, d))  && (this.checkConflictSpeaker(e, event))) {
                    return true;
                }
            }
        }
        return false;
    }
}
