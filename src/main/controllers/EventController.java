package main.controllers;

import java.util.*;
import main.entities.Event;
import main.presenters.EventSignUpScreen;
import main.usecases.EventsManager;
import main.usecases.EventInfoManager;
import main.usecases.EventBuilder;

/**
 * The EventController handles event management: create and cancel an event; add or remove a user from
 * the attendee list; organize the speaker; as well as a getter for the current schedule of a User
 *
 * @author Zewen Ma
 * @version 3.0
 * @since 2020-11-08
 */
public class EventController {
    private EventsManager eventsmanager;

    /**
     * Default constructor for an EventsController
     */
    public EventController(){
        this.eventsmanager = new EventsManager();
    }

    /**
     * Creating an event to the schedule(stored in EventsManager)
     * @param eventbuilder that creates an event
     * @return true iff the event is successfully created
     */
    public boolean createEvent(EventBuilder eventbuilder){
        return this.eventsmanager.scheduleEvent(eventbuilder);
    }

    /**
     * Removing an event from the schedule(stored in EventsManager)
     * @param eventid of an Event
     * @return true iff the event is successfully removed
     */
    public boolean removeEvent(UUID eventid){
        return this.eventsmanager.removeEvent(eventid);
    }

    /**
     * Getting a list of events for a User given userid.
     * @param userid user id of a specific User
     * @return a list of events of this User given userid.
     */
    public ArrayList<Event> getUserEvents(UUID userid){
        return this.eventsmanager.getUserEvents(userid);
    }

    /**
     * A getter of all events scheduled.
     * @return a list of Events scheduled
     */
    public ArrayList<Event> getAllEvents(){
        return this.eventsmanager.getEvents();
    }

    /**
     * Add a user to a specific Event
     * @param eventid of Events stored in eventsmanager
     * @param userid needs to be added to the event
     * @return ture iff the user is successfully added
     */
    public boolean addUser(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventid, schedule);
        return eventInfoManager.addUser(userid);
    }

    /**
     * Remove a user from a specific Event
     * @param eventid of Events stored in eventsmanager
     * @param userid needs to be removed from the event
     * @return ture iff the user is successfully removed
     */
    public boolean removeUser(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventid, schedule);
        return eventInfoManager.removeUser(userid);
    }

    /**
     * A sign up method for an attendee User
     * @param eventid of an Event that an attendee wants to attend
     * @param userid of the attendee User
     * @return true iff the attendee User has successfully signed up the spot
     */
    public boolean signupEvent(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventid, schedule);
        return eventinfomanager.addUser(userid);
    }

    /**
     * A cancellation method for an attendee User
     * @param eventid of an Event that an attendee wants to cancel a spot
     * @param userid of the attendee User
     * @return true iff the attendee User has successfully cancelled the spot
     */
    public boolean cancelEvent(UUID eventid, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventid, schedule);
        return eventinfomanager.removeUser(userid);
    }

    /**
     * An Attendee is able to view all the information of the Events scheduled as a string
     * @return a string representation of all the events scheduled
     */
    public String getEventsInfo(){
        return this.eventsmanager.toString();
    }

    /**
     * An Attendee is able to view  the information of a single Event as a string given eventid
     * @param eventid of an Event whose information is presented as a string
     * @return a string representation of all the events scheduled
     */
    public String getSingleEventInfo(UUID eventid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventid, schedule);
        return eventinfomanager.getEvent();
    }

}
