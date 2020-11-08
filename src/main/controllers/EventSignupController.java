package main.controllers;

import main.entities.Event;
import main.usecases.EventInfoManager;
import main.usecases.EventsManager;

import java.util.*;
import java.util.UUID;

/**
 * The EventSignupController handles sign up a spot as well as concel a spot for attendees.
 *
 * @author Zewen Ma
 * @version 1.0
 * @since 2020-11-08
 */
public class EventSignupController {
    private EventsManager eventsmanager;

    public EventSignupController(){
        this.eventsmanager = new EventsManager();
    }

    /**
     * Getting a list of events for a User given userid.
     * @param userid user id of a specific User
     * @return a list of events of this User given userid.
     */
    public ArrayList<Event> getUserEvents(UUID userid){
        return this.eventsmanager.getUserEmails(userid);
    }

    /**
     * A getter of all events scheduled.
     * @return a list of Events scheduled
     */
    public ArrayList<Event> getAllEvents(){
        return this.eventsmanager.getEvents();
    }

    /**
     * A sign up method for an attendee User
     * @param eventid of Event an attendee wants to attend
     * @param userid of the attendee User
     * @return true iff the attendee User has successfully signed up
     */
    public boolean signupEvent(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventid, schedule);
        return eventinfomanager.addUser(userid);
    }

    /**
     * A cancellation method for an attendee User
     * @param eventid of Event an attendee wants to attend
     * @param userid of the attendee User
     * @return true iff the attendee User has successfully cancelled the spot
     */
    public boolean cancelEvent(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventid, schedule);
        return eventinfomanager.removeUser(userid);
    }

    /**
     * A Attendee is able to view all the information of the Events scheduled as a string
     * @return a string representation of all the events scheduled
     */
    public String getEventsInfo(){
        return this.eventsmanager.toString();
    }
}
