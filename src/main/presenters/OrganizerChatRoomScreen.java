package main.presenters;

import main.controllers.ProgramController;

import java.util.UUID;

/**
 * @author Steven Yuan
 * @version 1.0
 * @since 2020-11-12
 */
public class OrganizerChatRoomScreen extends ChatRoomScreen {

    public OrganizerChatRoomScreen(ProgramController programController, UUID chatRoomId) {
        super(programController, chatRoomId);
    }

    public void chatRoomScreenStart() {
        System.out.println("Options: " + "0. Return to the main menu" +
                "1. View Messages" +
                "2. Start messaging" +
                "3. See List of Participants; " +
                "4. Rename this chat room" +
                "5. Delete this chat room" +
                "6. Send a message to all participants" +
                "(Enter a number)");
    }

    public void printMessageToAll() {

    }
}
