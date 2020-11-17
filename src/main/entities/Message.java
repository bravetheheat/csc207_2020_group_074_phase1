package main.entities;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Message is a uniquely-identifiable object that stores a message and its sender
 *
 * @author David Zhaos
 */
public class Message {

    private String id;
    private String text;
    private LocalDateTime time;
    private String sender;

    /**
     * Empty constructor for deserialization.
     */
    public Message(){

    }

    /**
     * Class constructor
     *
     * @param text   the text of the message
     * @param sender the String of the sender
     */
    public Message(String text, String sender) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.sender = sender;
        this.time = LocalDateTime.now();
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
     * @return String of the sender of the message
     */
    public String getSender() {
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
    public String getId() {
        return this.id;
    }

    /**
     * String representation of the message
     *
     * @return a string representation of the message
     */
    public String toString() {
        return this.sender + " at " + this.time.toString() + ": " + this.text;
    }


}