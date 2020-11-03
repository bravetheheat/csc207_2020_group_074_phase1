package main.usecases;

import main.entities.ChatRoom;

import java.util.*;

/**
 * <code>MessageManager</code> adds messages to each user's chat rooms
 *
 * @author Steven Yuan
 * @version 1.1
 * @since 2020-11-03
 */
public class MessageManager {

    private List<ChatRoom> chatRoomList;
    private Map<UUID, List<ChatRoom>> usersToChatRoom;

    /**
     * Default constructor that instantiates a <code>MessageManager</code> object
     */
    public MessageManager() {

    }

    /**
     * Class constructor that creates a <code>Hashmap</code> that
     * maps user id to chat rooms
     *
     * @param chatRoomList A list of chat rooms for all the users
     */
    public MessageManager(List<ChatRoom> chatRoomList) {
        this.chatRoomList = chatRoomList;
        usersToChatRoom = new HashMap<>();
        for (ChatRoom chatRoom : chatRoomList) {
            for (UUID participant : chatRoom.getParticipants()) {
                if (!usersToChatRoom.containsKey(participant)) {
                    List<ChatRoom> chatRooms = new ArrayList<>();
                    chatRooms.add(chatRoom);
                    usersToChatRoom.put(participant, chatRooms);
                } else {
                    usersToChatRoom.get(participant).add(chatRoom);
                }
            }
        }
    }

    /**
     * Store messages for all the users in each chat room
     * @param message ID of the message
     */
    public void addMessages(UUID message) {
        for (UUID participant : usersToChatRoom.keySet()) {
            for (ChatRoom chatRoom : usersToChatRoom.get(participant)) {
                chatRoom.addMessage(message);
            }
        }
    }
}
