package main.usecases;


import main.entities.Event;

import java.time.LocalDateTime;

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
    private String roomID;
    private String speakerID;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setRoom(String roomID) {
        this.roomID = roomID;
    }

    public void setSpeaker(String speakerID) {
        this.speakerID = speakerID;
    }

    public Event toEvent() {
        return new Event(title, time, roomID, speakerID);
    }
}
