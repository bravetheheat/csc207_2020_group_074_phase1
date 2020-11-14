package main.controllers;

import main.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The AttendeeController takes care of actions performed by an Attendee: checking and managing contacts,
 * checking and sending messages, and checking, registering, and dropping from events.
 *
 * @author Yi Tao Li
 * @version 1.2
 * @since 2020-11-12
 */
public class AttendeeController extends UserController{


    public AttendeeController(ProgramController programController) {
        super(programController);
    }


    /**
     * Returns a list of Messages of a ChatRoom that the user is participating in.
     *
     * @param chatRoom UUID of the user's selected ChatRoom
     */
    public List<UUID> checkMessages(UUID chatRoom) {
        return this.chatRoomManager.fetchMessagesFromChatRoom(chatRoom);
    }

    /**
     * Returns a list of events in the conference.
     */
    public ArrayList<Event> checkEvents() {
        return this.eventController.getAllEvents();
    }

    /**
     * Returns a list of events the user is registered in.
     *
     * @param eventController pre-defined EventController
     */
    public ArrayList<Event> checkRegisteredEvents(EventController eventController) {
        return eventController.getUserEvents(this.loggedInUser);
    }

    /**
     * Registers user in an event and returns true if they were not already registered.
     *
     * @param event UUID of the event that the user is trying to register for
     */
    public boolean registerForEvent(UUID event) {
        // currently checkRegisteredEvents returns an ArrayList of Event and not UUID
        if (!this.checkRegisteredEvents(this.eventController).contains(event)) {
            return eventController.addUser(event, this.loggedInUser);
        }
        return false;
    }

    /**
     * Removes an event from the user's list of registered events.
     *
     * @param event UUID of the event that the user is trying to remove
     */
    public boolean dropOutFromEvent(UUID event) {
        return this.eventController.removeUser(event, this.loggedInUser);
    }
}
