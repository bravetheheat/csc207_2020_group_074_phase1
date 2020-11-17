package main.usecases;

import main.entities.Inbox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * InboxManager handles the retrieving and management of Inboxes
 *
 * @author David Zhao
 */
public class InboxManager {

    private final Map<String, Inbox> inboxes = new HashMap<>();

    /**
     * No-arg constructor
     */
    public InboxManager() {
    }

    /**
     * Creates a new Inbox for a specified User
     *
     * @param userId ID of User
     */
    public void createInboxForUser(String userId) {
        Inbox newInbox = new Inbox(userId);
        this.inboxes.put(userId, newInbox);
    }

    public String getInboxUUIDFromUserUUID(String userId) {

        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
                return x.getId();
            }
        }
        return null;
    }

    /**
     * Retrieves the IDs of Messages in a specified User's inbox.
     *
     * @param userId ID of User
     * @return List of Message IDs
     */
    public List<String> getMessagesOfUser(String userId) {
        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
                return x.getMessages();
            }
        }
        return new LinkedList<>();
    }

    /**
     * Inserts a message into a specified User's inbox
     *
     * @param message  ID of Message
     * @param receiver ID of User receiving the message
     */
    public void putMessageInToInbox(String message, String receiver) {

        Inbox box = this.inboxes.get(receiver);
        if (box == null) {
            this.createInboxForUser(receiver);
            this.putMessageInToInbox(message, receiver);
            return;
        }
        box.addMessage(message);

    }
}
