package main.presenters;

import main.controllers.ProgramController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ChatRoomScreen extends Screen {

    UUID myUserId;
    UUID chatRoomId;

    public ChatRoomScreen(ProgramController programController, UUID chatRoomId) {
        super(programController);
        RegisterScreen registerScreen = new RegisterScreen(programController);
        myUserId = programController.getUsersManager().getIDFromUsername(registerScreen.username);
        this.chatRoomId = chatRoomId;
    }

    @Override
    public void start() {
        System.out.println("Options: 1. See List of Participants; " +
                "2. Delete This Conversation (Enter 1 or 2)");
        System.out.println("Below is the chat history:");
        viewMessages(chatRoomId);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                viewParticipants();
            case 2:
                deleteChatRoom();
            default:
                System.out.println("Invalid Input!");
        }
    }

    public void viewMessages(UUID chatRoomId) {
        programController.getChatRoomManager().fetchMessagesFromChatRoom(chatRoomId);
        System.out.println("==============");
    }

    public void viewParticipants() {
        List<UUID> listOfUserIds = programController.getChatRoomManager().
                fetchUsersFromChatRoom(myUserId);
        for (UUID id : listOfUserIds) {
            System.out.println(programController.getUsersManager().fetchUser(id));
        }
    }

    public void deleteChatRoom() {

    }
}
