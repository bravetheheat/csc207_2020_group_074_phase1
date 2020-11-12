package main.controllers;

import main.entities.Attendee;
import main.entities.Event;
import main.usecases.ChatRoomManager;
import main.usecases.MessageManager;

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
     * Returns a list of ChatRooms that the user is participating in.
     */
    public List<UUID> checkChatRooms() {
        return this.chatRoomManager.fetchUserChatRooms(this.loggedInUser);
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
     * Returns a list of the user's Contacts.
     */
    public List<UUID> checkContacts() {
        return this.contactsManager.getContactList(this.loggedInUser);
    }

    /**
     * Adds another user to the user's contact list if they are not already contacts and returns true if the contact is
     * added.
     *
     * @param user UUID of the other user who user is trying to add
     */
    public boolean addContact(UUID user) {
        if (this.checkContacts().contains(user)){
            return false;
        }
        contactsManager.addUser(this.loggedInUser, user);
        return true;
    }

    /**
     * Removes another user from the user's contact list and returns true.
     *
     * @param user UUID of the other user who using is trying to remove
     */
    public boolean removeContact(UUID user) {
        contactsManager.removeUser(this.loggedInUser, user);
        return true;
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
