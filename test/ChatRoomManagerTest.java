import main.entities.ChatRoom;
import main.entities.User;
import main.usecases.UserFactory;
import main.usecases.ChatRoomManager;

import org.junit.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatRoomManagerTest {
    ChatRoomManager chatRoomManager1, chatRoomManager2;
    Map<UUID, ChatRoom> chatRoomList;
    ChatRoom chatRoom1, chatRoom2;

    @Before
    public void setUp() {
        // create users
        User user1 = (new UserFactory()).getUser("user1@gmail.com", "user1", "Attendee");
        User user2 = (new UserFactory()).getUser("user1@gmail.com", "user1", "Organizer");
        // create ChatRoom objects
        chatRoom1 = new ChatRoom(Arrays.asList(user1.getId(), user2.getId()));
        chatRoom2 = new ChatRoom(Arrays.asList(user1.getId(), user2.getId()));
        System.out.println(chatRoom1.getParticipants());

        // create id's
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        // create ChatRoomManager objects
        chatRoomManager1 = new ChatRoomManager();

        chatRoomList = new HashMap<>();
        chatRoomList.put(id1, chatRoom1);
        chatRoomList.put(id2, chatRoom2);
        chatRoomManager2 = new ChatRoomManager(chatRoomList);
    }

    @Test
    public void testCreateChatRoom() {
        UUID chatRoom2ID = chatRoomManager2.createChatRoom();
        Assert.assertEquals(3, chatRoomList.size());
        Assert.assertTrue(chatRoomList.containsKey(chatRoom2ID));
    }
}

