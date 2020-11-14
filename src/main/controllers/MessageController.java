package main.controllers;
import java.util.*;
import main.usecases.MessageManager;
import main.usecases.ContactsManager;
import main.controllers.EventController;
import main.entities.Event;
import main.usecases.UsersManager;
import main.entities.Message;
import main.usecases.InboxManager;

/**
 * The MessageController handles different types of users' sending messages features, and could also gives that who
 * could the user message.
 *
 * @author Ruoming Ren
 * @version 2.0
 * @since 2020-11-12
 */
public class MessageController {

    MessageManager messageManager;
    EventController eventController;
    UsersManager usersManager;
    InboxManager inboxManager;



    public MessageController(ProgramController programController){
        this.messageManager = programController.getMessageManager();
        this.eventController = programController.getEventController();
        this.usersManager = programController.getUsersManager();
        this.inboxManager = programController.getInboxManager();
    }

//    /**
//     * a helper method check if there exist a chatroom that contains only these two users.
//     * @param sender the id of the first user
//     * @param receiver the id of the second user
//     * @return the UUID of the chatroom if there exist a chatroom that contains only these two users,
//     * otherwise, return null.
//     */
//    public UUID chatRoomContainingOnlyTheseTwo(UUID sender, UUID receiver){
//        for(UUID chatroomId : chatRoomManager.fetchChatRoomsOfUser(sender)){
//            for(UUID participantId : chatRoomManager.fetchChatRoom(chatroomId).getParticipants()){
//                if(chatRoomManager.fetchChatRoom(chatroomId).getParticipants().size() == 2
//                        && participantId == receiver){
//
//                    return chatroomId;
//                }
//            }
//        }
//        return null;
//    }

    /**
     * send a message from a sender to a receiver.
     * @param sender the id of the sender
     * @param receiver the id of the receiver
     * @param context the context of the message
     */
    public void sendMessage(UUID sender, UUID receiver, String context){
        UUID newMessage = messageManager.createMessage(context, sender);
        inboxManager.putMessageInToInbox(newMessage, receiver);
    }

    /**
     * Send the same message to all users inside the list
     * @param sender the sender of the message
     * @param receivers a list of receivers
     * @param context the context of the message
     */
    public void broadCast(UUID sender, List<UUID> receivers, String context){
        for(UUID receiver: receivers){
            this.sendMessage(sender, receiver, context);
        }
    }

    /**
     * get a list of users that could be messaged by an attendee, which is all other users except the sender
     * @return the list of user's id which could be messaged by an attendee.
     */
    public List<UUID> receiversForAttendeeAndOrganizer(UUID sender){
        List<UUID> receivers = new ArrayList<>();
        // Search all users
        for(UUID receiver: usersManager.getAllUsers()){
            // check if he is an organizer
            if(receiver != sender){
                receivers.add(receiver);
            }
        }
        return receivers;

    }

    /**
     * Broadcast to all other users. This method should only be used by organizer
     * @param sender the sender
     * @param context the context of the message
     */
    public void broadCastToAll(UUID sender, String context){
        this.broadCast(sender, this.receiversForAttendeeAndOrganizer(sender), context);
    }

    /**
     * Broadcast to all attendees. This method should only be used by organizer.
     *
     * @param sender the sender
     * @param context the context of the message
     */
    public void broadCastToAttendees(UUID sender, String context){
        List<UUID> attendees = new ArrayList<>();
        List<UUID> receivers = this.receiversForAttendeeAndOrganizer(sender);
        for (UUID user:receivers) {
            if (this.usersManager.fetchRole(user).equals("Attendee")) {
                attendees.add(user);
            }
        }
        this.broadCast(sender, attendees, context);
    }


    /**
     * Broadcast to all speakers. This method should only be used by organizer.
     *
     * @param sender the sender
     * @param context the context of the message
     */
    public void broadCastToSpeakers(UUID sender, String context){
        List<UUID> speakers = new ArrayList<>();
        List<UUID> receivers = this.receiversForAttendeeAndOrganizer(sender);
        for (UUID user:receivers) {
            if (this.usersManager.fetchRole(user).equals("Speaker")) {
                speakers.add(user);
            }
        }
        this.broadCast(sender, speakers, context);
    }
    /**
     * speaker could broadcast to all attendees of an event
     * @param event the uuid of the event
     * @param speaker the speaker himself
     * @param context the context of the string
     */
    public void broadCastForSpeaker(UUID event, UUID speaker, String context){
//        List<UUID> receivers = EventController.getSingleEventInfo().getAttendeesID();
//        this.broadCast(speaker, receivers, context);
    }

    /**
     * speaker could broadcast the message to all attendees of several events
     * @param events a list of events
     * @param speaker the speaker himself
     * @param context the context of string
     */
    public void broadCastForSpeakerMoreEvents(List<UUID> events, UUID speaker, String context){
        for(UUID event: events){
            this.broadCastForSpeaker(event, speaker, context);
        }
    }



//    /**
//     * get a list of user who have send messages to the speaker before
//     * @param sender the id of the speaker
//     * @return a list of user who have send messages to the speaker before
//     */
//    public List<UUID> couldBeMessagedBySpeakerThroughReply(UUID sender){
//        List<UUID> receivers = new ArrayList<>();
//        // search all chatrooms this speaker in.
//        for(UUID chatRoom: chatRoomManager.fetchChatRoomsOfUser(sender)){
//            // make sure there are only two participates
//            if(chatRoomManager.fetchChatRoom(chatRoom).getParticipants().size() == 2){
//                // search all messages to check if the attendee have send
//                for(UUID message: chatRoomManager.fetchChatRoom(chatRoom).getMessages()){
//
//                    if(messageManager.retrieveMessageSender(message) != sender){
//                        // get the uuid of the attendee
//                        for(UUID user: chatRoomManager.fetchChatRoom(chatRoom).getParticipants()){
//                            if(user != sender){
//                                receivers.add(user);
//                            }
//                        }
//                    }
//
//                }
//
//
//            }
//        }
//        return receivers;
//    }




}
