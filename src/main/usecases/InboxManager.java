package main.usecases;

import main.entities.Inbox;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InboxManager {

    private Map<UUID, Inbox> inboxes = new HashMap<>();

    public InboxManager() {

    }

    public UUID getInboxUUIDFromUserUUID(UUID userId) {

    }
}
