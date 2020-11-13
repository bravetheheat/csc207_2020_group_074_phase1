package main.controllers;

import java.time.LocalDateTime;
import java.util.*;
import main.entities.Event;
import main.usecases.EventsManager;
import main.usecases.EventInfoManager;
import main.usecases.EventBuilder;

/**
 * The EventController handles event management: create and cancel an event; add or remove a user from
 * the attendee list; organize the speaker; as well as a getter for the current schedule of a User
 *
 * @author Zewen Ma
 * @version 3.2
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
     * @param eventId of an Event
     * @return true iff the event is successfully removed
     */
    public boolean removeEvent(UUID eventId){
        return this.eventsmanager.removeEvent(eventId);
    }

    /**
     * Getting a list of events for a User given userid.
     * @param userId user id of a specific User
     * @return a list of events of this User given userid.
     */
    public ArrayList<UUID> getUserEvents(UUID userId){
        return this.eventsmanager.getUserEvents(userId);
    }

    /**
     * Getting a list of events for a Speaker given userid.
     * @param userId user id of a specific Speaker
     * @return a list of events of this User given userid.
     */
    public ArrayList<UUID> getSpeakerEvents(UUID userId){
        return this.eventsmanager.getSpeakerEvents(userId);
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
     * @param eventId of Events stored in eventsmanager
     * @param userId needs to be added to the event
     * @return ture iff the user is successfully added
     */
    public boolean addUser(UUID eventId, UUID userId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
        return eventInfoManager.addUser(userId);
    }

    /**
     * Remove a user from a specific Event
     * @param eventId of Events stored in eventsmanager
     * @param userId needs to be removed from the event
     * @return ture iff the user is successfully removed
     */
    public boolean removeUser(UUID eventId, UUID userId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventInfoManager = new EventInfoManager(eventId, schedule);
        return eventInfoManager.removeUser(userId);
    }

    /**
     * A sign up method for an attendee User
     * @param eventId of an Event that an attendee wants to attend
     * @param userId of the attendee User
     * @return true iff the attendee User has successfully signed up the spot
     */
    public boolean signupEvent(UUID eventId, UUID userId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.addUser(userId);
    }

    /**
     * A cancellation method for an attendee User
     * @param eventId of an Event that an attendee wants to cancel a spot
     * @param userId of the attendee User
     * @return true iff the attendee User has successfully cancelled the spot
     */
    public boolean cancelEvent(UUID eventId, UUID userId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.removeUser(userId);
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
     * @param eventId of an Event whose information is presented as a string
     * @return a string representation of all the events scheduled
     */
    public String getSingleEventInfo(UUID eventId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.getEvent();
    }

    /**
     * Return true iff a new speaker is successfully added to the event
     * @param eventId of the Event
     * @param speakerId of the Speaker
     * @return ture iff the speaker is successfully added to the event
     */
    public boolean addSpeaker(UUID eventId, UUID speakerId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.addSpeaker(speakerId);
    }

    /**
     * Return true iff the speaker is successfully removed from the event
     * @param eventId of the Event
     * @param speakerId of the Speaker
     * @return true iff the speaker is successfully removed from the event
     */
    public boolean removeSpeaker(UUID eventId, UUID speakerId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.removeSpeaker(speakerId);
    }

    /**
     * Return true iff the new information of the Event is updated
     * @param eventId of the event
     * @param newTime of the event
     * @param newRoomId of the event
     * @return true iff the new information of the Event is updated
     */
    public boolean updateEventInfo(UUID eventId, LocalDateTime newTime, UUID newRoomId){
        Map<UUID, Event> schedule = this.eventsmanager.getSchedule();
        EventInfoManager eventinfomanager = new EventInfoManager(eventId, schedule);
        return eventinfomanager.updateEventInfo(newTime, newRoomId);
    }
}
