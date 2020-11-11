package main.presenters;

import main.controllers.ProgramController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;

/**
 * Provide a list of chat rooms and let user create chat room with
 * someone in the contact list
 *
 * @author Steven Yuan
 * @version 2.0
 * @since 2020-11-03
 */
public class MessageScreen extends Screen {

    private UUID myUserId;

    public MessageScreen(ProgramController programController) {
        super(programController);
        RegisterScreen registerScreen = new RegisterScreen(programController);
        myUserId = programController.getUsersManager().getIDFromUsername(registerScreen.username);
        // Where are we storing the information of the current user?(in the Register Screen?)
    }

    @Override
    public void start() {
        viewChatRooms();
        System.out.println();
        System.out.println("1. Select a chat room.");
        System.out.println("2. Create a new chat room.");
        int input = scanner.nextInt();
        if (input == 1) {
            System.out.println("Enter the name of the chat room:");
            String chatRoomNameInput = scanner.nextLine();
            goToChatRoomScreen(chatRoomNameInput);
        }
        else {
            startChatRoomWithFriend();
        }
    }

    public void startChatRoomWithFriend() {
        // UUID newChatRoomId;
        System.out.println("Enter the username of the person you want to chat with:");
        String friendUsername = scanner.nextLine();
        UUID friendUserId = programController.getUsersManager().
                getIDFromUsername(friendUsername);
        System.out.println("Create a name for the chat room:");
        while (true) {
            String chatRoomNameInput = scanner.nextLine();
            if (!programController.getChatRoomManager().
                    getChatRoomIdToName().values().contains(chatRoomNameInput)) {
                programController.getChatRoomManager().createChatRoom(
                        Arrays.asList(myUserId, friendUserId), chatRoomNameInput);
                break;
            }
            else {
                System.out.println("The name already exists. Please enter another one:");
            }
        }
        // return newChatRoomId;
    }

    public void viewChatRooms() {
        System.out.println();
        List<UUID> chatRoomIds = programController.getChatRoomManager().
                fetchChatRoomsOfUser(myUserId);
        for (UUID id : chatRoomIds) {
            System.out.println(programController.getChatRoomManager().
                    getChatRoomIdToName().get(id));
        }
        // Should we have a name for each ChatRoom?(can be the same as the user name of
        // the friend that you chat with)
    }


    public void goToChatRoomScreen(String chatRoomName) {
        HashMap<String, UUID> chatRoomNameToId = new HashMap<>();
        for (UUID id : programController.getChatRoomManager().
                getChatRoomIdToName().keySet()) {
            chatRoomNameToId.put(programController.getChatRoomManager().
                    getChatRoomIdToName().get(id), id);
        }
        UUID chatRoomIdSelected = chatRoomNameToId.get(chatRoomName);
        ChatRoomScreen chatRoomScreen = new ChatRoomScreen(
                programController, chatRoomIdSelected);
        chatRoomScreen.start();
    }


}
