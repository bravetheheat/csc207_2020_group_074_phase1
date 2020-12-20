package main.usecases;

import main.entities.Inbox;
import main.gateways.Gateway;

import java.util.*;

/**
 * InboxManager handles the retrieving and management of Inboxes
 *
 * @author David Zhao
 */
public class InboxManager {

    private Map<String, Inbox> inboxes;

    /**
     * No-arg constructor
     */
    public InboxManager() {

        this.inboxes = new HashMap<>();
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


    /**
     * Retrieves the IDs of Messages in a specified User's inbox.
     *
     * @param userId ID of User
     * @return List of Message IDs
     */
    public List<String> getMessagesOfUser(String userId) {
        List<String> allMessages = new ArrayList<>();
        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
//                return x.getMessages();
                allMessages.addAll(x.getMessages());
                }
            }
//        return new LinkedList<>();
        return allMessages;
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

    /**
     * Saves Inbox to Gateway
     * @param gateway an implementation of Gateway
     */
    public void saveToGateway(Gateway gateway) {
        List<Inbox> inboxes = new ArrayList<>();
        inboxes.addAll(this.inboxes.values());
        gateway.saveInboxes(inboxes);
    }

    /**
     * Loads Inboxes from Gateway
     * @param gateway an implementation of Gateway
     */
    public void loadFromGateway(Gateway gateway) {
        this.inboxes = new HashMap<>();
        List<Inbox> newInboxes = gateway.loadInboxes();
        for (Inbox inbox : newInboxes) {
            this.inboxes.put(inbox.getId(), inbox);
        }
    }
}
