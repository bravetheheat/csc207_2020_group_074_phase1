package main.usecases;


import main.entities.Event;

import java.time.LocalDateTime;

/**
 * The EventBuilder get build event giving necessary info.
 *
 * @author Haoze Huang
 * @version 2.0
 * @since 2020-11-10
 */
public class EventBuilder {
    private String title;
    private LocalDateTime time;
    private String roomID;
    private String requirement;
    private int duration;

    /**
     * Set title of event
     *
     * @param title of event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set time of event
     *
     * @param time of event
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Set room of event
     *
     * @param roomID of event
     */
    public void setRoom(String roomID) {
        this.roomID = roomID;
    }

    /**
     * Set duration of event
     *
     * @param duration of event
     */
    public void setDuration(int duration){this.duration = duration;}


    public void setRequirement(String requirement){this.duration = duration;}
    /**
     * Generate new default event
     *
     * @return Event
     */
    public Event toDefaultEvent() {
        return new Event(title, time, roomID, requirement, duration);
    }

    /**
     * Generate new various types of event
     *
     * @return Event
     */
    public Event toOneSpeakerEvent(){
        return new Event(title, time, roomID, "One", requirement, duration);
    }

    public Event toMultiSpeakerEvent(){
        return new Event(title, time, roomID, "Multi", requirement, duration);
    }

}
