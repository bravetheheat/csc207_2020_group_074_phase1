package main.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class that represents an event at the conference.
 *
 * @author Yile Xie
 */

public class Event {
    private final UUID id = UUID.randomUUID();
    private String title;
    private LocalDateTime time;
    private UUID roomID;
    private UUID speakerID;
    private List<UUID> attendeesID;

    /**
     * A title, time, room number, and the ID of the speaker are required to
     * create an instance of Event.
     *
     * @param title     of the event
     * @param time      of the event (starting time)
     * @param roomID    in which this event takes place
     * @param speakerID of the speaker that speaks at this event
     */
    public Event(String title, LocalDateTime time, UUID roomID, UUID speakerID) {
        this.title = title;
        this.time = time;
        this.roomID = roomID;
        this.speakerID = speakerID;
        this.attendeesID = new ArrayList<>();
    }

    /**
     * Get the unique UUID of this event.
     *
     * @return id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns a list of IDs of attendees who signed up for this event.
     *
     * @return attendeesID
     */
    public List<UUID> getAttendeesID() {
        return attendeesID;
    }

    /**
     * A given id of an attendee is added to the list of id
     *
     * @param id to be added
     */
    public void addAttendees(UUID id) {
        attendeesID.add(id);
    }

    /**
     * A given id of an attendee is removed from the list of id
     *
     * @param id to be removed
     */
    public void removeAttendees(UUID id) {
        attendeesID.remove(id);
    }

    /**
     * Returns the title of this event.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for the event.
     *
     * @param title to be changed to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the time of this event.
     *
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Reschedule the event.
     *
     * @param time to be changed to
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Returns the room ID in which this event occurs.
     *
     * @return roomNum
     */
    public UUID getRoomID() {
        return roomID;
    }

    /**
     * Reassign a room for the event to take place.
     *
     * @param roomID to be changed to
     */
    public void setRoomID(UUID roomID) {
        this.roomID = roomID;
    }

    /**
     * Get the ID of the speaker for this event.
     *
     * @return speakerID
     */
    public UUID getSpeakerID() {
        return speakerID;
    }

    /**
     * Change the ID of speaker for this event.
     *
     * @param speakerID to be changed to
     */
    public void setSpeakerID(UUID speakerID) {
        this.speakerID = speakerID;
    }
}
