package main.entities;

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
    private int capacity;
    private boolean isTable; // the room's layout is for a round table conference or it has rows of chairs.
    private boolean hasTech; // the room has projector or microphone to present
    private boolean hasStage; // the room has a stage for speaker to present

    /**
     * No-arg constructor for deserialization
     */
    public Room() {

    }


    /**
     * A room number and a capacity are required to create an instance of Room.
     */
    public Room(int roomNum, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.roomNum = roomNum;
        this.capacity = capacity;
        this.isTable = false;
        this.hasStage = false;
        this.hasTech = false;
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

    public void setStage() {
        this.hasStage = true;
    }

    public void setTech() {
        this.hasTech = true;
    }

    public void setToTable() {
        this.isTable = true;
    }

    public boolean getIsTable() {
        return this.isTable;
    }

    public boolean getHasTech() {
        return this.hasTech;
    }

    public boolean getHasStage() {
        return this.hasStage;
    }
}
