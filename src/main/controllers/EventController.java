package main.controllers;

import java.util.*;
import main.entities.Event;
import main.usecases.EventsManager;
import main.usecases.EventInfoManager;

/**
 * The EventController handles event management: create and cancel an event; add or remove a user from
 * the attendee list; organize the speaker; as well as a getter for the current schedule of a User
 *
 * @author Zewen Ma
 * @version 1.0
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
     * @param event Event
     * @return true iff the event is successfully created
     */
    public boolean createEvent(Event event){
        return this.eventsmanager.scheduleEvent(event);
    }

    /**
     * Removing an event from the schedule(stored in EventsManager)
     * @param event Event
     * @return true iff the event is successfully removed
     */
    public boolean removeEvent(Event event){
        return this.eventsmanager.removeEvent(event.getId());
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
     * Add a user to a specific Event
     * @param event stored in eventsmanager
     * @param userid needs to be added to the event
     * @return ture iff the user is successfully added
     */
    public boolean addUser(Event event, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(event.getId(), schedule);
        return eventInfoManager.addUser(userid);
    }

    /**
     * Remove a user from a specific Event
     * @param event stored in eventsmanager
     * @param userid needs to be removed from the event
     * @return ture iff the user is successfully removed
     */
    public boolean removeUser(Event event, UUID userid){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(event.getId(), schedule);
        return eventInfoManager.removeUser(userid);
    }
}
