package main.usecases;

import main.entities.Inbox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InboxManager {

    private final Map<String, Inbox> inboxes = new HashMap<>();

    public InboxManager() {

    }

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

    public List<String> getMessagesOfUser(String userId) {
        for (Inbox x : inboxes.values()) {
            if (userId.equals(x.getUser())) {
                return x.getMessages();
            }
        }
        return new LinkedList<>();
    }

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
