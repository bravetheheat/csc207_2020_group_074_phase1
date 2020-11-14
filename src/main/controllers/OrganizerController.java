package main.controllers;

import java.time.LocalDateTime;
import main.usecases.EventBuilder;
import main.usecases.UsersManager;

import java.util.*;

/**
 * Apart from the responsibilities listed in the UserController, the OrganizerController handles advance actions
 * which could be done by an organizer: checking and managing events, creating speaker accounts, and creating
 * rooms.
 *
 * @author Ruoming Ren
 * @version 2.0
 * @since 2020-11-10
 */

public class OrganizerController extends AttendeeController{

    UsersManager usersManager;


    /**
     * Constructor of OrganizerController for a logged in user.
     *
     * @param programController pre-defined programController
     */
    public OrganizerController(ProgramController programController) {
        super(programController);
        this.usersManager = programController.getUsersManager();
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
     * Remove the event from the schedule and return true if the event have been successfully removed
     *
     * @param event the uuid of the event
     * @return true if the event have been successfully removed
     */
    public boolean removeEvent(UUID event){
        return eventController.removeEvent(event);
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
        UUID roomId = eventController.getSingleEventInfo(event).getRoomId();
        return eventController.updateEventInfo(event, time, roomId);
    }

    /**
     * change the speaker of the event
     * @param event the uuid of the event
     * @param speaker the new speaker of the event
     * @return true if the new speaker have been successfully updated or the new speaker is already inside the
     * old speakers' list. Return false if the time of the new speaker is not available
     */
    public boolean updateSpeaker(UUID event, UUID speaker){

        return eventController.removeSpeaker(event, eventController.getSingleEventInfo(event).getSpeakerID())
                && eventController.addSpeaker(event, speaker);

    }

    /**
     * update the room of the event
     * @param event the uuid of the event
     * @param room the new room of the event
     * @return true if room of the event have been successfully update of the new room has no difference with the
     * old room. Return false if the room has been occupied at that time
     */
    public boolean updateRoom(UUID event, UUID room){
        LocalDateTime time = eventController.getSingleEventInfo(event).getTime();
        return eventController.updateEventInfo(event, time, room);
    }

    /**
     * create a new speaker account
     * @param userName the user name of the speaker
     * @param password the password of the speaker
     * @return true if the account have been successfully created
     */
    public boolean createSpeaker(String userName, String password){
        return usersManager.addUser(userName, password, "Speaker");
    }

    public boolean createRoom(){

    }







}
