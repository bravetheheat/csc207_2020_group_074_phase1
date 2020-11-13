package main.controllers;

import main.presenters.ChatRoomScreen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ChatRoomScreenController {

    ProgramController programController = new ProgramController();
    ChatRoomScreen chatRoomScreen;
    UUID myUserId;
    UUID chatRoomId;
    Scanner scanner;

    public ChatRoomScreenController(ProgramController programController, UUID chatRoomId) {
        chatRoomScreen = new ChatRoomScreen(programController, chatRoomId);
        myUserId = programController.getAuthController().fetchLoggedInUser();
        this.chatRoomId = chatRoomId;
    }

    public void run() {
        chatRoomScreen.chatRoomScreenStart();
        selectOptions();
    }

    public void selectOptions() {
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                viewMessages(chatRoomId);
            case 2:
                String msg = scanner.nextLine();
                sendMessage(getFriendId(), msg);
            case 3:
                viewParticipants();
            case 4:
                chatRoomScreen.printEnterNewName();
                renameChatRoom();
            case 5:
                deleteChatRoom();
            default:
                chatRoomScreen.printInvalidInput();
        }
    }

    private UUID getFriendId() {
        List<UUID> participantIds = programController.getChatRoomManager().
                fetchChatRoom(chatRoomId).getParticipants();
        return participantIds.get(1);
    }

    public void viewMessages(UUID chatRoomId) {
        chatRoomScreen.printBelowIsChatHistory();
        List<UUID> listOfMessageIds = programController.getChatRoomManager().
                fetchMessagesFromChatRoom(chatRoomId);
        for (UUID messageId : listOfMessageIds) {
            String messageText = programController.messageManager.retrieveMessageText(messageId);
            LocalDateTime messageDate = programController.messageManager.
                    retrieveMessageDate(messageId);
            UUID messageSenderId = programController.messageManager.
                    retrieveMessageSender(messageId);
            String messageSenderName = programController.usersManager.
                    fetchUser(messageSenderId).getUsername();
            chatRoomScreen.printMessage(messageSenderName, messageDate, messageText);
        }
        chatRoomScreen.printLineBreak();
    }

    public void viewParticipants() {
        List<UUID> listOfUserIds = programController.getChatRoomManager().
                fetchUsersFromChatRoom(myUserId);
        chatRoomScreen.printParticipants(listOfUserIds);
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
                chatRoomScreen.printNameAlreadyExists();
            }
        }
    }

    public void deleteChatRoom() {
        programController.getChatRoomManager().deleteChatRoom(chatRoomId);
    }

    public void sendMessage(UUID friendId, String message) {
        programController.getMessageManager().createMessage(message, friendId);
    }
}