package main.usecases;

import main.entities.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <code>MessageManager</code> adds messages to each user's chat rooms
 *
 * @author Steven Yuan, David Zhao
 * @version 1.2
 * @since 2020-11-03
 */
public class MessageManager {

    private final Map<UUID, Message> messageList;

    /**
     * Default constructor that instantiates a <code>MessageManager</code> object
     */
    public MessageManager() {
        this.messageList = new HashMap<>();
    }

    public MessageManager(List<Message> messageList) {
        this.messageList = new HashMap<>();
        for (Message message : messageList) {
            UUID messageId = message.getId();
            this.messageList.put(messageId, message);
        }
    }

    public UUID createMessage(String text, UUID sender) {
        Message newMessage = new Message(text, sender);
        UUID newMessageId = newMessage.getId();
        this.messageList.put(newMessageId, newMessage);
        return newMessageId;
    }

    public String retrieveMessageText(UUID messageId) {
        Message message = this.messageList.get(messageId);
        return message.getText();
    }

    public LocalDateTime retrieveMessageDate(UUID messageId) {
        Message message = this.messageList.get(messageId);
        return message.getDate();
    }

    public UUID retrieveMessageSender(UUID messageId) {
        Message message = this.messageList.get(messageId);
        return message.getSender();
    }


}
