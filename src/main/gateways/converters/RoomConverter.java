package main.gateways.converters;

import main.entities.Room;
import main.gateways.beans.RoomBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Converter that serializes and deserializes Room
 *
 * @author David Zhao
 */
public class RoomConverter implements Converter<RoomBean, Room>{

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public List<Room> convertFromBeans(List<RoomBean> roomBeans) {
        Map<String, Room> rooms = new HashMap<>();

        for (RoomBean roomBean: roomBeans) {
            Room currentRoom = rooms.get(roomBean.getId());
            if (currentRoom == null) {
                currentRoom = new Room();
                currentRoom.setId(roomBean.getId());
                currentRoom.setCapacity(roomBean.getCapacity());
                currentRoom.setRoomNum(roomBean.getRoomNum());
                rooms.put(currentRoom.getId(), currentRoom);
            }

            LocalDateTime eventTime = LocalDateTime.parse(roomBean.getEventTime(), this.dateTimeFormatter);

            currentRoom.addToSchedule(eventTime, roomBean.getEventId());

        }
        List<Room> roomList = new ArrayList<>();
        roomList.addAll(rooms.values());
        return roomList;
    }

    public List<RoomBean> convertToBeans(List<Room> rooms) {
        List<RoomBean> roomBeanList = new ArrayList<>();

        for (Room room:rooms) {
            if (room.getSchedule().size()>0) {
                for (Map.Entry<LocalDateTime, String> entry : room.getSchedule().entrySet()) {
                    RoomBean roomBean = new RoomBean();
                    roomBean.setCapacity(room.getCapacity());
                    roomBean.setId(room.getId());
                    roomBean.setRoomNum(room.getRoomNum());
                    roomBean.setEventTime(entry.getKey().format(dateTimeFormatter));
                    roomBean.setEventId(entry.getValue());
                    roomBeanList.add(roomBean);
                }

            }
            else {
                RoomBean roomBean = new RoomBean();
                roomBean.setCapacity(room.getCapacity());
                roomBean.setId(room.getId());
                roomBean.setRoomNum(room.getRoomNum());
                roomBeanList.add(roomBean);
            }
        }

        return roomBeanList;
    }
}
