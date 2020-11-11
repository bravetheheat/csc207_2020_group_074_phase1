package main.presenters;

import main.controllers.ProgramController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ChatRoomScreen extends Screen {

    UUID myUserId;

    public ChatRoomScreen(ProgramController programController) {
        super(programController);
        RegisterScreen registerScreen = new RegisterScreen(programController);
        myUserId = programController.getUsersManager().getIDFromUsername(registerScreen.username);
    }

    @Override
    public void start() {
        System.out.println("Options: 1. See List of Participants; " +
                "2. Delete This Conversation (Enter 1 or 2)");
        viewMessages();
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

    public void startChatRoomWithFriend() {
        System.out.println("Who do you want to chat with?\n");
        String friendUsername = scanner.nextLine();
        UUID friendUserId = programController.getUsersManager().getIDFromUsername(friendUsername);
        UUID chatRoomId = programController.getChatRoomManager().createChatRoom(
                Arrays.asList(myUserId, friendUserId));
    }

    public void viewMessages() {
        List<UUID> participantsIds = programController.getChatRoomManager().
                fetchUsersFromChatRoom(myUserId);
        List<String> lstOfFriendsInChatRoom = new ArrayList<>();
        for (UUID id : participantsIds) {
            if (!id.equals(myUserId)) {
                lstOfFriendsInChatRoom.add(programController.getUsersManager().
                        fetchUser(id).getUsername());
            }
        }
        System.out.println("Below is the chat history between you and " +
                lstOfFriendsInChatRoom);
        System.out.println("You can start chatting now:)");
        programController.getChatRoomManager().fetchMessagesFromChatRoom();
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
