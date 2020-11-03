package main.usecases;

import main.entities.ChatRoom;

import java.util.*;

/**
 * <code>MessageManager</code> add messages to <code>ChatRoom</code>
 *
 * @author Steven Yuan
 * @version 1.0
 * @since 2020-11-03
 */
public class MessageManager {

    private List<ChatRoom> chatRoomList;
    private Map<UUID, List<ChatRoom>> usersToChatRoom;

    public MessageManager() {
        chatRoomList = new ArrayList<>();
        usersToChatRoom = new HashMap<>();
        for (ChatRoom chatRoom : chatRoomList) {
            for (UUID participant : chatRoom.getParticipants()) {
                if (!usersToChatRoom.containsKey(participant)) {
                    List<ChatRoom> chatRooms = new ArrayList<>();
                    chatRooms.add(chatRoom);
                    usersToChatRoom.put(participant, chatRooms);
                }
                else {
                    usersToChatRoom.get(participant).add(chatRoom);
                }
            }
        }
    }

    public void addMessages(UUID message) {
        for (UUID participant : usersToChatRoom.keySet()) {
            for (ChatRoom chatRoom : usersToChatRoom.get(participant)) {
                chatRoom.addMessage(message);
            }
        }
    }
}
