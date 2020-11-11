package main.controllers;

import main.entities.Event;
import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;

import java.util.*;

/**
 * The UserController is an abstract class with functionality related to basic user actions: checking and
 * managing contacts, checking and sending messages, and checking, registering, and dropping from events.
 *
 * @author Yi Tao Li
 * @version 1.2
 * @since 2020-11-09
 */
public abstract class UserController {


    protected UUID loggedInUser;
    protected ContactsManager contactsManager;
    protected ChatRoomManager chatRoomManager;
    protected MessageController messageController;
    protected EventController eventController;

    /**
     * Constructor of UserController for a logged in user.
     *
     * @param programController pre-defined programController
     */
    public UserController(ProgramController programController) {
        this.loggedInUser = programController.getAuthController().fetchLoggedInUser();
        this.contactsManager = programController.getContactsManager();
        this.chatRoomManager = programController.getChatRoomManager();
        this.messageController = programController.getMessageController();
        this.eventController = programController.getEventController();
    }

    /**
     * Abstract method for checking the ChatRooms that user is participating in.
     */
    public abstract List<UUID> checkChatRooms();

    /**
     * Abstract method for checking the Messages in a particular ChatRoom.
     */
    public abstract List<UUID> checkMessages(UUID chatRoom);

    /**
     * Abstract method for checking a user's contacts.
     */
    public abstract List<UUID> checkContacts();

    /**
     * Abstract method for adding another user to the user's list of contacts.
     */
    public abstract boolean addContact(UUID user);

    /**
     * Abstract method for removing another user from the user's list of contacts.
     */
    public abstract boolean removeUser(UUID user);

    /**
     * Sends a message to a contact and returns true.
     *
     * @param text String of the message that the user is trying to send
     * @param chatRoom UUID of the ChatRoom that the user is trying to send their message to
     * @param messageManager pre-defined MessageManager
     * @param chatRoomManager pre-defined ChatRoomManager
     */
    public boolean sendMessage(String text, UUID chatRoom, MessageManager messageManager,
                               ChatRoomManager chatRoomManager) {
        UUID message = messageManager.createMessage(text, this.loggedInUser);
        chatRoomManager.addMessageToChatRoom(chatRoom, message);
        return true;
    }

    /**
     * Returns a list of events in the conference.
     *
     * @param eventController pre-defined EventController
     */
    public ArrayList<Event> checkEvents(EventController eventController) {
        return eventController.getAllEvents();
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
     * @param eventController pre-defined EventController
     */
    public boolean registerForEvent(Event event, EventController eventController) {
        if (!this.checkRegisteredEvents(eventController).contains(event)) {
            return eventController.addUser(event, this.loggedInUser);
        }
        return false;
    }

    /**
     * Removes an event from the user's list of registered events.
     *
     * @param event UUID of the event that the user is trying to remove
     * @param eventController pre-defined EventController
     */
    public boolean dropOutFromEvent(Event event, EventController eventController) {
        return eventController.removeUser(event, this.loggedInUser);
    }

}