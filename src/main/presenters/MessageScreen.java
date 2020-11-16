package main.presenters;

/**
 * Provide a list of chat rooms and let user create chat room with
 * someone in the contact list
 *
 * @author Steven Yuan
 * @version 2.0
 * @since 2020-11-03
 */
public class MessageScreen {

    public MessageScreen() {
    }


    public void messageScreenStart() {
        System.out.println();
        System.out.println("0. Return to main menu.");
        System.out.println("1. Select a chat room.");
        System.out.println("2. Create a new chat room.");
    }

    public void printEnterChatRoomName() {
        System.out.println("Enter the name of the chat room:");
    }

    public void printEnterUsernameOfPerson() {
        System.out.println("Enter the username of the person you want to chat with:");
    }

    public void printCreateNameForChatRoom() {
        System.out.println("Create a name for the chat room:");
    }

    public void printNameAlreadyExists() {
        System.out.println("The name already exists. Please enter another one:");
    }

//    public void printChatRooms(ProgramController programController, List<String> chatRoomIds) {
//        System.out.println();
//        for (String id : chatRoomIds) {
//            System.out.println(programController.getChatRoomManager().
//                    getChatRoomIdToName().get(id));
//        }
//    }

//    public void startChatRoomWithFriend() {
//        System.out.println("Enter the username of the person you want to chat with:");
//        String friendUsername = scanner.nextLine();
//        String friendUserId = programController.getUsersManager().
//                getIDFromUsername(friendUsername);
//        System.out.println("Create a name for the chat room:");
//        while (true) {
//            String chatRoomNameInput = scanner.nextLine();
//            if (!programController.getChatRoomManager().
//                    getChatRoomIdToName().values().contains(chatRoomNameInput)) {
//                programController.getChatRoomManager().createChatRoom(
//                        Arrays.asList(myUserId, friendUserId), chatRoomNameInput);
//                break;
//            }
//            else {
//                System.out.println("The name already exists. Please enter another one:");
//            }
//        }
//    }

//    public void viewChatRooms() {
//        System.out.println();
//        List<String> chatRoomIds = messageScreenController.fetchChatRoomIds();
//        for (String id : chatRoomIds) {
//            System.out.println(programController.getChatRoomManager().
//                    getChatRoomIdToName().get(id));
//        }
//    }


//    public void goToChatRoomScreen(String chatRoomName) {
//        HashMap<String, String> chatRoomNameToId = new HashMap<>();
//        for (String id : programController.getChatRoomManager().
//                getChatRoomIdToName().keySet()) {
//            chatRoomNameToId.put(programController.getChatRoomManager().
//                    getChatRoomIdToName().get(id), id);
//        }
//        String chatRoomIdSelected = chatRoomNameToId.get(chatRoomName);
//        ChatRoomScreen chatRoomScreen = new ChatRoomScreen(
//                programController, chatRoomIdSelected);
//        chatRoomScreen.start();
//    }


}
