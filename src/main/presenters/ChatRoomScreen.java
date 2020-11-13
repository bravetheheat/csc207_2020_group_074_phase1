package main.presenters;

import main.controllers.ProgramController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Steven Yuan
 * @version 2.0
 * @since 2020-11-12
 */
public class ChatRoomScreen {

    public ChatRoomScreen() {

    }


    public void chatRoomScreenStart() {
        System.out.println("Options: " + "0. Return to the main menu" +
                "1. View Messages" +
                "2. Start messaging" +
                "3. See List of Participants; " +
                "4. Rename this chat room" +
                "5. Delete this chat room" +
                "(Enter a number)");
    }

    public void printEnterNewName() {
        System.out.println("Enter a new name for this chat room: (enter 'q' to quit)");
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

//    public void viewParticipants() {
//        List<UUID> listOfUserIds = programController.getChatRoomManager().
//                fetchUsersFromChatRoom(myUserId);
//        for (UUID id : listOfUserIds) {
//            System.out.println(programController.getUsersManager().fetchUser(id));
//        }
//    }

    public void printParticipants(ProgramController programController,
                                  List<UUID> listOfUserIds) {
        for (UUID id : listOfUserIds) {
            System.out.println(programController.getUsersManager().fetchUser(id));
        }
    }

    public void printMessage(String senderName, LocalDateTime messageDate, String text) {
        System.out.println(senderName + "[" + messageDate.toString() + "]: " + text);
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
