package main.usecases;

import main.entities.Inbox;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.LinkedList;

public class InboxManager {

    private final Map<UUID, Inbox> inboxes = new HashMap<>();

    public InboxManager() {

    }

    public void createInboxForUser(UUID userId) {
        Inbox newInbox = new Inbox(userId);
        this.inboxes.put(userId, newInbox);
    }

    public UUID getInboxUUIDFromUserUUID(UUID userId) {

        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
                return x.getId();
            }
        }
        return null;
    }

    public List<UUID> getMessagesOfUser(UUID userId) {
        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
                return x.getMessages();
            }
        }
        return new LinkedList<>();
    }

    public void putMessageInToInbox(UUID message, UUID receiver){

        Inbox box = this.inboxes.get(receiver);
        if (box == null) {
            this.createInboxForUser(receiver);
            this.putMessageInToInbox(message, receiver);
            return;
        }
        box.addMessage(message);

    }
}
