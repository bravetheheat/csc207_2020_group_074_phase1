package main.gateways.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RoomBean is used to serialize and deserialize Rooms
 * Each Room can be represented by one or more RoomBeans
 *
 * @author David Zhao
 */
public class RoomBean implements Serializable {
    private String id;
    private int roomNum;
    private int capacity;
    private boolean hasTech;
    private boolean isTable;
    private boolean hasStage;

    public RoomBean() {

    }

    public boolean isHasTech() {
        return hasTech;
    }

    public void setHasTech(boolean hasTech) {
        this.hasTech = hasTech;
    }

    public boolean isTable() {
        return isTable;
    }

    public void setTable(boolean table) {
        isTable = table;
    }

    public boolean isHasStage() {
        return hasStage;
    }

    public void setHasStage(boolean hasStage) {
        this.hasStage = hasStage;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomNum() {
        return this.roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }


    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
