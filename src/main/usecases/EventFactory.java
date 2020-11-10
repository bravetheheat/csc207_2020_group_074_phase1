package main.usecases;

import main.entities.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The EventFactory get different additional constraints to the scheduling for various types of events.
 *
 * @author Haoze Huang
 * @version 1.0
 * @since 2020-11-10
 */
public class EventFactory {
    private final String title;
    private final LocalDateTime time;
    private final UUID roomID;
    private final UUID speakerID;

    public EventFactory(String title, LocalDateTime time, UUID roomID, UUID speakerID){
        this.title = title;
        this.time = time;
        this.roomID = roomID;
        this.speakerID = speakerID;
    }

    public Event getEvent(String ...eventRequirements){
        EventBuilder eb = new EventBuilder();
        eb.setTitle(title);
        eb.setRoom(roomID);
        eb.setTime(time);
        eb.setSpeaker(speakerID);
        //default one speaker event
        return eb.toEvent();
        //more event requirements to be added in phase 2
    }

}
