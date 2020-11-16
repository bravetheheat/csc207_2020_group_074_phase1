package main.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class that represents a conference room.
 *
 * @author Yile Xie
 */

public class Room {
    private String id; // for referencing purposes
    private int roomNum; // the organizer can enter a room with its room number, and the schedule will display
    // the room number instead of the String
    private Map<LocalDateTime, String> schedule;
    private int capacity;

    /**
     * No-arg constructor for deserialization
     */
    public Room() {
    this.schedule = new HashMap<>();
    }


    /**
     * A room number is required to create an instance of Room.
     */
    public Room(int roomNum) {
        this.id = UUID.randomUUID().toString();
        this.roomNum = roomNum;
        this.schedule = new HashMap<>();
        this.capacity = 2;
    }

    /**
     * Get the unique String of this room.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the room number.
     *
     * @return roomNum
     */
    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
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
    public Map<LocalDateTime, String> getSchedule() {
        return schedule;
    }

    /**
     * Add a pair of time and event to the schedule.
     *
     * @param time  that this room will be booked
     * @param event corresponding to the time slot
     */
    public void addToSchedule(LocalDateTime time, String event) {
        schedule.put(time, event);
    }

    /**
     * Remove a pair of time and event from the schedule.
     *
     * @param time  that this room was booked
     * @param event corresponding to the time slot
     */
    public void removeFromSchedule(LocalDateTime time, String event) {
        schedule.remove(time);
    }
}
