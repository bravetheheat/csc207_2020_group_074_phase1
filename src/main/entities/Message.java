package main.entities;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Message is a uniquely-identifiable object that stores a message and its sender
 *
 * @author David Zhao
 * @version 1.0
 * @since 2020-10-31
 */
public class Message {

    private final UUID id = UUID.randomUUID();
    private final String text;
    private final LocalDateTime time = LocalDateTime.now();
    private final UUID sender;

    /**
     * Class constructor
     *
     * @param text   the text of the message
     * @param sender the UUID of the sender
     */
    public Message(String text, UUID sender) {
        this.text = text;
        this.sender = sender;
    }

    /**
     * Gets the text stored in the message
     *
     * @return text of the message
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the sender of the message
     *
     * @return UUID of the sender of the message
     */
    public UUID getSender() {
        return this.sender;
    }

    /**
     * Gets the date the message was created
     *
     * @return the date the message was created
     */
    public LocalDateTime getDate() {
        return this.time;
    }

    /**
     * Gets the unique identifier of the message
     *
     * @return id of the message
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * String representation of the message
     *
     * @return a string representation of the message
     */
    public String toString() {
        return this.sender.toString() + " at " + this.time.toString() + ": " + this.text;
    }


}