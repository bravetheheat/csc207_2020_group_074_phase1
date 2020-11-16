package main.gateways.beans;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * EventBean is used to serialize and deserialize Event objects into string form
 * Each Event can be represented by one or more EventBeans
 */
public class EventBean implements Serializable {
    private String id;
    private String title;
    private LocalDateTime time;
    private String roomID;
    private String speakerID;
    private String attendeeId;

    public EventBean() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(String speakerID) {
        this.speakerID = speakerID;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
