package main.controllers;

import main.presenters.ChatRoomScreen;
import main.screencontrollers.MessageScreenController;
import main.screencontrollers.ScreenController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Steven Yuan
 * @version 2.0
 * @since 2020-11-12
 */
public class ChatRoomScreenController extends ScreenController {

    ChatRoomScreen chatRoomScreen;
    MessageScreenController messageScreenController;
    UUID myUserId;
    UUID chatRoomId;

    public ChatRoomScreenController(ProgramController programController, UUID chatRoomId) {
        super(programController);
        chatRoomScreen = new ChatRoomScreen();
        messageScreenController = new MessageScreenController(programController);
        myUserId = programController.getAuthController().fetchLoggedInUser();
        this.chatRoomId = chatRoomId;
    }

    @Override
    public void start() {
        chatRoomScreen.chatRoomScreenStart();
        selectOptions();
        returnToMessageScreen();
    }

    public void selectOptions() {
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) break;
            switch (input) {
                case 1:
                    chatRoomScreen.printBelowIsChatHistory();
                    viewOldMessages(chatRoomId);
                case 2:
                    String msg = scanner.nextLine();
                    sendMessage(getFriendId(), msg);
                    viewOldMessages(chatRoomId);
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
    }

    public void returnToMessageScreen() {
        messageScreenController.start();
    }

    protected UUID getFriendId() {
        List<UUID> participantIds = programController.getChatRoomManager().
                fetchChatRoom(chatRoomId).getParticipants();
        return participantIds.get(1);
    }

    public void viewOldMessages(UUID chatRoomId) {
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
        chatRoomScreen.printParticipants(programController, listOfUserIds);
    }

    public void renameChatRoom() {
        while (true) {
            String name = scanner.nextLine();
            if (name.equals("q") || name.equals("quit")) {
                break;
            }
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
