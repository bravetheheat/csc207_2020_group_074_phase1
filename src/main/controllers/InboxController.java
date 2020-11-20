package main.controllers;

import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * InboxController handles functionality related to retrieving inboxes and their messages
 */
public class InboxController {
    MessageManager messageManager;
    InboxManager inboxManager;
    UsersManager usersManager;

    /**
     * Base constructor for a controller class
     *
     * @param programController
     */
    public InboxController(ProgramController programController) {

        this.messageManager = programController.getMessageManager();
        this.inboxManager = programController.getInboxManager();
        this.usersManager = programController.getUsersManager();
    }

    /**
     * Retrieves a Map of the Messages from the Inbox of a given User ID
     *
     * @param userId A User ID
     * @return A Map of Message IDs and Message objects
     */
    public Map<String, String> getMessagesOfUser(String userId) {
        List<String> userMessages = this.inboxManager.getMessagesOfUser(userId);
        Map<String, String> messageDict = new LinkedHashMap<>();
        for (String messageId : userMessages) {
            messageDict.put(messageId, getMessageString(messageId));
        }
        return messageDict;
    }

    /**
     * Retrieves the stringified version of a Message
     * This is not equivalent to Message.toString() due to the special requirements of this controller
     *
     * @param messageId A given Message ID
     * @return A string representing that Message
     */
    public String getMessageString(String messageId) {
        String messageText = this.messageManager.retrieveMessageText(messageId);
        String messageDate = this.messageManager.retrieveMessageDate(messageId).toString();
        String messageSender = this.getMessageSender(messageId);

        return messageSender + ": " + messageText + "\n" + "[Sent at " + messageDate + "]";

    }

    /**
     * Retrieves the User ID of the sender of a Message
     *
     * @param messageId A given Message ID
     * @return A User ID
     */
    private String getMessageSender(String messageId) {
        String userId = this.messageManager.retrieveMessageSender(messageId);
        String sender = this.usersManager.getUsernameFromID(userId);
        return sender;

    }


}
