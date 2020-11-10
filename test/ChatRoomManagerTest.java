import main.usecases.ChatRoomManager;

import org.junit.*;

public class ChatRoomManagerTest {
    ChatRoomManager chatRoomManager1, chatRoomManager2;

    @Before
    public void setUp() {
        chatRoomManager1 = new ChatRoomManager();
        chatRoomManager2 = new ChatRoomManager();
    }
}
