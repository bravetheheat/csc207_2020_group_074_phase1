package main.gateways;

import main.entities.*;

import java.util.List;

/**
 * Interface to save and load entities from an external data source
 *
 * @author David Zhao
 */
public interface Gateway {


    /**
     * Loads User objects
     *
     * @return List of User
     */
    List<User> loadUsers();

    /**
     * Saves User objects
     *
     * @param users User objects to save
     */
    void saveUsers(List<User> users);

    /**
     * Load Event objects
     *
     * @return List of Events
     */
    List<Event> loadEvents();

    /**
     * Save Event objects
     *
     * @param events List of Events to save
     */
    void saveEvents(List<Event> events);

    /**
     * Save Room objects
     *
     * @param rooms List of Rooms to save
     */
    void saveRooms(List<Room> rooms);

    /**
     * Load Room objects
     *
     * @return List of Rooms
     */
    List<Room> loadRooms();

    /**
     * Save Message objects
     *
     * @param messages List of Message to save
     */
    void saveMessages(List<Message> messages);

    /**
     * Load Message objects
     *
     * @return List of Message
     */
    List<Message> loadMessages();

    /**
     * Save Inbox objects
     *
     * @param inboxes List of Inbox to save
     */
    void saveInboxes(List<Inbox> inboxes);

    /**
     * Load Inbox objects
     *
     * @return List of Inbox
     */
    List<Inbox> loadInboxes();
}
