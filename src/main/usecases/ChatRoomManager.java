package main.usecases;

import main.entities.ChatRoom;

import java.util.*;

/**
 * <code>ChatRoomManager</code> adds messages to each user's chat rooms
 *
 * @author Steven Yuan, David Zhao
 * @version 1.3
 * @since 2020-11-10
 */
public class ChatRoomManager {

    private final Map<UUID, ChatRoom> chatRoomList;
    private final Map<UUID, List<UUID>> usersToChatRoom;
    private final Map<UUID, String> chatRoomIdToName;

    /**
     * Default constructor that instantiates a <code>ChatRoomManager</code> object
     */
    public ChatRoomManager() {
        this.chatRoomList = new HashMap<>();
        this.usersToChatRoom = new HashMap<>();
        this.chatRoomIdToName = new HashMap<>();
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
        this.chatRoomIdToName = new HashMap<>();
        for (ChatRoom chatRoom : chatRoomList.values()) {
            this.populateUsersToChatRoom(chatRoom);
        }
        for (UUID roomId : chatRoomList.keySet()) {
            chatRoomIdToName.put(roomId, chatRoomList.get(roomId).getName());
        }
    }

    /**
     * Helper method:
     * Map each user to his/her <code>ChatRoom</code>s
     *
     * @param chatRoom a chat room that contains users' messages
     */
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

    public Map<UUID, String> getChatRoomIdToName() {
        return this.chatRoomIdToName;
    }

    /**
     * Create a <code>ChatRoom</code>
     *
     * @return ID of the <code>ChatRoom</code> created
     */
    public UUID createChatRoom() {
        ChatRoom newChatRoom = new ChatRoom();
        UUID newChatRoomId = newChatRoom.getId();
        this.chatRoomList.put(newChatRoomId, newChatRoom);
        return newChatRoomId;
    }

    /**
     * Create a <code>ChatRoom</code> with a list of participants
     *
     * @param participants a list of <code>Users</code>
     * @return ID of the <code>ChatRoom</code> created
     */
    public UUID createChatRoom(List<UUID> participants, String name) {
        ChatRoom newChatRoom = new ChatRoom(participants, name);
        UUID newChatRoomId = newChatRoom.getId();
        this.chatRoomList.put(newChatRoomId, newChatRoom);
        return newChatRoomId;
    }

    /**
     * Delete a <code>ChatRoom</code>
     *
     * @param chatRoomId ID of the <code>ChatRoom</code> deleted
     */
    public void deleteChatRoom(UUID chatRoomId) {
        this.chatRoomList.remove(chatRoomId);
    }

    /**
     * Get a <code>ChatRoom</code> via ID
     *
     * @param chatRoomId ID of the <code>ChatRoom</code>
     * @return the <code>ChatRoom</code> associated with the specified ID
     */
    public ChatRoom fetchChatRoom(UUID chatRoomId) {
        return this.chatRoomList.get(chatRoomId);
    }

    /**
     * Add messages to a <code>ChatRoom</code>
     *
     * @param chatRoomId ID of the <code>ChatRoom</code> added
     * @param messageId  ID of the <code>Message</code> added
     */
    public void addMessageToChatRoom(UUID chatRoomId, UUID messageId) {
        ChatRoom chatRoom = this.chatRoomList.get(chatRoomId);
        chatRoom.addMessage(messageId);
    }

    /**
     * Get messages from a <code>ChatRoom</code>
     *
     * @param chatRoomId ID of the <code>ChatRoom</code>
     * @return a list of message IDs from the specified <code>ChatRoom</code>
     */
    public List<UUID> fetchMessagesFromChatRoom(UUID chatRoomId) {
        ChatRoom chatRoom = this.chatRoomList.get(chatRoomId);
        return chatRoom.getMessages();
    }

    /**
     * Get chat rooms of a <code>User</code>
     *
     * @param userId ID of the <code>User</code>
     * @return a list of IDs of the all the <code>ChatRoom</code>s
     */
    public List<UUID> fetchChatRoomsOfUser(UUID userId) {
        return this.usersToChatRoom.get(userId);
    }

    public List<UUID> fetchUsersFromChatRoom(UUID chatRoomId) {
        return chatRoomList.get(chatRoomId).getParticipants();
    }

}
