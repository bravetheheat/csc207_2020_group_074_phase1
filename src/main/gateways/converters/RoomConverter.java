package main.gateways.converters;

import main.entities.Room;
import main.gateways.beans.RoomBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Implementation of Converter that serializes and deserializes Room
 *
 * @author David Zhao
 */
public class RoomConverter implements Converter<RoomBean, Room> {

    public List<Room> convertFromBeans(List<RoomBean> roomBeans) {
        List<Room> rooms = new ArrayList<>();

        for (RoomBean roomBean : roomBeans) {
            Room room = new Room();

            room.setId(roomBean.getId());
            room.setCapacity(roomBean.getCapacity());
            room.setRoomNum(roomBean.getRoomNum());
            if (roomBean.isHasTech()) {
                room.setTech();
            }
            if (roomBean.isHasStage()) {
                room.setStage();
            }

            if (roomBean.isTable()) {
                room.setToTable();
            }

            rooms.add(room);
        }

        return rooms;
    }

    public List<RoomBean> convertToBeans(List<Room> rooms) {
        List<RoomBean> roomBeanList = new ArrayList<>();

        for (Room room: rooms) {
            RoomBean roomBean = new RoomBean();

            roomBean.setId(room.getId());
            roomBean.setCapacity(room.getCapacity());
            roomBean.setRoomNum(room.getRoomNum());
            roomBean.setHasStage(room.getHasStage());
            roomBean.setHasTech(room.getHasTech());
            roomBean.setTable(room.getIsTable());
            roomBeanList.add(roomBean);
        }


        return roomBeanList;
    }
}
