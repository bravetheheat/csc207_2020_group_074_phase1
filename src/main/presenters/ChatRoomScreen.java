package main.presenters;

import main.controllers.ProgramController;

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
        System.out.println("Options: " + "1. View Messages" +
                "2. See List of Participants; " +
                "3. Rename this chat room" +
                "4. Delete this chat room (Enter the number)");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                viewMessages(chatRoomId);
            case 2:
                viewParticipants();
            case 3:
                System.out.println("Enter a new name for this chat room:");
                renameChatRoom();
            case 4:
                deleteChatRoom();
            default:
                System.out.println("Invalid Input!");
        }
    }

    public void viewMessages(UUID chatRoomId) {
        System.out.println("Below is the chat history:");
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

    public void renameChatRoom() {
        while (true) {
            String name = scanner.nextLine();
            if (!programController.getChatRoomManager().
                    getChatRoomIdToName().values().contains(name)) {
                programController.getChatRoomManager().
                        fetchChatRoom(chatRoomId).setName(name);
                break;
            }
            else {
                System.out.println("The name already exists. Please enter another one:");
            }
        }
    }

    public void deleteChatRoom() {
        programController.getChatRoomManager().deleteChatRoom(chatRoomId);
    }

    public void sendMessage() {

    }
}
