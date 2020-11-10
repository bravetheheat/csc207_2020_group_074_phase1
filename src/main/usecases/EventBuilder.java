package main.usecases;


import main.entities.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The EventBuilder get build event giving necessary info.
 *
 * @author Haoze Huang
 * @version 1.0
 * @since 2020-11-10
 */
public class EventBuilder {
    private String title;
    private LocalDateTime time;
    private UUID roomID;
    private UUID speakerID;

    public EventBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public EventBuilder setTime(LocalDateTime time){
        this.time = time;
        return this;
    }

    public EventBuilder setRoom(UUID roomID){
        this.roomID = roomID;
        return this;
    }

    public EventBuilder setSpeaker(UUID speakerID){
        this.speakerID = speakerID;
        return this;
    }

    public Event toEvent(){
        return new Event(title, time, roomID, speakerID);
    }
}
