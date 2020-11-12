package main.controllers;

import main.entities.Event;
import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;

import java.util.*;

/**
 * The UserController is an abstract class for controllers of all user types.
 *
 * @author Yi Tao Li
 * @version 1.3
 * @since 2020-11-12
 */
public abstract class UserController {


    protected UUID loggedInUser;
    protected ContactsManager contactsManager;
    protected ChatRoomManager chatRoomManager;
    protected MessageManager messageManager;
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
        this.messageManager = programController.getMessageManager();
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
     * Sends a message to a contact and returns true.
     *
     * @param text String of the message that the user is trying to send
     * @param chatRoom UUID of the ChatRoom that the user is trying to send their message to
     */
    public boolean sendMessage(String text, UUID chatRoom) {
        UUID message = this.messageManager.createMessage(text, this.loggedInUser);
        this.chatRoomManager.addMessageToChatRoom(chatRoom, message);
        return true;
    }

    public abstract ArrayList<Event> checkEvents();
}