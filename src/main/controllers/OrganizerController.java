package main.controllers;

import main.entities.Event;
import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;
import main.controllers.EventController;
import main.usecases.EventsManager;
import java.time.LocalDateTime;
import main.usecases.EventBuilder;

import java.util.*;

/**
 * Apart from the responsibilities listed in the UserController, the OrganizerController handles advance actions
 * which could be done by an organizer: checking and managing events, Send messages to all Speakers or one
 * specific speaker, all attendees or one specific attendees
 * Creating speaker accounts, and creating rooms.
 *
 * @author Ruoming Ren
 * @version 1.2
 * @since 2020-11-10
 */

public class OrganizerController extends AttendeeController{


    

    /**
     * Constructor of OrganizerController for a logged in user.
     *
     * @param programController pre-defined programController
     */
    public OrganizerController(ProgramController programController) {
        super(programController);
    }

    /**
     * Build an event by giving the title, time, room ID, and speaker ID
     *
     * @param title the title of the event
     * @param time the time of the event
     * @param room the room of the event
     * @param speaker the speaker of the event
     * @return true if the event be created successfully.
     */
    public boolean createEvent(String title, LocalDateTime time, UUID room, UUID speaker){
        EventBuilder newEvent = new EventBuilder();
        newEvent.setTitle(title);
        newEvent.setTime(time);
        newEvent.setRoom(room);
        newEvent.setSpeaker(speaker);
        return eventController.createEvent(newEvent);

    }

    /**
     * Update the time of the event
     * @param event the uuid of the event
     * @param time the new time of the event
     * @return true if the event's time have been successfully update or the new time has no difference with the
     * old time. Return false if the new time is conflict with other event and the time haven't been successfully
     * updated.
     */
    public boolean updateTime(UUID event, LocalDateTime time){
        eventController.updateEventInfo(event, )

    }

    /**
     * update the title of the event
     * @param event the uuid of the event
     * @param title the new title of the event
     */
    public void updateTitle(UUID event, String title){

    }

    /**
     * Add a new speaker to the event
     * @param event the uuid of the event
     * @param speaker the new speaker of the event
     * @return true if the new speaker have been successfully added or the new speaker is already inside the
     * old speakers' list. Return false if the time of the new speaker is not available
     */
    public boolean addSpeaker(UUID event, UUID speaker){

    }

    /**
     * remove a speaker from the event
     * @param event
     * @param speaker
     */
    public void removeSpeaker(UUID event, UUID speaker){

    }

    /**
     * update the room of the event
     * @param event the uuid of the event
     * @param room the new room of the event
     * @return true if room of the event have been successfully update of the new room has no difference with the
     * old room. Return false if the room has been occupied at that time
     */
    public boolean updateRoom(UUID event, UUID room){

    }


    public String getEventInfo(String eventTitle){

    }





}
