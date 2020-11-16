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

    /**
     * Set title of event
     * @param title of event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set time of event
     * @param time of event
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Set room of event
     * @param roomID of event
     */
    public void setRoom(String roomID) {
        this.roomID = roomID;
    }

    /**
     * Set speaker of event
     * @param speakerID of event
     */
    public void setSpeaker(String speakerID) {
        this.speakerID = speakerID;
    }

    /**
     * Generate new event
     * @return Event
     */
    public Event toEvent() {
        return new Event(title, time, roomID, speakerID);
    }
}
