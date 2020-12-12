package main.usecases;

import main.entities.Room;
import main.gateways.Gateway;

import java.time.LocalDateTime;
import java.util.*;

/**
 * this class stores all rooms which could hold events
 *
 * @author Ruoming Ren, Yile Xie
 * @since 2020-11-13
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
        Room newRoom = new Room(roomNum,capacity);
        rooms.put(newRoom.getId(), newRoom);
        return true;
    }

    /**
     * Return a room that corresponds to the given roomId iff the roomId exists in rooms
     * @param roomId of the target room
     * @return the room corresponding to the given roomId
     */
    public Room getRoomGivenId(String roomId){
        if (!rooms.containsKey(roomId)){
            return null;
        }
        else{
            return rooms.get(roomId);
        }
    }

    public int getRoomNumGivenId(String roomId){
        if (getRoomGivenId(roomId) != null){
            return getRoomGivenId(roomId).getRoomNum();
        }else{
            return -1;
        }
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
     * add constraints to room base on list of constraints
     *
     * @param roomNum room number
     * @param category room constraints
     * @return verification if there is any room constaints are modified
     */
    public boolean addConstraintToRoom(int roomNum, ArrayList<String> category){
        Room room = getRoomGivenRoomNum(roomNum);
        if (room == null){
            return false;
        }
        if (!category.contains("Tech") && !category.contains("Table") && !category.contains("Stage")){
            return true;
        }
        if (category.contains("Tech")) {
            room.setTech();
        }if (category.contains("Table")){
            room.setToTable();
        }if (category.contains("Stage")){
            room.setStage();
        }
        return true;
    }

    /**
     * Return the capacity of the target room provided its room number
     * @param roomNum of the room
     * @return the capacity of the room with given room number
     */
    public int getRoomCapacityGivenRoomNum(int roomNum){
        return this.getRoomGivenRoomNum(roomNum).getCapacity();
    }

    /**
     * give the schedule of a room given its roomNum
     *
     * @param roomNum the given roomNum
     * @return the schedule of the room. Return null if the room does not exist.
     */
//    public Map<LocalDateTime, String> roomSchedule(int roomNum) {
//        if (this.getRoomGivenRoomNum(roomNum) == null) {
//            return null;
//        } else {
//            return this.getRoomGivenRoomNum(roomNum).getSchedule();
//        }
//    }

    /**
     * get all rooms' number which inside the program
     * @return a list of rooms' number
     */
    public List<Integer> getAllRooms() {
        List<Integer> allRooms = new ArrayList<>();
        for (Room room : this.rooms.values()) {
            allRooms.add(room.getRoomNum());
        }
        return allRooms;
    }

    /**
     * get all rooms which inside the program
     * @return a list of rooms' objects
     */
    public Collection<Room> getAllRoomsObject() {
        return this.rooms.values();
    }

    /**
     * save all rooms which inside the program to the gateway.
     * @param gateway the gateway of the program
     */
    public void saveRoomsToGateway(Gateway gateway) {
        List<Room> roomList = new ArrayList<>();
        roomList.addAll(this.rooms.values());
        gateway.saveRooms(roomList);

    }

    /**
     * load all rooms which inside the gateway
     * @param gateway the gateway of the program
     */
    public void loadRoomsFromGateway(Gateway gateway) {
        this.rooms = new HashMap<>();
        List<Room> loadedRooms = gateway.loadRooms();
        for (Room room : loadedRooms) {
            this.rooms.put(room.getId(), room);
        }
    }


}
