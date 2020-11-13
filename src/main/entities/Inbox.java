package main.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * The Inbox holds the messages of a user;
 *
 * @author David Zhao
 * @version 2.0
 * @since 2020-11-13
 */
public class Inbox {

    private final UUID id = UUID.randomUUID();
    private final List<UUID> messages = new LinkedList<UUID>();
    private final UUID user;

    /**
     * Class constructor that defaults to an empty chatroom.
     */
    public Inbox(UUID user) {
        this.user = user;

    }


    /**
     * Gets the unique identifier of the chatroom
     *
     * @return the UUID of the chatroom
     */
    public UUID getId() {
        return this.id;
    }

    public UUID getUser() {
        return this.user;
    }

    /**
     * Adds a message to the chatroom
     *
     * @param message UUID of the message to add
     */
    public void addMessage(UUID message) {
        this.messages.add(message);
    }

    /**
     * Gets the messages in the room
     *
     * @return list of UUIDs of the messages in the room.
     */
    public List<UUID> getMessages() {
        return this.messages;
    }


}
