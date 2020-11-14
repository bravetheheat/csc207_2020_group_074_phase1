//package main.screencontrollers;
//
//
//import main.controllers.ChatRoomScreenController;
//import main.controllers.ProgramController;
//import main.presenters.MessageScreen;
//import main.screencontrollers.ScreenController;
//
//import java.util.*;
//
///**
// * @author Steven Yuan
// * @version 1.1
// * @since 2020-11-12
// */
//public class MessageScreenController extends ScreenController {
//
//    MessageScreen messageScreen;
//    UUID myUserId;
//
//    public MessageScreenController(ProgramController programController) {
//        super(programController);
//        myUserId = programController.getAuthController().fetchLoggedInUser();
//        messageScreen = new MessageScreen();
//    }
//
//    @Override
//    public void start() {
//        viewChatRooms();
//        messageScreen.messageScreenStart();
//        selectOrCreate();
//    }
//
//    public List<UUID> fetchChatRoomIds() {
//        return programController.getChatRoomManager().
//                fetchChatRoomsOfUser(myUserId);
//    }
//
//    public void selectOrCreate() {
//        int input = scanner.nextInt();
//        switch (input) {
//            case 0:
//                returnToMainMenu();
//            case 1: {
//                messageScreen.printEnterChatRoomName();
//                String chatRoomNameInput = scanner.nextLine();
//                goToChatRoomScreen(chatRoomNameInput);
//            }
//
//            case 2:
//                startChatRoomWithFriend();
//        }
//    }
//
//    public void returnToMainMenu() {
//        // TODO: implement this
//    }
//
//    public void startChatRoomWithFriend() {
//        messageScreen.printEnterUsernameOfPerson();
//        String friendUsername = scanner.nextLine();
//        UUID friendUserId = programController.getUsersManager().
//                getIDFromUsername(friendUsername);
//        messageScreen.printCreateNameForChatRoom();
//        while (true) {
//            String chatRoomNameInput = scanner.nextLine();
//            if (!programController.getChatRoomManager().
//                    getChatRoomIdToName().values().contains(chatRoomNameInput)) {
//                programController.getChatRoomManager().createChatRoom(
//                        Arrays.asList(myUserId, friendUserId), chatRoomNameInput);
//                break;
//            } else {
//                messageScreen.printNameAlreadyExists();
//            }
//        }
//    }
//
//    public void viewChatRooms() {
//        List<UUID> chatRoomIds = fetchChatRoomIds();
//        messageScreen.printChatRooms(programController, chatRoomIds);
//    }
//
//    public void goToChatRoomScreen(String chatRoomName) {
//        HashMap<String, UUID> chatRoomNameToId = new HashMap<>();
//        for (UUID id : programController.getChatRoomManager().
//                getChatRoomIdToName().keySet()) {
//            chatRoomNameToId.put(programController.getChatRoomManager().
//                    getChatRoomIdToName().get(id), id);
//        }
//        UUID chatRoomIdSelected = chatRoomNameToId.get(chatRoomName);
//        ChatRoomScreenController chatRoomScreenController =
//                new ChatRoomScreenController(programController, chatRoomIdSelected);
//        chatRoomScreenController.start();
//    }
//}
