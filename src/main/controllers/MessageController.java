package main.controllers;

import main.entities.Event;
import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * the constructor of the MessageController
     * @param programController the main controller of the program which carries the information which
     * this class needs
     */
    public MessageController(ProgramController programController) {
        this.messageManager = programController.getMessageManager();
        this.eventController = programController.getEventController();
        this.usersManager = programController.getUsersManager();
        this.inboxManager = programController.getInboxManager();
    }

    /**
     * send a message from a sender to a receiver.
     *
     * @param sender   the id of the sender
     * @param receiver the id of the receiver
     * @param context  the context of the message
     */
    public void sendMessage(String sender, String receiver, String context) {
        String newMessage = messageManager.createMessage(context, sender);
        inboxManager.putMessageInToInbox(newMessage, receiver);
    }

    /**
     * Send the same message to all users inside the list
     *
     * @param sender    the sender of the message
     * @param receivers a list of receivers
     * @param context   the context of the message
     */
    public void broadCast(String sender, List<String> receivers, String context) {
        for (String receiver : receivers) {
            this.sendMessage(sender, receiver, context);
        }
    }

    /**
     * get a list of users that could be messaged by an attendee, which is all other users except the sender
     *
     * @return the list of user's id which could be messaged by an attendee.
     */
    public List<String> receiversForAttendeeAndOrganizer(String sender) {
        List<String> receivers = new ArrayList<>();
        // Search all users
        for (String receiver : usersManager.getAllUsers()) {
            // check if he is an organizer
            if (receiver.equals(sender)) {
                receivers.add(receiver);
            }
        }
        return receivers;

    }

    /**
     * get all events which the speaker in charge of
     * @param speaker the speaker's id
     * @return all events object which the speaker in charge of
     */
    public ArrayList<Event> eventsOfSpeaker(String speaker) {
        ArrayList<Event> events = new ArrayList<>();
        for (String event : eventController.getSpeakerEvents(speaker)) {
            events.add(eventController.getSingleEvent(event));
        }
        return events;

    }

    /**
     * get all events in string format which the speaker in charge of
     * @param speaker the speaker's id
     * @return a list of events which the speaker in charge of
     */
    public ArrayList<String> eventsOfSpeakerInString(String speaker) {
        ArrayList<String> events = new ArrayList<>();
        for (String event : eventController.getSpeakerEvents(speaker)) {
            events.add(eventController.getSingleEvent(event).getTitle());
        }
        return events;
    }

    /**
     * get all attendees which the speaker could message to
     * @param speaker the speakers id
     * @return a list of attendees' ids the speaker could message to
     */
    public ArrayList<String> replyOptionsForSpeaker(String speaker) {
        ArrayList<String> ret = new ArrayList<>();
        for (String message : inboxManager.getMessagesOfUser(speaker)) {
            if (!ret.contains(messageManager.retrieveMessageSender(message))) {
                ret.add(messageManager.retrieveMessageSender(message));
            }
        }
        return ret;
    }

    /**
     * get all attendees' usernames which the speaker could message to
     * @param speaker the id of the speaker
     * @return a list of attendees' usernames
     */
    public ArrayList<String> replyOptionsForSpeakerInString(String speaker) {
        ArrayList<String> ret = new ArrayList<>();
        for (String sender : replyOptionsForSpeaker(speaker)) {
            ret.add(usersManager.fetchUser(sender).getUsername());
        }
        return ret;
    }


    /**
     * Broadcast to all other users. This method should only be used by organizer
     *
     * @param sender  the sender
     * @param context the context of the message
     */
    public void broadCastToAll(String sender, String context) {
        this.broadCast(sender, this.receiversForAttendeeAndOrganizer(sender), context);
    }

    /**
     * Broadcast to all attendees. This method should only be used by organizer.
     *
     * @param sender  the sender
     * @param context the context of the message
     */
    public void broadCastToAttendees(String sender, String context) {
        List<String> attendees = new ArrayList<>();
        List<String> receivers = this.receiversForAttendeeAndOrganizer(sender);
        for (String user : receivers) {
            if (this.usersManager.fetchRole(user).equals("Attendee")) {
                attendees.add(user);
            }
        }
        this.broadCast(sender, attendees, context);
    }


    /**
     * Broadcast to all speakers. This method should only be used by organizer.
     *
     * @param sender  the sender
     * @param context the context of the message
     */
    public void broadCastToSpeakers(String sender, String context) {
        List<String> speakers = new ArrayList<>();
        List<String> receivers = this.receiversForAttendeeAndOrganizer(sender);
        for (String user : receivers) {
            if (this.usersManager.fetchRole(user).equals("Speaker")) {
                speakers.add(user);
            }
        }
        this.broadCast(sender, speakers, context);
    }

    /**
     * speaker could broadcast to all attendees of an event
     *
     * @param event   the uuid of the event
     * @param speaker the speaker himself
     * @param context the context of the string
     */
    public void broadCastForSpeaker(String event, String speaker, String context) {
        List<String> receivers = eventController.getSingleEvent(event).getAttendeesID();
        this.broadCast(speaker, receivers, context);
    }

    /**
     * speaker could broadcast the message to all attendees of several events
     *
     * @param events  a list of events
     * @param speaker the speaker himself
     * @param context the context of string
     */
    public void broadCastForSpeakerMoreEvents(List<String> events, String speaker, String context) {
        for (String event : events) {
            this.broadCastForSpeaker(event, speaker, context);
        }
    }


}
