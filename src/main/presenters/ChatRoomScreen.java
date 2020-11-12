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
//        System.out.println("Options: " + "1. View Messages" +
//                "2. Start messaging" +
//                "3. See List of Participants; " +
//                "4. Rename this chat room" +
//                "5. Delete this chat room" +
//                "(Enter a number)");
//        int input = scanner.nextInt();
//        switch (input) {
//            case 1:
//                viewMessages(chatRoomId);
//            case 2:
//                String msg = scanner.nextLine();
//                sendMessage(getFriendId(), msg);
//            case 3:
//                viewParticipants();
//            case 4:
//                System.out.println("Enter a new name for this chat room:");
//                renameChatRoom();
//            case 5:
//                deleteChatRoom();
//            default:
//                System.out.println("Invalid Input!");
//        }
    }

    public void chatRoomScreenStart() {
        System.out.println("Options: " + "1. View Messages" +
                "2. Start messaging" +
                "3. See List of Participants; " +
                "4. Rename this chat room" +
                "5. Delete this chat room" +
                "(Enter a number)");
    }

    public void printEnterNewName() {
        System.out.println("Enter a new name for this chat room:");
    }

    public void printInvalidInput() {
        System.out.println("Invalid Input!");
    }

    public void printBelowIsChatHistory () {
        System.out.println("Below is the chat history:");
    }

    public void printLineBreak() {
        System.out.println("==============");
    }

//    private UUID getFriendId() {
//        List<UUID> participantIds = programController.getChatRoomManager().
//                fetchChatRoom(chatRoomId).getParticipants();
//        return participantIds.get(1);
//    }

//    public void viewMessages(UUID chatRoomId) {
//        System.out.println("Below is the chat history:");
//        programController.getChatRoomManager().fetchMessagesFromChatRoom(chatRoomId);
//        System.out.println("==============");
//    }

    public void printNameAlreadyExists() {
        System.out.println("The name already exists. Please enter another one:");
    }

    public void viewParticipants() {
        List<UUID> listOfUserIds = programController.getChatRoomManager().
                fetchUsersFromChatRoom(myUserId);
        for (UUID id : listOfUserIds) {
            System.out.println(programController.getUsersManager().fetchUser(id));
        }
    }

//    public void renameChatRoom() {
//        while (true) {
//            String name = scanner.nextLine();
//            if (!programController.getChatRoomManager().
//                    getChatRoomIdToName().values().contains(name)) {
//                programController.getChatRoomManager().
//                        fetchChatRoom(chatRoomId).setName(name);
//                break;
//            }
//            else {
//                System.out.println("The name already exists. Please enter another one:");
//            }
//        }
//    }

//    public void deleteChatRoom() {
//        programController.getChatRoomManager().deleteChatRoom(chatRoomId);
//    }

//    public void sendMessage(UUID friendId, String message) {
//        programController.getMessageManager().createMessage(message, friendId);
//    }
}
