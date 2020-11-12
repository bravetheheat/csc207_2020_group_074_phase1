import main.entities.ChatRoom;
import main.entities.User;
import main.usecases.UserFactory;
import main.usecases.ChatRoomManager;

import org.junit.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Test cases for <code>ChatRoomManager</code>
 *
 * @author Steven Yuan
 * @version 1.0
 * @since 2020-11-10
 */
public class ChatRoomManagerTest {
    ChatRoomManager chatRoomManager1, chatRoomManager2;
    Map<UUID, ChatRoom> chatRoomList1, chatRoomList2;
    ChatRoom chatRoom1, chatRoom2, chatRoom3;

    // create id's
    UUID id1 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    UUID id3 = UUID.randomUUID();

    @Before
    public void setUp() {
        // create users
        User user1 = (new UserFactory()).getUser("user1@gmail.com", "user1", "Attendee");
        User user2 = (new UserFactory()).getUser("user2@gmail.com", "user2", "Organizer");
        User user3 = (new UserFactory()).getUser("user3@gmail.com", "user3", "Attendee");

        // create ChatRoom objects
        chatRoom1 = new ChatRoom(Arrays.asList(user1.getId(), user2.getId()));
        chatRoom2 = new ChatRoom(Arrays.asList(user1.getId(), user2.getId()));
        chatRoom3 = new ChatRoom(Arrays.asList(user2.getId(), user3.getId()));
        System.out.println(chatRoom1.getParticipants());

        // create ChatRoomManager objects
        chatRoomManager1 = new ChatRoomManager();

        chatRoomList1 = new HashMap<>();
        chatRoomList2 = new HashMap<>();
        chatRoomList1.put(id1, chatRoom1);
        chatRoomList1.put(id2, chatRoom2);
        chatRoomList2.put(id3, chatRoom3);
        chatRoomManager2 = new ChatRoomManager(chatRoomList1);
    }

    @Test
    public void testCreateChatRoom() {
        UUID chatRoom2ID = chatRoomManager2.createChatRoom();
        Assert.assertEquals(3, chatRoomList1.size());
        Assert.assertTrue(chatRoomList1.containsKey(chatRoom2ID));
    }

    @Test
    public void testDeleteChatRoom() {
        UUID chatRoom1ID = chatRoomManager1.createChatRoom();
        chatRoomManager1.deleteChatRoom(chatRoom1ID);
        Assert.assertNull(chatRoomManager1.fetchChatRoom(chatRoom1ID));
    }

    @Test
    public void testFetchChatRoom() {
        Assert.assertEquals(chatRoom1, chatRoomManager2.fetchChatRoom(id1));
        Assert.assertEquals(chatRoom2, chatRoomManager2.fetchChatRoom(id2));
        chatRoomManager2 = new ChatRoomManager(chatRoomList2);
        Assert.assertEquals(chatRoom3, chatRoomManager2.fetchChatRoom(id3));
    }

    @Test
    public void testAddMessageToChatRoom() {

    }

    @Test
    public void testFetchMessagesFromChatRoom() {

    }

    @Test
    public void testFetchUserChatRooms() {

    }
}

