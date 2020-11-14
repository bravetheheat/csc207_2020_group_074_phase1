package main.controllers;

import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.*;

public class InboxController {
    MessageManager messageManager;
    InboxManager inboxManager;
    UsersManager usersManager;

    public InboxController(MessageManager messageManager, InboxManager inboxManager, UsersManager usersManager) {
        this.messageManager = messageManager;
        this.inboxManager = inboxManager;
        this.usersManager = usersManager;
    }

    public Map<UUID, String> getMessagesOfUser(UUID userId) {
        List<UUID> userMessages = this.inboxManager.getMessagesOfUser(userId);
        Map<UUID, String> messageDict = new LinkedHashMap<>();
        for (UUID messageId:  userMessages) {
            messageDict.put(messageId, getMessageString(messageId));
        }
        return messageDict;
    }

    private String getMessageString(UUID messageId) {
        String messageText = this.messageManager.retrieveMessageText(messageId);
        String messageDate = this.messageManager.retrieveMessageDate(messageId).toString();
        String messageSender = this.getMessageSender(messageId);

        return messageSender + " " + messageDate;

    }

    private String getMessageSender(UUID messageId) {
        UUID userId = this.messageManager.retrieveMessageSender(messageId);
        String sender = this.usersManager.getUsernameFromID(userId);
        return sender;

    }


}
