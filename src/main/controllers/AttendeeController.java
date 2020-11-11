package main.controllers;

import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;

import java.util.List;
import java.util.UUID;

/**
 * The AttendeeController takes care of actions performed by an Attendee: checking and managing contacts,
 * checking and sending messages, and checking, registering, and dropping from events.
 *
 * @author Yi Tao Li
 * @version 1.0
 * @since 2020-11-09
 */
public class AttendeeController extends UserController{

    public AttendeeController(AuthController authController) {
        super(authController);
    }

    /**
     * Returns a list of ChatRooms that the user is participating in.
     *
     * @param chatRoomManager pre-defined ChatRoomManager
     */
    public List<UUID> checkChatRooms(ChatRoomManager chatRoomManager) {
        return chatRoomManager.fetchUserChatRooms(this.loggedInUser);
    }

    /**
     * Returns a list of Messages of a ChatRoom that the user is participating in.
     *
     * @param chatRoom UUID of the user's selected ChatRoom
     * @param chatRoomManager pre-defined ChatRoomManager
     */
    public List<UUID> checkMessages(UUID chatRoom, ChatRoomManager chatRoomManager) {
        return chatRoomManager.fetchMessagesFromChatRoom(chatRoom);
    }

    /**
     * Returns a list of the user's Contacts.
     *
     * @param contactsManager pre-defined ContactsManager
     */
    public List<UUID> checkContacts(ContactsManager contactsManager) {
        return contactsManager.getContactList(this.loggedInUser);
    }

    /**
     * Adds another user to the user's contact list if they are not already contacts and returns true if the contact is
     * added.
     *
     * @param user UUID of the other user who user is trying to add
     * @param contactsManager pre-defined ContactsManager
     */
    public boolean addContact(UUID user, ContactsManager contactsManager) {
        if (this.checkContacts(contactsManager).contains(user)){
            return false;
        }
        contactsManager.addUser(this.loggedInUser, user);
        return true;
    }

    /**
     * Removes another user from the user's contact list and returns true.
     *
     * @param user UUID of the other user who using is trying to remove
     * @param contactsManager pre-defined ContactsManager
     */
    public boolean removeUser(UUID user, ContactsManager contactsManager) {
        contactsManager.removeUser(this.loggedInUser, user);
        return true;
    }

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

}
