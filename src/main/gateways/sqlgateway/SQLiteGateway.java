package main.gateways.sqlgateway;

import main.entities.*;
import main.gateways.Gateway;
import main.gateways.beans.*;
import main.gateways.converters.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the storage and loading of entities from an SQLite database.
 * It can be easily modified for any JDBC-compatible DB driver
 */
public class SQLiteGateway implements Gateway {

    private Connection conn;

    /**
     * Initial constructor that establishes a connection to the database
     * and creates the appropriate tables
     */
    public SQLiteGateway() {
        this.connect();
        if (conn != null) {
            this.initialize();
        }

    }

    /**
     * Connects to the database
     */
    private void connect() {
        try {
            String url = "jdbc:sqlite:src/store/app.db";
            this.conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to connect to DB");
        }
    }

    /**
     * Initializes the database by creating a schema
     */
    private void initialize() {
        this.createUserTable();
        this.createMessageTable();
        this.createRoomTable();
        this.createInboxTable();
        this.createEventTable();
    }

    /**
     * Creates the schema for the User entity
     */
    private void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(\n"
                + " id string PRIMARY KEY, \n"
                + " username string NOT NULL,\n"
                + " password string NOT NULL, \n"
                + " role string NOT NULL\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the schema for the Message entity
     */
    private void createMessageTable() {
        String sql = "CREATE TABLE IF NOT EXISTS messages(\n"
                + " id string PRIMARY KEY, \n"
                + " text string NOT NULL,\n"
                + " time string NOT NULL,\n"
                + " sender string NOT NULL"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the schema for the Room entity
     */
    private void createRoomTable() {
        String sql = "CREATE TABLE IF NOT EXISTS rooms(\n"
                + " id string PRIMARY KEY, \n"
                + " roomNum int NOT NULL,\n"
                + " capacity int NOT NULL,\n"
                + " hasTech boolean NOT NULL,\n"
                + " isTable boolean NOT NULL,\n"
                + " hasStage boolean NOT NULL"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Cannot create room table");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the schema for the Event entity
     */
    private void createEventTable() {
        String sql = "CREATE TABLE IF NOT EXISTS events(\n"
                + " id string PRIMARY KEY, \n"
                + " title string NOT NULL,\n"
                + " time string NOT NULL,\n"
                + " roomId string NOT NULL,\n"
                + " speakersId string,\n"
                + " attendeesId string,\n"
                + " type string NOT NULL,\n"
                + " duration int NOT NULL,\n"
                + " capacity int NOT NULL\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the schema for the Inbox entity
     */
    private void createInboxTable() {
        String sql = "CREATE TABLE IF NOT EXISTS inboxes(\n"
                + " id string NOT NULL, \n"
                + " user string NOT NULL,\n"
                + " messageId string NOT NULL\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * See interface definition
     *
     * @param users User objects to save
     */
    public void saveUsers(List<User> users) {
        Converter converter = new UserConverter();
        List<UserBean> userBeans = converter.convertToBeans(users);
        saveUserBeans(userBeans);

    }

    /**
     * See interface definition
     *
     * @return a list of User objects
     */
    public List<User> loadUsers() {
        Converter converter = new UserConverter();
        List<UserBean> userBeans = this.loadUserBeans();
        List<User> users = converter.convertFromBeans(userBeans);
        return users;
    }

    /**
     * Saves serialized User to table
     *
     * @param userBeans serialized User
     */
    private void saveUserBeans(List<UserBean> userBeans) {
        this.deleteAllValuesFromTable("users");
        String sql = "INSERT INTO users(id, username, password, role) VALUES(?,?,?,?)";
        for (UserBean userBean : userBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userBean.getId());
                pstmt.setString(2, userBean.getUsername());
                pstmt.setString(3, userBean.getPassword());
                pstmt.setString(4, userBean.getRole());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Load serialized User from table
     *
     * @return list of serialized User
     */
    private List<UserBean> loadUserBeans() {

        List<UserBean> userBeans = new ArrayList<>();
        String sql = "SELECT id, username, password, role FROM users";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setId(rs.getString("id"));
                userBean.setUsername(rs.getString("username"));
                userBean.setPassword(rs.getString("password"));
                userBean.setRole(rs.getString("role"));
                userBeans.add(userBean);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return userBeans;

    }

    /**
     * Saves list of Room in the DB
     *
     * @param rooms List of Rooms to save
     */
    public void saveRooms(List<Room> rooms) {
        Converter converter = new RoomConverter();
        List<RoomBean> roomBeans = converter.convertToBeans(rooms);
        saveRoomBeans(roomBeans);
    }

    /**
     * Loads the Rooms in the DB
     *
     * @return a list of Room entities
     */
    public List<Room> loadRooms() {
        Converter converter = new RoomConverter();
        List<RoomBean> roomBeans = loadRoomBeans();
        List<Room> rooms = converter.convertFromBeans(roomBeans);
        return rooms;
    }

    /**
     * Saves serialized Rooms to the DB
     *
     * @param roomBeans serialized Rooms
     */
    private void saveRoomBeans(List<RoomBean> roomBeans) {
        this.deleteAllValuesFromTable("rooms");
        String sql = "INSERT INTO rooms(id, roomNum, capacity, hasTech, isTable, hasStage) VALUES(?,?,?,?,?,?)";
        for (RoomBean roomBean : roomBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, roomBean.getId());
                pstmt.setInt(2, roomBean.getRoomNum());
                pstmt.setInt(3, roomBean.getCapacity());
                pstmt.setBoolean(4, roomBean.isHasTech());
                pstmt.setBoolean(5, roomBean.isTable());
                pstmt.setBoolean(6, roomBean.isHasStage());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Load serialized Rooms from the DB
     *
     * @return list of serialized Rooms
     */
    private List<RoomBean> loadRoomBeans() {
        List<RoomBean> roomBeans = new ArrayList<>();
        String sql = "SELECT id, roomNum, capacity, hasTech, isTable, hasStage FROM rooms";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RoomBean roomBean = new RoomBean();
                roomBean.setId(rs.getString("id"));
                roomBean.setRoomNum(rs.getInt("roomNum"));
                roomBean.setCapacity(rs.getInt("capacity"));
                roomBean.setHasTech(rs.getBoolean("hasTech"));
                roomBean.setTable(rs.getBoolean("isTable"));
                roomBean.setHasStage(rs.getBoolean("hasStage"));


                roomBeans.add(roomBean);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return roomBeans;
    }

    /**
     * Saves Message entities to the DB
     *
     * @param messages List of Message to save
     */
    public void saveMessages(List<Message> messages) {
        Converter converter = new MessageConverter();
        List<MessageBean> messageBeans = converter.convertToBeans(messages);
        saveMessageBeans(messageBeans);
    }

    /**
     * Load Message entities from the DB
     *
     * @return list of Message entities
     */
    public List<Message> loadMessages() {
        Converter converter = new MessageConverter();
        List<MessageBean> messageBeans = this.loadMessageBeans();
        List<Message> messages = converter.convertFromBeans(messageBeans);
        return messages;
    }

    /**
     * Saves serialized Message entities to the DB
     *
     * @param messageBeans list of serialized Message entities
     */
    private void saveMessageBeans(List<MessageBean> messageBeans) {
        this.deleteAllValuesFromTable("messages");
        String sql = "INSERT INTO messages(id, text, time, sender) VALUES(?,?,?,?)";
        for (MessageBean messageBean : messageBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, messageBean.getId());
                pstmt.setString(2, messageBean.getText());
                pstmt.setString(3, messageBean.getTime());
                pstmt.setString(4, messageBean.getSender());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads serialized Message entities from the DB
     *
     * @return list of serialized Message entities
     */
    private List<MessageBean> loadMessageBeans() {
        List<MessageBean> messageBeans = new ArrayList<>();
        String sql = "SELECT id, text, time, sender FROM messages";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MessageBean messageBean = new MessageBean();
                messageBean.setId(rs.getString("id"));
                messageBean.setText(rs.getString("text"));
                messageBean.setTime(rs.getString("time"));
                messageBean.setSender(rs.getString("sender"));
                messageBeans.add(messageBean);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messageBeans;
    }

    /**
     * Saves Event entities to the DB
     *
     * @param events List of Events to save
     */
    public void saveEvents(List<Event> events) {
        Converter converter = new EventConverter();
        List<EventBean> eventBeans = converter.convertToBeans(events);
        saveEventBeans(eventBeans);
    }

    /**
     * Loads Event entities from the DB
     *
     * @return a list of Event entities
     */
    public List<Event> loadEvents() {
        Converter converter = new EventConverter();
        List<EventBean> eventBeans = loadEventBeans();
        List<Event> events = converter.convertFromBeans(eventBeans);
        return events;
    }

    /**
     * Saves serialized Event entities to the DB
     *
     * @param eventBeans list of serialized Event entities
     */
    private void saveEventBeans(List<EventBean> eventBeans) {
        this.deleteAllValuesFromTable("events");
        String sql = "INSERT INTO events(id, title, time, roomId, speakersId, attendeesId, type, duration, capacity) VALUES(?,?,?,?,?,?,?,?,?)";
        for (EventBean eventBean : eventBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, eventBean.getId());
                pstmt.setString(2, eventBean.getTitle());
                pstmt.setString(3, eventBean.getTime());
                pstmt.setString(4, eventBean.getRoomID());
                pstmt.setString(5, eventBean.getSpeakersID());
                pstmt.setString(6, eventBean.getAttendeesId());
                pstmt.setString(7, eventBean.getType());
                pstmt.setInt(8, eventBean.getDuration());
                pstmt.setInt(9, eventBean.getCapacity());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
    }

    /**
     * Loads serialized Event entities from the DB
     *
     * @return list of serialized Event entities
     */
    private List<EventBean> loadEventBeans() {
        List<EventBean> eventBeans = new ArrayList<>();
        String sql = "SELECT id, title, time, roomId, speakersId, attendeesId, type, duration, capacity FROM events";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EventBean eventBean = new EventBean();
                eventBean.setId(rs.getString("id"));
                eventBean.setTitle(rs.getString("title"));
                eventBean.setTime(rs.getString("time"));
                eventBean.setRoomID(rs.getString("roomId"));
                eventBean.setSpeakersID(rs.getString("speakersId"));
                eventBean.setAttendeesId(rs.getString("attendeesId"));
                eventBean.setType(rs.getString("type"));
                eventBean.setDuration(rs.getInt("duration"));
                eventBean.setCapacity(rs.getInt("capacity"));
                eventBeans.add(eventBean);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return eventBeans;
    }

    /**
     * Saves list of Inbox entities to the DB
     *
     * @param inboxes List of Inbox to save
     */
    public void saveInboxes(List<Inbox> inboxes) {
        Converter converter = new InboxConverter();
        List<InboxBean> inboxBeans = converter.convertToBeans(inboxes);
        saveInboxBeans(inboxBeans);

    }

    /**
     * Loads list of Inbox entities from the DB
     *
     * @return list of Inbox entities
     */
    public List<Inbox> loadInboxes() {
        Converter converter = new InboxConverter();
        List<InboxBean> inboxBeans = this.loadInboxBeans();
        List<Inbox> inboxes = converter.convertFromBeans(inboxBeans);
        return inboxes;
    }

    /**
     * Saves list of serialized Inbox entities to the DB
     *
     * @param inboxBeans list of serialized Inbox entities
     */
    private void saveInboxBeans(List<InboxBean> inboxBeans) {
        this.deleteAllValuesFromTable("inboxes");
        String sql = "INSERT INTO inboxes(id, user, messageId) VALUES(?,?,?)";
        for (InboxBean inboxBean : inboxBeans) {
            try {

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, inboxBean.getId());
                pstmt.setString(2, inboxBean.getUser());
                pstmt.setString(3, inboxBean.getMessageID());
                pstmt.executeUpdate();


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
    }

    /**
     * Load list of serialized Inbox entities from the DB
     *
     * @return list of serialized Inbox entities
     */
    private List<InboxBean> loadInboxBeans() {
        List<InboxBean> inboxBeans = new ArrayList<>();
        String sql = "SELECT id, user, messageId FROM inboxes";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                InboxBean inboxBean = new InboxBean();
                inboxBean.setId(rs.getString("id"));
                inboxBean.setUser(rs.getString("user"));
                inboxBean.setMessageID(rs.getString("messageId"));
                inboxBeans.add(inboxBean);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inboxBeans;
    }

    /**
     * Clears all values in a specific table
     *
     * @param table name of table to be cleared
     */
    private void deleteAllValuesFromTable(String table) {
        String sql = "DELETE FROM " + table + ";";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
