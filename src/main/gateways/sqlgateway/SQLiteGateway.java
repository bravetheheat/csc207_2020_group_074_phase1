package main.gateways.sqlgateway;

import main.entities.*;
import main.gateways.Gateway;
import main.gateways.beans.*;
import main.gateways.converters.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteGateway implements Gateway {

    private Connection conn;

    public SQLiteGateway() {
        this.connect();
        if (conn != null) {
            this.initialize();
        }

    }

    private void connect() {
        try {
            String url = "jdbc:sqlite:src/main/store.db";
            this.conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to connect to DB");
        }
    }

    private void initialize() {
        this.createUserTable();
        this.createMessageTable();
        this.createRoomTable();
        this.createInboxTable();
        this.createEventTable();
    }

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

    private void createRoomTable() {
        String sql = "CREATE TABLE IF NOT EXISTS rooms(\n"
                + " id string NOT NULL, \n"
                + " roomNum int NOT NULL,\n"
                + " eventId string NOT NULL,\n"
                + " eventTime string NOT NULL,\n"
                + " capacity int NOT NULL\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Cannot create room table");
            System.out.println(e.getMessage());
        }
    }

    private void createEventTable() {
        String sql = "CREATE TABLE IF NOT EXISTS events(\n"
                + " id string NOT NULL, \n"
                + " title string NOT NULL,\n"
                + " time string NOT NULL,\n"
                + " roomId string NOT NULL,\n"
                + " speakerId int NOT NULL,\n"
                + " attendeeId int NOT NULL\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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


    public void saveUsers(List<User> users) {
        Converter converter = new UserConverter();
        List<UserBean> userBeans = converter.convertToBeans(users);
        saveUserBeans(userBeans);

    }

    public List<User> loadUsers() {
        Converter converter = new UserConverter();
        List<UserBean> userBeans = this.loadUserBeans();
        List<User> users = converter.convertFromBeans(userBeans);
        return users;
    }

    private void saveUserBeans(List<UserBean> userBeans) {
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

    public void saveRooms(List<Room> rooms) {
        Converter converter = new RoomConverter();
        List<RoomBean> roomBeans = converter.convertToBeans(rooms);
        saveRoomBeans(roomBeans);
    }

    public List<Room> loadRooms() {
        Converter converter = new RoomConverter();
        List<RoomBean> roomBeans = loadRoomBeans();
        List<Room> rooms = converter.convertFromBeans(roomBeans);
        return rooms;
    }

    private void saveRoomBeans(List<RoomBean> roomBeans) {
        String sql = "INSERT INTO rooms(id, roomNum, eventId, eventTime, capacity) VALUES(?,?,?,?,?)";
        for (RoomBean roomBean : roomBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, roomBean.getId());
                pstmt.setInt(2, roomBean.getRoomNum());
                pstmt.setString(3, roomBean.getEventId());
                pstmt.setString(4, roomBean.getEventTime());
                pstmt.setInt(5, roomBean.getCapacity());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<RoomBean> loadRoomBeans() {
        List<RoomBean> roomBeans = new ArrayList<>();
        String sql = "SELECT id, roomNum, eventId, eventTime, capacity FROM rooms";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RoomBean roomBean = new RoomBean();
                roomBean.setId(rs.getString("id"));
                roomBean.setRoomNum(rs.getInt("roomNum"));
                roomBean.setEventId(rs.getString("eventId"));
                roomBean.setEventTime(rs.getString("eventTime"));
                roomBean.setCapacity(rs.getInt("capacity"));
                roomBeans.add(roomBean);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return roomBeans;
    }

    public void saveMessages(List<Message> messages) {
        Converter converter = new MessageConverter();
        List<MessageBean> messageBeans = converter.convertToBeans(messages);
        saveMessageBeans(messageBeans);
    }

    public List<Message> loadMessages() {
        Converter converter = new MessageConverter();
        List<MessageBean> messageBeans = this.loadMessageBeans();
        List<Message> messages = converter.convertFromBeans(messageBeans);
        return messages;
    }

    private void saveMessageBeans(List<MessageBean> messageBeans) {
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

    public void saveEvents(List<Event> events) {
        Converter converter = new EventConverter();
        List<EventBean> eventBeans = converter.convertToBeans(events);
        saveEventBeans(eventBeans);
    }

    public List<Event> loadEvents() {
        Converter converter = new EventConverter();
        List<EventBean> eventBeans = loadEventBeans();
        List<Event> events = converter.convertFromBeans(eventBeans);
        return events;
    }

    private void saveEventBeans(List<EventBean> eventBeans) {
        String sql = "INSERT INTO events(id, title, time, roomId, speakerId, attendeeId) VALUES(?,?,?,?,?,?)";
        for (EventBean eventBean : eventBeans) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, eventBean.getId());
                pstmt.setString(2, eventBean.getTitle());
                pstmt.setString(3, eventBean.getTime());
                pstmt.setString(4, eventBean.getRoomID());
                pstmt.setString(5, eventBean.getSpeakerID());
                pstmt.setString(6, eventBean.getAttendeeId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
    }

    private List<EventBean> loadEventBeans() {
        List<EventBean> eventBeans = new ArrayList<>();
        String sql = "SELECT id, title, time, roomId, speakerId, attendeeId FROM events";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EventBean eventBean = new EventBean();
                eventBean.setId(rs.getString("id"));
                eventBean.setTitle(rs.getString("title"));
                eventBean.setTime(rs.getString("time"));
                eventBean.setRoomID(rs.getString("roomId"));
                eventBean.setSpeakerID(rs.getString("speakerId"));
                eventBean.setAttendeeId(rs.getString("attendeeId"));
                eventBeans.add(eventBean);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return eventBeans;
    }

    public void saveInboxes(List<Inbox> inboxes) {
        Converter converter = new InboxConverter();
        List<InboxBean> inboxBeans = converter.convertToBeans(inboxes);
        saveInboxBeans(inboxBeans);

    }

    public List<Inbox> loadInboxes() {
        Converter converter = new InboxConverter();
        List<InboxBean> inboxBeans = this.loadInboxBeans();
        List<Inbox> inboxes = converter.convertFromBeans(inboxBeans);
        return inboxes;
    }

    private void saveInboxBeans(List<InboxBean> inboxBeans) {
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


}
