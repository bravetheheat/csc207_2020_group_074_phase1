package main.usecases;

import main.entities.ChatRoom;

import java.util.*;

/**
 * <code>ChatRoomManager</code> adds messages to each user's chat rooms
 *
 * @author Steven Yuan, David Zhao
 * @version 1.1
 * @since 2020-11-03
 */
public class ChatRoomManager {

    private final Map<UUID, ChatRoom> chatRoomList;
    private final Map<UUID, List<UUID>> usersToChatRoom;

    /**
     * Default constructor that instantiates a <code>ChatRoomManager</code> object
     */
    public ChatRoomManager() {
        this.chatRoomList = new HashMap<>();
        this.usersToChatRoom = new HashMap<>();

    }

    /**
     * Class constructor that creates a <code>Hashmap</code> that
     * maps user id to chat rooms
     *
     * @param chatRoomList A list of chat rooms for all the users
     */
    public ChatRoomManager(Map<UUID, ChatRoom> chatRoomList) {
        this.chatRoomList = chatRoomList;
        this.usersToChatRoom = new HashMap<>();
        for (ChatRoom chatRoom : chatRoomList.values()) {
            this.populateUsersToChatRoom(chatRoom);
        }
    }

    private void populateUsersToChatRoom(ChatRoom chatRoom) {
        for (UUID participant : chatRoom.getParticipants()) {
            if (!this.usersToChatRoom.containsKey(participant)) {
                List<UUID> chatRoomIds = new ArrayList<>();
                chatRoomIds.add(chatRoom.getId());
                this.usersToChatRoom.put(participant, chatRoomIds);
            } else {
                this.usersToChatRoom.get(participant).add(chatRoom.getId());
            }
        }
    }

    public UUID createChatRoom() {
        ChatRoom newChatRoom = new ChatRoom();
        UUID newChatRoomId = newChatRoom.getId();
        this.chatRoomList.put(newChatRoomId, newChatRoom);
        return newChatRoomId;
    }

    public UUID createChatRoom(List<UUID> participants) {
        ChatRoom newChatRoom = new ChatRoom(participants);
        UUID newChatRoomId = newChatRoom.getId();
        this.chatRoomList.put(newChatRoomId, newChatRoom);
        return newChatRoomId;
    }

    public void deleteChatRoom(UUID chatRoomId) {
        this.chatRoomList.remove(chatRoomId);
    }

    public ChatRoom fetchChatRoom(UUID chatRoomId) {
        return this.chatRoomList.get(chatRoomId);
    }

    public void addMessageToChatRoom(UUID chatRoomId, UUID messageId) {
        ChatRoom chatRoom = this.chatRoomList.get(chatRoomId);
        chatRoom.addMessage(messageId);
    }

    public List<UUID> fetchMessagesFromChatRoom(UUID chatRoomId) {
        ChatRoom chatRoom = this.chatRoomList.get(chatRoomId);
        return chatRoom.getMessages();
    }

    public List<UUID> fetchUserChatRooms(UUID userId) {
        return this.usersToChatRoom.get(userId);
    }


}
