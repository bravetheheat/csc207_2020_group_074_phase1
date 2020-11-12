package main.controllers;


import main.presenters.ChatRoomScreen;
import main.presenters.MessageScreen;

import java.util.*;

/**
 * @author Steven Yuan
 * @version 1.0
 * @since 2020-11-12
 */
public class MessageScreenController {

    ProgramController programController = new ProgramController();
    MessageScreen messageScreen;
    UUID myUserId;
    Scanner scanner;

    public MessageScreenController() {
        myUserId = programController.getAuthController().fetchLoggedInUser();
        messageScreen = new MessageScreen(programController);
        scanner = new Scanner(System.in);
    }

    public void run() {
        messageScreen.viewChatRooms();
        messageScreen.messageScreenStart();
        selectOrCreate();
    }

    public List<UUID> fetchChatRoomIds() {
        return programController.getChatRoomManager().
                fetchChatRoomsOfUser(myUserId);
    }

    public void selectOrCreate() {
        int input = scanner.nextInt();
        if (input == 1) {
            messageScreen.printEnterChatRoomName();
            String chatRoomNameInput = scanner.nextLine();
            goToChatRoomScreen(chatRoomNameInput);
        }
        else {
            startChatRoomWithFriend();
        }
    }

    public void startChatRoomWithFriend() {
        messageScreen.printEnterUsernameOfPerson();
        String friendUsername = scanner.nextLine();
        UUID friendUserId = programController.getUsersManager().
                getIDFromUsername(friendUsername);
        messageScreen.printCreateNameForChatRoom();
        while (true) {
            String chatRoomNameInput = scanner.nextLine();
            if (!programController.getChatRoomManager().
                    getChatRoomIdToName().values().contains(chatRoomNameInput)) {
                programController.getChatRoomManager().createChatRoom(
                        Arrays.asList(myUserId, friendUserId), chatRoomNameInput);
                break;
            }
            else {
                messageScreen.printNameAlreadyExists();
            }
        }
    }

    public void goToChatRoomScreen(String chatRoomName) {
        HashMap<String, UUID> chatRoomNameToId = new HashMap<>();
        for (UUID id : programController.getChatRoomManager().
                getChatRoomIdToName().keySet()) {
            chatRoomNameToId.put(programController.getChatRoomManager().
                    getChatRoomIdToName().get(id), id);
        }
        UUID chatRoomIdSelected = chatRoomNameToId.get(chatRoomName);
//        ChatRoomScreen chatRoomScreen = new ChatRoomScreen(
//                programController, chatRoomIdSelected);
        ChatRoomScreenController chatRoomScreenController =
                new ChatRoomScreenController(programController, chatRoomIdSelected);
        chatRoomScreenController.run();
    }

}
