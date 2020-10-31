package main.entities;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.NoSuchElementException;

/**
 * A class that represents a conference room.
 *
 * @author Yile Xie
 */

public class Room {
    private final UUID id = UUID.randomUUID(); // for referencing purposes
    private int roomNum; // the organizer can enter a room with its room number, and the schedule will display
    // the room number instead of the UUID
    private HashMap<LocalDateTime, UUID> schedule;
    private int capacity;

    /**
     * A room number is required to create an instance of Room.
     */
    public Room(int roomNum) {
        this.roomNum = roomNum;
        this.schedule = new HashMap<>();
        this.capacity = 2;
    }

    /**
     * Get the unique UUID of this room.
     *
     * @return id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the room number.
     *
     * @return roomNum
     */
    public int getRoomNum() {
        return roomNum;
    }

    /**
     * Get the capacity of this room.
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set a new capacity for this room.
     *
     * @param capacity to be changed to
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get the booking schedule of this room.
     *
     * @return schedule
     */
    public HashMap<LocalDateTime, UUID> getSchedule() {
        return schedule;
    }

    /**
     * Add a pair of time and event to the schedule.
     *
     * @param time  that this room will be booked
     * @param event corresponding to the time slot
     */
    public void addToSchedule(LocalDateTime time, UUID event) {
        schedule.put(time, event);
    }

    /**
     * Remove a pair of time and event from the schedule.
     *
     * @param time  that this room was booked
     * @param event corresponding to the time slot
     */
    public void removeFromSchedule(LocalDateTime time, UUID event) {
        schedule.remove(time);
    }
}
