package main.controllers;
import java.util.*;
import main.usecases.MessageManager;
import main.usecases.ContactsManager;
import main.usecases.ChatRoomManager;
import main.controllers.EventController;
import main.entities.Event;
import main.usecases.UsersManager;
import main.entities.Message;

/**
 * The MessageController handles different types of users' sending messages features, and could also gives that who
 * could the user message.
 *
 * @author Ruoming Ren
 * @version 1.0
 * @since 2020-11-12
 */
public class MessageController {

    MessageManager messageManager;
    ChatRoomManager chatRoomManager;
    ContactsManager contactsManager;
    EventController eventController;
    UsersManager usersManager;



    public MessageController(ProgramController programController){
        this.chatRoomManager = programController.chatRoomManager;
        this.messageManager = programController.messageManager;
        this.contactsManager = programController.contactsManager;
        this.eventController = programController.eventController;
        this.usersManager = programController.usersManager;
    }

    /**
     * a helper method check if there exist a chatroom that contains only these two users.
     * @param sender the id of the first user
     * @param receiver the id of the second user
     * @return the UUID of the chatroom if there exist a chatroom that contains only these two users,
     * otherwise, return null.
     */
    public UUID ifExistChatRoomContainingOnlyTheseTwoUsers(UUID sender, UUID receiver){
        for(UUID chatroomId : chatRoomManager.fetchChatRoomsOfUser(sender)){
            for(UUID participantId : chatRoomManager.fetchChatRoom(chatroomId).getParticipants()){
                if(chatRoomManager.fetchChatRoom(chatroomId).getParticipants().size() == 2
                        && participantId == receiver){

                    return chatroomId;
                }
            }
        }
        return null;
    }

    /**
     * send a message from a sender to a receiver.
     * @param sender the id of the sender
     * @param receiver the id of the receiver
     * @param message the context of the message
     */
    public void sendMessage(UUID sender, UUID receiver, String message){
        if(this.ifExistChatRoomContainingOnlyTheseTwoUsers(sender, receiver) != null){
            chatRoomManager.addMessageToChatRoom(this.ifExistChatRoomContainingOnlyTheseTwoUsers(sender, receiver),
                    messageManager.createMessage(message, sender));
        }else{
            List<UUID> participants = new ArrayList<>();
            participants.add(sender);
            participants.add(receiver);
            chatRoomManager.addMessageToChatRoom(chatRoomManager.createChatRoom(participants),
                    messageManager.createMessage(message, sender));

        }
    }

    /**
     * get a list of users that could be messaged by an attendee
     * @param sender the id of the attendee
     * @return the list of user's id which could be messaged by an attendee.
     */
    public List<UUID> usersCouldBeMessagedByAttendee(UUID sender){
        List<UUID> receivers = new ArrayList<>();
        // Add all people inside the contact list.
        for(UUID receiver: contactsManager.getContactList(sender)){
            receivers.add(receiver);
        }
        // Add all speakers of events the attendee signing up
        for(Event event: eventController.getUserEvents(sender)) {
            receivers.add(event.getSpeakerID());
        }
        return receivers;
    }

    /**
     * get a list of users that could be messaged by an organizer
     * @param sender the id of the organizer
     * @return the list of user's id which could be messaged by the organizer.
     */
    public List<UUID> usersCouldBeMessagedByOrganizer(UUID sender){
        List<UUID> receivers = new ArrayList<>();
        for(UUID receiver: usersManager.getRegisteredUsers()){
            receivers.add(receiver);
        }
        return receivers;
    }

    /**
     * get a map, the keys are the uuid of events which the speaker has, and the values are lists of attendees of
     * those events
     * @param sender the id of the speaker
     * @return a map shows all attendees inside events of a speaker
     */
    public Map<UUID, List<UUID>> usersCouldBeMessagedBySpeakerThroughEvents(UUID sender){
        Map<UUID, List<UUID>> receivers= new HashMap<>();
        for(UUID events: SpeakerController)
        List<UUID> receivers = new ArrayList<>();

    }

    /**
     * get a list of user who have send messages to the speaker before
     * @param sender the id of the speaker
     * @return a list of user who have send messages to the speaker before
     */
    public List<UUID> usersCouldBeMessagedBySpeakerThroughReply(UUID sender){
        List<UUID> receivers = new ArrayList<>();
        // search all chatrooms this speaker in.
        for(UUID chatRoom: chatRoomManager.fetchChatRoomsOfUser(sender)){
            // make sure there are only two participates
            if(chatRoomManager.fetchChatRoom(chatRoom).getParticipants().size() == 2){
                // search all messages to check if the attendee have send
                for(UUID message: chatRoomManager.fetchChatRoom(chatRoom).getMessages()){

                    if(messageManager.retrieveMessageSender(message) != sender){
                        // get the uuid of the attendee
                        for(UUID user: chatRoomManager.fetchChatRoom(chatRoom).getParticipants()){
                            if(user != sender){
                                receivers.add(user);
                            }
                        }
                    }

                }


            }
        }
        return receivers;
    }




}
