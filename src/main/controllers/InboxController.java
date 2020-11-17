package main.controllers;

import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.*;

public class InboxController {
    MessageManager messageManager;
    InboxManager inboxManager;
    UsersManager usersManager;

    public InboxController(ProgramController programController) {

        this.messageManager = programController.getMessageManager();
        this.inboxManager = programController.getInboxManager();
        this.usersManager = programController.getUsersManager();
    }

    public Map<String, String> getMessagesOfUser(String userId) {
        List<String> userMessages = this.inboxManager.getMessagesOfUser(userId);
        Map<String, String> messageDict = new LinkedHashMap<>();
        for (String messageId:  userMessages) {
            messageDict.put(messageId, getMessageString(messageId));
        }
        return messageDict;
    }

    public String getMessageString(String messageId) {
        String messageText = this.messageManager.retrieveMessageText(messageId);
        String messageDate = this.messageManager.retrieveMessageDate(messageId).toString();
        String messageSender = this.getMessageSender(messageId);

        return messageSender + ": " + messageText + "\n" + "[Sent at " + messageDate + "]";

    }

    private String getMessageSender(String messageId) {
        String userId = this.messageManager.retrieveMessageSender(messageId);
        String sender = this.usersManager.getUsernameFromID(userId);
        return sender;

    }


}
