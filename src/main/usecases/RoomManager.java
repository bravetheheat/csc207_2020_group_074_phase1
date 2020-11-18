package main.usecases;

import main.entities.Room;
import main.gateways.Gateway;

import java.time.LocalDateTime;
import java.util.*;

/**
 * this class stores all rooms which could hold events
 *
 * @author Ruoming Ren
 */
public class RoomManager {

    private Map<String, Room> rooms;

    public RoomManager() {
        this.rooms = new HashMap<>();
    }

    /**
     * a new room will be added if its roomNum is not conflict with others.
     *
     * @param roomNum  the new room number
     * @param capacity the new room's capacity
     * @return true if the room has been successfully created
     */
    public boolean addRoom(int roomNum, int capacity) {
        for (Room room : rooms.values()) {
            if (room.getRoomNum() == roomNum) {
                return false;
            }
        }
        Room newRoom = new Room(roomNum);
        newRoom.setCapacity(capacity);
        rooms.put(newRoom.getId(), newRoom);
        return true;
    }

    /**
     * the method will return the id of a room given its roomNum
     *
     * @param roomNum the roomNum of a room
     * @return the id of the room, if there doesn't exist a room with given roomNum, return null.
     */
    public String getRoomIDGivenRoomNum(int roomNum) {
        if (this.getRoomGivenRoomNum(roomNum) == null) {
            return null;
        } else {
            return getRoomGivenRoomNum(roomNum).getId();
        }
    }

    /**
     * the method will return the room object given its roomNum
     *
     * @param roomNum the roomNum of the room
     * @return the room object of the room, if there doesn't exist a room with given roomNum, return null.
     */
    public Room getRoomGivenRoomNum(int roomNum) {
        Room target = null;
        for (Room room : rooms.values()) {
            if (room.getRoomNum() == roomNum) {
                target = room;
            }
        }
        return target;
    }

    /**
     * give the schedule of a room given its roomNum
     *
     * @param roomNum the given roomNum
     * @return the schedule of the room. Return null if the room does not exist.
     */
    public Map<LocalDateTime, String> roomSchedule(int roomNum) {
        if (this.getRoomGivenRoomNum(roomNum) == null) {
            return null;
        } else {
            return this.getRoomGivenRoomNum(roomNum).getSchedule();
        }
    }

    /**
     * Gets a list of all the room numbers
     *
     * @return List of all room numbers
     */
    public List<Integer> getAllRooms() {
        List<Integer> allRooms = new ArrayList<Integer>();
        for (Room room : this.rooms.values()) {
            allRooms.add(room.getRoomNum());
        }
        return allRooms;
    }

    /**
     * Returns a collection of all the Room objects
     *
     * @return Collection of Room
     */
    public Collection<Room> getAllRoomsObject() {
        return this.rooms.values();
    }

    /**
     * Saves Room objects to a specified Gateway
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void saveRoomsFromGateway(Gateway gateway) {
        List<Room> roomList = new ArrayList<>();
        roomList.addAll(this.rooms.values());
        gateway.saveRooms(roomList);

    }

    /**
     * Loads Room objects from a specified Gateway
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void loadRoomsFromGateway(Gateway gateway) {
        this.rooms = new HashMap<>();
        List<Room> loadedRooms = gateway.loadRooms();
        for (Room room : loadedRooms) {
            this.rooms.put(room.getId(), room);
        }
    }


}
