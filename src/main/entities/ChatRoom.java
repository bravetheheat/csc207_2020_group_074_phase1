package main.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * The ChatRoom holds the messages and participants of a conversation.
 *
 * @author David Zhao
 * @version 1.0
 * @since 2020-10-31
 */
public class ChatRoom {

    private final UUID id = UUID.randomUUID();
    private final List<UUID> messages = new LinkedList<UUID>();
    private final List<UUID> participants = new ArrayList<UUID>();

    /**
     * Class constructor that defaults to an empty chatroom.
     */
    public ChatRoom() {
    }

    /**
     * Class constructor that opens with a number of users
     *
     * @param participants A list of the UUIDS of the participants in the chatroom
     */
    public ChatRoom(List<UUID> participants) {
        this.participants.addAll(participants);
    }

    /**
     * Gets the unique identifier of the chatroom
     *
     * @return the UUID of the chatroom
     */
    public UUID getId() {
        return this.id;

    }


    /**
     * Adds a participant to the chatroom
     *
     * @param participant the UUID of the participant to add to the chatroom.
     */
    public void addParticipant(UUID participant) {
        this.participants.add(participant);
    }

    /**
     * Gets the list of participants in the chatroom
     *
     * @return the list of UUIDs of the participants in chatroom
     */
    public List<UUID> getParticipants() {
        return this.participants;
    }

    /**
     * Removes a participant from the chatroom
     *
     * @param participant UUID of the participant to remove
     */
    public void removeParticipant(UUID participant) {
        this.participants.remove(participant);
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
