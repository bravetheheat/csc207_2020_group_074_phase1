package main.gateways.beans;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * EventBean is used to serialize and deserialize Event objects into string form
 * Each Event can be represented by one or more EventBeans
 *
 * @author David Zhao
 */
public class EventBean implements Serializable {
    private String id;
    private String title;
    private String time;
    private String roomID;
    private String speakersID;
    private String attendeesId;
    private String type;
    private int duration;
    private int capacity;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public EventBean() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getSpeakersID() {
        return speakersID;
    }

    public void setSpeakersID(String speakerID) {
        this.speakersID = speakerID;
    }

    public String getAttendeesId() {
        return attendeesId;
    }

    public void setAttendeesId(String attendeeId) {
        this.attendeesId = attendeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
