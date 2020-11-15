package main.gateways;

import main.entities.Event;
import main.entities.Room;
import main.entities.User;

import java.util.List;

public interface Gateway {


    List<User> loadUsers();

    void saveUsers(List<User> users);

    List<Event> loadEvents();

    void saveEvents(List<Event> events);

    void saveRooms(List<Room> rooms);

    List<Room> loadRooms();
}
