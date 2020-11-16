package main.usecases;

import main.entities.Message;
import main.gateways.Gateway;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>MessageManager</code> stores all the messages and assigns an ID to
 * each <code>Message</code>
 *
 * @author Steven Yuan, David Zhao
 * @version 2.0
 * @since 2020-11-03
 */
public class MessageManager {

    private Map<String, Message> messageList;

    /**
     * Default constructor that instantiates a <code>MessageManager</code> object
     */
    public MessageManager() {
        this.messageList = new HashMap<>();
    }

    /**
     * Create a <code>Message</code> with specified text and sender
     *
     * @param text   The content of the <code>Message</code>
     * @param sender The sender of the message
     * @return String The ID of the message created
     */
    public String createMessage(String text, String sender) {
        Message newMessage = new Message(text, sender);
        String newMessageId = newMessage.getId();
        this.messageList.put(newMessageId, newMessage);
        return newMessageId;
    }

    /**
     * Get the content of a message
     *
     * @param messageId ID of the message retrieved
     * @return The content of the message
     */
    public String retrieveMessageText(String messageId) {
        Message message = this.messageList.get(messageId);
        return message.getText();
    }

    /**
     * Get the date of a message
     *
     * @param messageId ID of the message retrieved
     * @return date of the message
     */
    public LocalDateTime retrieveMessageDate(String messageId) {
        Message message = this.messageList.get(messageId);
        return message.getDate();
    }

    /**
     * Get the sender of a message
     *
     * @param messageId ID of the message retrieved
     * @return the ID of the sender
     */
    public String retrieveMessageSender(String messageId) {
        Message message = this.messageList.get(messageId);
        return message.getSender();
    }

    /**
     * Saves current store of Message to gateway
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void saveMessagesToGateway(Gateway gateway) {
        List<Message> messages = new ArrayList<>();
        messages.addAll(this.messageList.values());
        gateway.saveMessages(messages);
    }

    /**
     * Imports and replaces current store of Message with Gateway-provided store
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void loadMessagesFromGateway(Gateway gateway) {
        this.messageList = new HashMap<>();
        List<Message> messages = gateway.loadMessages();
        for (Message message : messages) {
            this.messageList.put(message.getId(), message);
        }
    }


}
